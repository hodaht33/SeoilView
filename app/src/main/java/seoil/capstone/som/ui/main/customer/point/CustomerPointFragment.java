package seoil.capstone.som.ui.main.customer.point;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.R;

import static android.widget.Toast.LENGTH_LONG;


public class CustomerPointFragment extends Fragment implements CustomerPointContract.View, View.OnClickListener {


    private RecyclerView mRecyclerView;
    private List<CustomerPointAdapter.Item> mPoint;

    private CustomerPointAdapter mAdapter;
    private CustomerPointPresenter mPresenter;
    private String mUserId;
    private List<String> mPointDate;

    private Animation mFabopen, mFabclose;
    private boolean IsFaboepn = false;
    private FloatingActionButton mFloatingActionSetting, mFloatingActingSave, mFloatingActingUse;

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


        //mRecyclerView = view.findViewById(R.id.recyclerViewPointInformation);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mFabopen = AnimationUtils.loadAnimation(getContext(),R.anim.fabopen);
        mFabclose = AnimationUtils.loadAnimation(getContext(), R.anim.fabclose);

        //mFloatingActionSetting = view.findViewById(R.id.floatingActionButtonSettingPoint);
        //mFloatingActingSave = view.findViewById(R.id.floatingActionButtonSavingPoint);
        //mFloatingActingUse = view.findViewById(R.id.floatingActionButtonUsingPoint);

        mFloatingActionSetting.setOnClickListener(this);
        mFloatingActingSave.setOnClickListener(this);
        mFloatingActingUse.setOnClickListener(this);

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
        int id = v.getId();
//        switch (id){
//            case R.id.floatingActionButtonSettingPoint:
//                anim();
//                break;
//            case R.id.floatingActionButtonSavingPoint:
//                anim();
//                Toast.makeText(getActivity(),"SAVING",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.floatingActionButtonUsingPoint:
//                anim();
//                Toast.makeText(getActivity(),"USING",Toast.LENGTH_SHORT).show();
//                break;
//
//        }

    }
    public void anim() {

        if (IsFaboepn) {
            mFloatingActingSave.startAnimation(mFabclose);
            mFloatingActingUse.startAnimation(mFabclose);
            mFloatingActingSave.setClickable(false);
            mFloatingActingUse.setClickable(false);
            IsFaboepn = false;
        } else {
            mFloatingActingSave.startAnimation(mFabopen);
            mFloatingActingUse.startAnimation(mFabopen);
            mFloatingActingSave.setClickable(true);
            mFloatingActingUse.setClickable(true);
            IsFaboepn = true;
        }
    }

    private void initView(View view) {

        //mRecyclerView = view.findViewById(R.id.recyclerViewPointInformation);
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

        mAdapter = new CustomerPointAdapter(mPoint, getContext(), mPointDate);
        mRecyclerView.setAdapter(mAdapter);
    }

}
