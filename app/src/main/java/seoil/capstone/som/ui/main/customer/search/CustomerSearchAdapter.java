package seoil.capstone.som.ui.main.customer.search;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;


public class CustomerSearchAdapter extends  RecyclerView.Adapter<CustomerSearchAdapter.ViewHolder>{

    private ArrayList<String> mShopName;
    private ArrayList<String> mShopAddress;
    private CustomerSearchPresenter mPresenter;
    private String mShopId; //ShopId로
    private Context mContext;


    public CustomerSearchAdapter(ArrayList<String> listShopName, ArrayList<String> listShopAddress, CustomerSearchPresenter presenter, String shopId, Context context) {

        mShopName = listShopName;
        mShopAddress = listShopAddress;
        mPresenter = presenter;
        mShopId = shopId;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_customer_shop,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView textViewName= holder.textViewShopName;
        textViewName.setText(mShopName.get(position));
        TextView textViewAddress = holder.textViewShopAddress;
        textViewAddress.setText(mShopAddress.get(position));
    }


    @Override
    public int getItemCount() {
        if (mShopName == null) {
            return 0;
        }
        return mShopName.size();
    }
    public void setFilter(ArrayList<String> listShopName, ArrayList<String> listShopAddress){

        mShopName = listShopName;
        mShopAddress = listShopAddress;
        notifyDataSetChanged();
    }


    public class OnItemClickListener {
        void onClick(int pos, View itemView) {

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewShopName;
        TextView textViewShopAddress;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewShopName = itemView.findViewById(R.id.textViewShopName);
            textViewShopAddress = itemView.findViewById(R.id.textViewShopAddress);

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                }
            });
        }
    }
}

/*
    public void setQuery(CharSequence query, boolean submit) {
        mSearchSrcTextView.setText(query);
        if (query != null) {
            mSearchSrcTextView.setSelection(mSearchSrcTextView.length());
            mUserQuery = query;
        }
                if (submit && !TextUtils.isEmpty(query)) {
            onSubmitQuery();
        }
    }
*/
