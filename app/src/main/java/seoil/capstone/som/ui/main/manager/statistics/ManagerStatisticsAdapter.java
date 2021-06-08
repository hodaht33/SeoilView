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

    private ArrayList<Item> data;       //점주 통계 어댑터의 데이터

    public ManagerStatisticsAdapter(ArrayList<Item> data) {     //점주 통계 어댑터의 생성자

        this.data = data;
    }
    
    //뷰 홀더가 생성될 때
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_manager_statistics_sales, parent, false);

        return new ViewHolder(view);
    }

    //데이터가 뷰 홀더에 적용될 때
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Item temp = data.get(position);
        holder.textViewDate.setText(temp.date);

        if (temp.sales == null) {               //매출의 널값 처리

            holder.textViewSales.setText("0원");
        } else {

            final String tempSales = temp.sales + "원";
            holder.textViewSales.setText(tempSales);
        }

        if (temp.cost == null) {                //지출의 널값 처리

            holder.textViewCost.setText("0원");
        } else {

            final String tempCost = Math.abs(temp.cost) + "원";
            holder.textViewCost.setText(tempCost);
        }

    }

    @Override
    public int getItemCount() {
        if (data == null) {

            return 0;
        }
        return data.size();
    }

    //점주 통계 어댑터의 뷰홀더
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

    //점주 통계 어댑터에서 사용하는 아이템 형식
    public static class Item {

        String date;
        Integer sales;
        Integer cost;

        public Item(String date, Integer sales, Integer cost) {

            this.date = date;
            this.sales = sales;
            this.cost = cost;
        }
    }

    //데이터 초기화
    private void clear() {

        if (data == null) {

            return;
        }

        int size = data.size();

        if (data.size() > 0) {
            data.subList(0, data.size()).clear();
        }

        notifyItemMoved(size, 0);
    }

    //어댑터의 데이터를 외부에서 전달받은 데이터로 설정
    public void setData(ArrayList<Item> data) {

        if (data == null) {

            clear();
        } else {

            this.data = data;
            notifyDataSetChanged();
        }
    }
}
