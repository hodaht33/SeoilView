package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerLedgerTextAdapter extends RecyclerView.Adapter<ManagerLedgerTextAdapter.ViewHolder> {

    private ArrayList<String> mDataName = null;
    private ArrayList<Integer> mDataAmount = null;

    ManagerLedgerTextAdapter(ArrayList<String> listName, ArrayList<Integer> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
    }

    @NonNull
    @Override
    public ManagerLedgerTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recyclerview_manager_ledger_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerLedgerTextAdapter.ViewHolder holder, int position) {

        TextView textView = holder.textViewItemName;
        textView.setText(mDataName.get(position));
        TextView textView1 = holder.textViewItemAmount;
        textView1.setText(String.valueOf(mDataAmount.get(position)));
    }

    @Override
    public int getItemCount() {

        return mDataName.size();
    }

    public void setData(ArrayList<String> listName, ArrayList<Integer> listAmount) {

        mDataName = listName;
        mDataAmount = listAmount;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewItemName;
        TextView textViewItemAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemName = itemView.findViewById(R.id.textView_ledger_item_name);
            textViewItemAmount = itemView.findViewById(R.id.textView_ledger_item_amount);
        }


    }
}
