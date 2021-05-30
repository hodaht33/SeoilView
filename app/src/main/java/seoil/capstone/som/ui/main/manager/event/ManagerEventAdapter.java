package seoil.capstone.som.ui.main.manager.event;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.event.detail.DetailEventActivity;

public class ManagerEventAdapter extends RecyclerView.Adapter<ManagerEventAdapter.ViewHolder> {
    
    private ArrayList<Integer> mEventCode = new ArrayList<>();          //이벤트 구분코드
    private ArrayList<String> mEventName = new ArrayList<>();           //이벤트 이름
    private ArrayList<String> mEventEndDate = new ArrayList<>();        //이벤트 종료날짜
    private ArrayList<String> mEventStartDate = new ArrayList<>();      //이벤트 시작 날짜

    private ManagerEventPresenter mPresenter;

    public ManagerEventAdapter(ArrayList<String> eventName, ArrayList<Integer> eventCode,
                               ArrayList<String> eventStartDate, ArrayList<String> eventEndDate,
                               ManagerEventPresenter presenter) {

        mEventCode = eventCode;
        mEventName = eventName;
        mEventStartDate = eventStartDate;
        mEventEndDate = eventEndDate;
        mPresenter = presenter;
    }

    //뷰 홀더 생성
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_manager_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //뷰 홀더 초기화
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setEventCode(mEventCode.get(position));
            }
        };

        holder.textViewContent.setText(mEventName.get(position));
        final String temp = mEventStartDate.get(position) + "~" + mEventEndDate.get(position);
        holder.textViewDate.setText(temp);

        holder.textViewContent.setOnClickListener(clickListener);
        holder.textViewDate.setOnClickListener(clickListener);


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
                mEventStartDate.remove(0);
                mEventEndDate.remove(0);
            }
        }
        notifyItemRangeChanged(0, size);
    }

    //어댑터에 있는 데이터 변경
    public void setData(ArrayList<String> eventName,ArrayList<Integer> eventCode, ArrayList<String> eventStartDate, ArrayList<String> eventEndDate) {

        clear();

        mEventName = eventName;
        mEventCode = eventCode;
        mEventStartDate = eventStartDate;
        mEventEndDate = eventEndDate;
    }

    //뷰 홀더 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewContent;
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewContent = itemView.findViewById(R.id.textViewMEventRecyclerContentName);
            textViewDate = itemView.findViewById(R.id.textViewMEventRecyclerDate);
        }
    }
}
