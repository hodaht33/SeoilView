package seoil.capstone.som.ui.main.customer.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class CustomerSearchFragment extends Fragment implements CustomerSearchContract.View{

    private ArrayList<String> mShopName;
    private ArrayList<String> mShopAddress;

    private String mSearchQuery;
    private String mShopId;

    private SearchView mSearch;
    private SwipeRefreshLayout mSwipeLayout;
    private ProgressBar mProgress;
    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;

    private CustomerSearchPresenter mPresenter;
    private CustomerSearchAdapter mAdapterShop;


    public CustomerSearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mPresenter = new CustomerSearchPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);

        mShopName = new ArrayList<>();
        mShopAddress = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_search, container, false);

        initView(view);


        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mShopId = bundle.getString("id");

        mRecyclerView = view.findViewById(R.id.recyclerViewCustomerSearch);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterShop = new CustomerSearchAdapter(mShopName, mShopAddress, mPresenter, mShopId, getContext());
        mRecyclerView.setAdapter(mAdapterShop);
        initListener(mShopId);


        return view;
    }



    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerViewCustomerSearch);
    }

    private void initListener(String mShopId) {

    }



    @Override
    public void onRefresh() {
        mSearch.clearFocus();

    }

    @Override
    public String getQueryString() {
        return null;
    }


    @Override
    public void showLoadingSpinner(boolean show) {

    }

    @Override
    public void showSpinnerAtBottom(boolean show) {

    }

    @Override
    public void clearList() {

    }

    @Override
    public boolean isListEmpty() {
        return false;
    }

    @Override
    public void setLayoutAdapterShop(ArrayList<String> listShopName, ArrayList<String> listShopAddress) {
        mAdapterShop.setFilter(listShopName, listShopAddress);
    }

    @Override
    public void initShop() {
        mPresenter.getShop(mShopId);
    }

    @Override
    public void showProgress() {

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}