package seoil.capstone.som.ui.main.customer.search;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.model.ShopDTO;


public class CustomerSearchAdapter extends  RecyclerView.Adapter<CustomerSearchAdapter.ViewHolder>{

    private ArrayList<ShopDTO> arrayList;

    private ArrayList<String> mShopName;
    private ArrayList<String> mShopCategory;
    private CustomerSearchPresenter mPresenter;
    private String mShopId; //ShopId로
    private Context mContext;


    public CustomerSearchAdapter(ArrayList<String> listShopName, ArrayList<String> listShopCategory, CustomerSearchPresenter presenter, String shopId, Context context) {

        mShopName = listShopName;
        mShopCategory = listShopCategory;
        mPresenter = presenter;
        mShopId = shopId;
        mContext = context;

    }

    //뷰 홀더가 생성되었을 때
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_shop,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return  viewHolder;
    }

    //데이터와 뷰를 연결
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewShopName.setText(mShopName.get(position));
        holder.textViewShopCategory.setText(mShopCategory.get(position));

        //listview가 클릭 됬을때
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //클릭시 shop이름 보여주기
                String Shopname = holder.textViewShopName.getText().toString();
                Toast.makeText(v.getContext() , Shopname,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //목록의 데이터 갯수
    @Override
    public int getItemCount() {

        return mShopName.size(); //리스트 갯수 보여줌

    }

    public  void  setData(ArrayList<String> listShopName, ArrayList<String> listShopCategory){
        mShopName = listShopName;
        mShopCategory = listShopCategory;
        notifyDataSetChanged();
    }

    // 아이템 데이터  연결
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewShopName;
        TextView textViewShopCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewShopName = itemView.findViewById(R.id.textViewShopName);
            textViewShopCategory = itemView.findViewById(R.id.textViewShopCategory);

        }
    }
}

