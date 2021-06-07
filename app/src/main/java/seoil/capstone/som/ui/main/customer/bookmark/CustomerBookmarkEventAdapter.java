package seoil.capstone.som.ui.main.customer.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

// 손님 즐겨찾기 이벤트 리사이클러뷰 어댑터
public class CustomerBookmarkEventAdapter extends RecyclerView.Adapter<CustomerBookmarkEventAdapter.ViewHolder> {

    private final CustomerBookmarkPresenter mPresenter;             //어댑터에서 인텐트 사용
    private ArrayList<String> mMarketName;                          //즐겨찾기된 매장 이름
    private ArrayList<String> mMarketEventName;                     //즐겨찾기된 매장의 진행중인 이벤트명
    private ArrayList<String> mMarketEventDate;                     //즐겨찾기된 매장의 진행중인 이벤트 기간
    private ArrayList<Integer> mMarketEventCode;                    //즐겨찾기된 매장의 진행중인 이벤트 코드

    public CustomerBookmarkEventAdapter(CustomerBookmarkPresenter presenter, ArrayList<String> marketName,
                                            ArrayList<String> eventName, ArrayList<String> eventDate,
                                            ArrayList<Integer> eventCode) {

        mMarketEventName = eventName;
        mMarketName = marketName;
        mMarketEventDate = eventDate;
        mMarketEventCode = eventCode;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public CustomerBookmarkEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_bookmark_event, parent, false);

        return new ViewHolder(view);
    }

    // 뷰홀더 재사용시 호출되는 콜백 메서드
    @Override
    public void onBindViewHolder(@NonNull CustomerBookmarkEventAdapter.ViewHolder holder, int position) {

        View.OnClickListener detailEventCustomer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.intentDetailEventCustomer(mMarketEventCode.get(position));
            }
        };

        holder.textViewMarketName.setText(mMarketEventName.get(position));
        holder.textViewEventDate.setText(mMarketEventDate.get(position));
        holder.textViewEventName.setText(mMarketEventName.get(position));

        holder.textViewMarketName.setOnClickListener(detailEventCustomer);
        holder.textViewEventName.setOnClickListener(detailEventCustomer);
        holder.textViewEventDate.setOnClickListener(detailEventCustomer);
    }

    // 뷰홀더 아이템 수 반환
    @Override
    public int getItemCount() {
        if (mMarketName == null) {

            return 0;
        }
        return mMarketName.size();
    }

    //데이터 변경
    public void setData(ArrayList<String> marketName, ArrayList<String> eventName, ArrayList<String> eventDate, ArrayList<Integer> eventCode) {

        mMarketName = marketName;
        mMarketEventName = eventName;
        mMarketEventDate = eventDate;
        mMarketEventCode = eventCode;

        notifyDataSetChanged();
    }

    // 뷰홀더 모델
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMarketName;
        TextView textViewEventName;
        TextView textViewEventDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewMarketName = itemView.findViewById(R.id.textViewCBookmarkEventMarketNameRecycler);
            textViewEventName = itemView.findViewById(R.id.textViewCBookmarkEventNameRecycler);
            textViewEventDate = itemView.findViewById(R.id.textViewCBookmarkEventDateRecycler);
        }
    }


}
