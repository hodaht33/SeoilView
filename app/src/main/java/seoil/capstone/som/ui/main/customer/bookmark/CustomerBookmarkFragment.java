package seoil.capstone.som.ui.main.customer.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class CustomerBookmarkFragment extends Fragment implements View.OnClickListener, CustomerBookmarkContract.View{

    private CustomerBookmarkPresenter mPresenter;
    private CustomerBookmarkAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mShopName;
    private ArrayList<String> mShopCategory;
    private String mUserId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerBookmarkPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mShopName = new ArrayList<>();
        mShopCategory = new ArrayList<>();

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mUserId = bundle.getString("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_bookmark, container, false);

        initView(view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CustomerBookmarkAdapter(mShopName, mShopCategory);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.getBookmarkShopInfo(mUserId);

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerViewCPointBookmark);
    }

    @Override
    public void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory) {

        mShopName = shopName;
        mShopCategory = shopCategory;
        mAdapter.setData(shopName, shopCategory);
    }
}
