package seoil.capstone.som.ui.main.customer.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

// 손님 즐겨찾기 매장 정보 리사이클러뷰 어댑터
public class CustomerBookmarkMarketInfoAdapter extends RecyclerView.Adapter<CustomerBookmarkMarketInfoAdapter.ViewHolder> {

    private  ArrayList<String> mShopName;               //즐겨찾기된 매장의 이름
    private  ArrayList<String> mShopCategory;           //즐겨찾기된 매장의 분류

    public CustomerBookmarkMarketInfoAdapter(ArrayList<String> marketName, ArrayList<String> marketCategory) {

        mShopName = marketName;
        mShopCategory = marketCategory;
    }

    @NonNull
    @Override
    public CustomerBookmarkMarketInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_bookmark, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerBookmarkMarketInfoAdapter.ViewHolder holder, int position) {

        TextView marketName = holder.marketName;
        TextView marketCategory = holder.marketCategory;

        marketName.setText(mShopName.get(position));
        marketCategory.setText(mShopCategory.get(position));

    }

    @Override
    public int getItemCount() {
        if (mShopName == null) {
            return 0;
        }
        return mShopName.size();
    }

    // 뷰홀더 모델
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView marketName;
        private final TextView marketCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            marketName = itemView.findViewById(R.id.textViewCBookmarkRecyclerMarketName);
            marketCategory = itemView.findViewById(R.id.textViewCBookmarkRecyclerMarketCategory);

            marketName.setOnClickListener(this);
            marketCategory.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    // 데이터 설정
    public void setData(ArrayList<String> shopName, ArrayList<String> shopCategory) {

        mShopName = shopName;
        mShopCategory = shopCategory;
        notifyDataSetChanged();
    }
}
