package seoil.capstone.som.ui.main.manager.statistics;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;


import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

import seoil.capstone.som.R;

public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View, View.OnClickListener{

    private int mYear, mMonth, mDay;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private Button mBtnSales;
    private Button mBtnCInfo;
    private String mStartDateQuery, mEndDateQuery;
    private ManagerStatisticsPresenter mPresenter;
    private LineChart mLineChartSales;
    private ArrayList<Integer> mSalesData;
    private Boolean mState;

    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerStatisticsPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);

        mLineChartSales.setVisibility(View.VISIBLE);
        mBtnSales.setBackgroundColor(Color.BLACK);
        mBtnSales.setTextColor(Color.WHITE);
        mBtnCInfo.setBackgroundColor(Color.WHITE);
        mBtnCInfo.setTextColor(Color.GRAY);

        mSalesData = new ArrayList<>();
        mState = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_statistics, container, false);

        initView(view);
        initListener();

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    void showDate(Boolean flag) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mDay = dayOfMonth;
                mMonth = month + 1;
                mYear = year;
                if(flag) {

                    mStartDateQuery = mPresenter.getDateQuery(mYear, mMonth, mDay);
                    mBtnStartDate.setText(mStartDateQuery);
                } else {

                    mEndDateQuery = mPresenter.getDateQuery(mYear, mMonth, mDay);
                    mBtnEndDate.setText(mEndDateQuery);
                }
            }
        },2021, 0, 1);

        datePickerDialog.show();
    }


    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMStatisticsSales) {

            if (!mState) {

                mLineChartSales.setVisibility(View.VISIBLE);
                mBtnSales.setBackgroundColor(Color.BLACK);
                mBtnSales.setTextColor(Color.WHITE);
                mBtnCInfo.setBackgroundColor(Color.WHITE);
                mBtnCInfo.setTextColor(Color.GRAY);
                mState = true;
            }
        }

        if (viewId == R.id.btnMStatisticsCInfo) {

            if (mState) {

                mLineChartSales.setVisibility(View.GONE);
                mBtnCInfo.setBackgroundColor(Color.BLACK);
                mBtnCInfo.setTextColor(Color.WHITE);
                mBtnSales.setBackgroundColor(Color.WHITE);
                mBtnSales.setTextColor(Color.GRAY);
                mState = false;
            }

        }

        if (viewId == R.id.btnMStatisticsStartDate) {

            showDate(true);
        }

        if (viewId == R.id.btnMStatisticsEndDate) {

            showDate(false);
        }
    }

    private void initView(View view) {

        mBtnSales = view.findViewById(R.id.btnMStatisticsSales);
        mBtnCInfo = view.findViewById(R.id.btnMStatisticsCInfo);
        mBtnStartDate = view.findViewById(R.id.btnMStatisticsStartDate);
        mBtnEndDate = view.findViewById(R.id.btnMStatisticsEndDate);

        mLineChartSales = view.findViewById(R.id.lineChartMStatisticsSales);
    }

    private void initListener() {

        mBtnSales.setOnClickListener(this);
        mBtnCInfo.setOnClickListener(this);
        mBtnStartDate.setOnClickListener(this);
        mBtnEndDate.setOnClickListener(this);
    }
}