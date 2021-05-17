package seoil.capstone.som.ui.main.customer.search;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.model.ShopData;


public class CustomerSearchAdapter extends  RecyclerView.Adapter<CustomerSearchAdapter.SearchViewHolder>{

    private List<ShopData> mitemList = new ArrayList<>();
    private  SearchRecyclerInterface mInterface;


    public interface  SearchRecyclerInterface {
        void onSearchItemClicked(String query);  //저장된 객체(내부에 저장)

        void onSearchDeleteClicked(ShopData shopData); //저장된 객체 삭제
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchViewHolder extends  RecyclerView.ViewHolder {

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
