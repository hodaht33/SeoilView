package seoil.capstone.som.ui.main.customer.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class CustomerBookmarkAdapter extends RecyclerView.Adapter<CustomerBookmarkAdapter.ViewHolder> {

    private  ArrayList<String> mShopName;
    private  ArrayList<String> mShopCategory;

    public CustomerBookmarkAdapter(ArrayList<String> marketName, ArrayList<String> marketCategory) {

        mShopName = marketName;
        mShopCategory = marketCategory;
    }

    @NonNull
    @Override
    public CustomerBookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_bookmark, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerBookmarkAdapter.ViewHolder holder, int position) {

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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView marketName;
        private TextView marketCategory;

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

    public void setData(ArrayList<String> shopName, ArrayList<String> shopCategory) {

        mShopName = shopName;
        mShopCategory = shopCategory;
        notifyDataSetChanged();
    }
}
