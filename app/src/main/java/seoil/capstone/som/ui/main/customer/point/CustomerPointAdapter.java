package seoil.capstone.som.ui.main.customer.point;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;

// 포인트 리사이클러뷰 어댑터
public class CustomerPointAdapter extends RecyclerView.Adapter<CustomerPointAdapter.ViewHolder> {

    public static final int HEADER = 0;         //포인트 구분
    public static final int CHILD = 1;          //포인트 정보

    private final Context mContext;             //view의 콘텍스트

    private List<Item> mPointList;              //전체 포인트 정보
    private List<String> mPointDate;            //사용, 적립 포인트 날짜
    private  Resources mResources;

    public CustomerPointAdapter(List<Item> point, Context context, List<String> pointDate, Resources resources) {

        mPointList = point;

        mContext = context;
        mPointDate = pointDate;

        mResources = resources;
    }

    @NonNull
    @Override
    public CustomerPointAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        Context context = parent.getContext();

        switch (viewType) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.recycler_customer_point_header, parent, false);
                ViewHolder header = new ViewHolder(view);
                return header;
            case CHILD:
                TextView itemTextView = new TextView(context);
                itemTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return new CustomerPointAdapter.ViewHolder(itemTextView) {
                };
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerPointAdapter.ViewHolder holder, int position) {

        final Item item = mPointList.get(position);
        switch (item.type) {
            case HEADER:

                final ViewHolder itemController =  holder;
                itemController.refferalItem = item;
                itemController.headerTitle.setText(item.text);
                itemController.headerTitle.setBackground(mResources.getDrawable(R.drawable.customer_radius));
                itemController.headerTitle.setPadding(0, 0, 0, 0);
                itemController.headerTitle.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // 숨겨진 아이템 없을 때
                        if (item.invisibleChildren == null) {

                            item.invisibleChildren = new ArrayList<>();
                            int count = 0;
                            int pos = mPointList.indexOf(itemController.refferalItem);
                            while (mPointList.size() > pos + 1
                                    && mPointList.get(pos + 1).type == CHILD) {

                                item.invisibleChildren.add(mPointList.remove(pos + 1));
                                count++;
                            }

                            notifyItemRangeRemoved(pos + 1, count);
                        } else {

                            int pos = mPointList.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {

                                mPointList.add(index, i);
                                index++;
                            }

                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:

                holder.headerTitle.setBackground(null);
                float dp = mContext.getResources().getDisplayMetrics().density;
                int subItemPaddingLeft = (int) (18 * dp);
                int subItemPaddingTopAndBottom = (int) (5 * dp);
                holder.headerTitle.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);

                int count = 0;
                for (int i = 0; i < position; i ++) {

                    if (mPointList.get(i).invisibleChildren != null) {
                        count = count + mPointList.get(i).invisibleChildren.size();
                    }
                }

                if (position > 1) {

                    final String temp = mPointList.get(position).text + " : " + mPointDate.get(position + count);
                    holder.headerTitle.setText(temp);
                } else {

                    holder.headerTitle.setText(mPointList.get(position).text);
                }

                break;
        }
    }



    @Override
    public int getItemCount() {
        if (mPointList == null) {

            return 0;
        }
        return mPointList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView headerTitle;
        public Item refferalItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTitle = itemView.findViewById(R.id.textViewCPointHeader);
        }
    }
    
    //포인트에서 사용할 데이터 형식 (헤더에 invisibleChildren 데이터 넣을 시 뷰홀더에서 안보이게 설정)
    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    //포인트, 날짜 설정
    public void setData(List<Item> pointList, List<String> pointDate) {
        mPointList = pointList;
        mPointDate = pointDate;
        notifyDataSetChanged();
    }
}

