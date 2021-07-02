package seoil.capstone.som.ui.main.customer.point;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;

// 포인트 뷰
public class CustomerPointFragment extends Fragment implements CustomerPointContract.View, View.OnClickListener {

    private RecyclerView mRecyclerView;                 //포인터를 보여줄 리사이클러뷰
    private List<CustomerPointAdapter.Item> mPoint;     //포인트 정보

    private CustomerPointAdapter mAdapter;              //포인트 리사이클러뷰에 사용할 어댑터
    private CustomerPointPresenter mPresenter;          //사용자 포인트 Presenter
    private String mUserId;                             //사용자 아이디
    private List<String> mPointDate;                    //사용 및 적립 포인트 날짜 정보

    // 프레젠터 생성, ArrayList초기화
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPointDate = new ArrayList<>();
        mPoint = new ArrayList<>();

        mPresenter = new CustomerPointPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    // 프레젠터 삭제
    @Override
    public void onDestroy() {

        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    // UI 초기화
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_point, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerViewCPoint);

        mUserId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

        initPoint();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new CustomerPointAdapter(mPoint, getContext(), mPointDate, getResources());
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
    public void showDialog(String msg) {

    }

    @Override
    public void onClick(View v) {

    }

    //잔여 포인트 설정
    @Override
    public synchronized void setCurrentPoint(List<CustomerPointAdapter.Item> currentPoint) {

        if (mPoint != null) {

            mPoint.clear();

        }

        mPoint.addAll(currentPoint);
        mPresenter.getUsePoint(mUserId);
    }

    //사용 포인트 설정
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

    //적립 포인트 설정
    @Override
    public synchronized void setSavePoint(CustomerPointAdapter.Item savePoint, List<String> saveDate) {

        mPoint.add(savePoint);

        mPointDate.addAll(saveDate);

        setData();
    }

    //포인트 조회
    public synchronized void initPoint() {

        mPresenter.getCurrentPoint(mUserId);
    }

    //어댑터의 데이터 변경
    private void setData() {

        mAdapter.setData(mPoint, mPointDate);
    }
}