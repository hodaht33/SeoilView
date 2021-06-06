package seoil.capstone.som.ui.main.manager.statistics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerStatisticsAdapter extends RecyclerView.Adapter<ManagerStatisticsAdapter.ViewHolder> {

    private ArrayList<Item> data;

    public ManagerStatisticsAdapter(ArrayList<Item> data) {

        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_manager_statistics_sales, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Item temp = data.get(position);
        holder.textViewDate.setText(temp.date);
        holder.textViewSales.setText(temp.sales);
        holder.textViewCost.setText(temp.cost);
    }

    @Override
    public int getItemCount() {
        if (data == null) {

            return 0;
        }
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDate;
        TextView textViewSales;
        TextView textViewCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDate = itemView.findViewById(R.id.textViewDateRecycler);
            textViewSales = itemView.findViewById(R.id.textViewSalesRecycler);
            textViewCost = itemView.findViewById(R.id.textViewRecyclerCost);
        }
    }

    public static class Item {

        String date;
        String sales;
        String cost;
    }

    public void setData(ArrayList<Item> data) {

        this.data = data;
        notifyDataSetChanged();
    }
}
