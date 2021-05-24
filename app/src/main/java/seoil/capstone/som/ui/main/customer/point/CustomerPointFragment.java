package seoil.capstone.som.ui.main.customer.point;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;


public class CustomerPointFragment extends Fragment implements CustomerPointContract.View, View.OnClickListener {


    private RecyclerView mRecyclerView;
    private List<CustomerPointAdapter.Item> mPoint;

    private CustomerPointAdapter mAdapter;
    private CustomerPointPresenter mPresenter;
    private String mUserId;
    private List<String> mPointDate;

    public CustomerPointFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPointDate = new ArrayList<>();
        mPoint = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_customer_point, container, false);

        initView(view);

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mUserId = bundle.getString("id");


        mPresenter = new CustomerPointPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        initPoint();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CustomerPointAdapter(mPoint, getContext(), mPointDate);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void onClick(View v) {

    }

    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerViewCPoint);
    }

    @Override
    public synchronized void setCurrentPoint(List<CustomerPointAdapter.Item> currentPoint) {

        if (mPoint != null) {

            mPoint.clear();

        }

        mPoint.addAll(currentPoint);
        mPresenter.getUsePoint(mUserId);
    }

    @Override
    public synchronized void setUsePoint(CustomerPointAdapter.Item usePoint, List<String> useDate) {

        if (mPointDate != null) {

            mPointDate.clear();
        }

        mPointDate.add("");
        mPointDate.add("");
        mPointDate.addAll(useDate);

        mPoint.add(usePoint);
        mPresenter.getSavePoint(mUserId);
    }

    @Override
    public synchronized void setSavePoint(CustomerPointAdapter.Item savePoint, List<String> saveDate) {

        mPoint.add(savePoint);

        mPointDate.addAll(saveDate);

        setData();
    }

    public synchronized void initPoint() {

        mPresenter.getCurrentPoint(mUserId);
    }

    private void setData() {

        mAdapter.setData(mPoint, mPointDate);
    }
}