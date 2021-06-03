package seoil.capstone.som.ui.main.customer.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class CustomerBookmarkEventAdapter extends RecyclerView.Adapter<CustomerBookmarkEventAdapter.ViewHolder> {

    private final CustomerBookmarkPresenter mPresenter;
    private ArrayList<String> mMarketName;
    private ArrayList<String> mMarketEventName;
    private ArrayList<String> mMarketEventDate;
    private ArrayList<Integer> mMarketEventCode;

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

    @Override
    public int getItemCount() {
        if (mMarketName == null) {

            return 0;
        }
        return mMarketName.size();
    }

    public void setData(ArrayList<String> marketName, ArrayList<String> eventName, ArrayList<String> eventDate, ArrayList<Integer> eventCode) {

        mMarketName = marketName;
        mMarketEventName = eventName;
        mMarketEventDate = eventDate;
        mMarketEventCode = eventCode;

        notifyDataSetChanged();
    }

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
