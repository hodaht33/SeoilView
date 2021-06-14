package seoil.capstone.som.ui.main.manager.event;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;

public class ManagerEventAdapter extends RecyclerView.Adapter<ManagerEventAdapter.ViewHolder> {

    public static final int HEADER = 0;
    public static final int CHILD = 1;


    private ArrayList<Integer> mEventCode = new ArrayList<>();          //이벤트 구분코드
    private ArrayList<Item> mEventName = new ArrayList<>();           //이벤트 이름
    private ArrayList<String> mEventDate = new ArrayList<>();        //이벤트 기간
    private Resources mResources;

    private ManagerEventPresenter mPresenter;

    public ManagerEventAdapter(ArrayList<Item> eventName, ArrayList<Integer> eventCode,
                               ArrayList<String> eventEndDate,
                               ManagerEventPresenter presenter, Resources resources) {

        mResources = resources;
        mEventCode = eventCode;
        mEventName = eventName;
        mEventDate = eventEndDate;
        mPresenter = presenter;
    }

    //뷰 홀더 생성
    @NonNull
    @Override
    public ManagerEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_manager_event, parent, false);

        return new ViewHolder(view);
    }

    //뷰 홀더 초기화
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Item item = mEventName.get(position);
        final ViewHolder itemController =  holder;

        switch (item.type) {

            case HEADER:

                itemController.refferalItem = item;
                itemController.textViewDate.setEnabled(false);
                itemController.textViewDate.setVisibility(View.GONE);
                itemController.textViewContentName.setText(item.textName);
                itemController.textViewContentName.setTextColor(mResources.getColor(R.color.black));
                itemController.textViewContentName.setBackground(mResources.getDrawable(R.drawable.meneger_event_radius));
                itemController.textViewContentName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // 숨겨진 아이템 없을 때
                        if (item.invisibleChildren == null) {

                            item.invisibleChildren = new ArrayList<>();
                            int count = 0;
                            int pos = mEventName.indexOf(itemController.refferalItem);
                            while (mEventName.size() > pos + 1
                                    && mEventName.get(pos + 1).type == CHILD) {

                                item.invisibleChildren.add(mEventName.remove(pos + 1));
                                count++;
                            }

                            notifyItemRangeRemoved(pos + 1, count);
                        } else {

                            int pos = mEventName.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {

                                mEventName.add(index, i);
                                index++;
                            }

                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:

                itemController.textViewDate.setEnabled(true);
                itemController.textViewDate.setVisibility(View.VISIBLE);
                itemController.textViewContentName.setBackgroundColor(mResources.getColor(R.color.white));
                itemController.textViewContentName.setTextColor(mResources.getColor(R.color.black));

                int count = 0;
                for (int i = 0; i <= position; i ++) {

                    if (mEventName.get(i).invisibleChildren != null) {

                        count = count + mEventName.get(i).invisibleChildren.size();
                    }
                }

                if (position > 1) {

                    int index = position + count;
                    holder.textViewContentName.setText(mEventName.get(position).textName);
                    holder.textViewDate.setText(mEventDate.get(index));

                    View.OnClickListener clickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.setEventCode(mEventCode.get(index));
                        }
                    };

                    holder.textViewContentName.setOnClickListener(clickListener);
                    holder.textViewDate.setOnClickListener(clickListener);
                } else {

                    holder.textViewContentName.setText(mEventName.get(position).textName);
                    holder.textViewDate.setText(mEventDate.get(position));

                    View.OnClickListener clickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.setEventCode(mEventCode.get(position));
                        }
                    };

                    holder.textViewContentName.setOnClickListener(clickListener);
                    holder.textViewDate.setOnClickListener(clickListener);
                }
                break;
        }
    }

    //현재 데이터의 갯수 조회
    @Override
    public int getItemCount() {
        if (mEventName == null) {

            return 0;
        }
        return mEventName.size();
    }

    // 데이터 초기화
    public void clear() {

        if (mEventName == null) {

            return;
        }

        int size = mEventName.size();

        if (size > 0) {

            for (int i = 0; i < size; i++) {
                mEventName.remove(0);
                mEventCode.remove(0);
                mEventDate.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    //어댑터에 있는 데이터 변경
    public void setData(ArrayList<Item> eventName,ArrayList<Integer> eventCode, ArrayList<String> eventDate) {

        clear();

        mEventName = eventName;
        mEventCode = eventCode;
        mEventDate = eventDate;
    }

    //뷰 홀더 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewContentName;
        TextView textViewDate;
        public Item refferalItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewContentName = itemView.findViewById(R.id.textViewMEventRecyclerContentName);
            textViewDate = itemView.findViewById(R.id.textViewMEventRecyclerDate);
        }
    }

    public static class Item {

        public int type;
        public String textName;
        public String textDate;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String textName, String textDate) {
            this.type = type;
            this.textName = textName;
            this.textDate = textDate;
        }
    }
}
