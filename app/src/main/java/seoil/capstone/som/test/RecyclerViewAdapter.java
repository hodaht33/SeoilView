package seoil.capstone.som.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seoil.capstone.som.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    class ViewHolder extends RecyclerView.ViewHolder {

        private EditText usingDate;
        private EditText shopCode;
        private EditText shopName;
        private EditText amount;

        public ViewHolder(View view) {
            super(view);

            usingDate = view.findViewById(R.id.textUsingDate);
            shopCode = view.findViewById(R.id.textShopCode);
            shopName = view.findViewById(R.id.textShopName);
            amount = view.findViewById(R.id.textUsingAmount);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_test_recycler_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
