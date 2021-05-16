package seoil.capstone.som.ui.main.manager.statistics;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import seoil.capstone.som.R;

public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View, View.OnClickListener{

    private int mFirstYear, mFirstMonth, mFirstDay;
    private int mLateYear, mLateMonth, mLateDay;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private Button mBtnSales;
    private Button mBtnCInfo;
    private String mStartDateQuery, mEndDateQuery;
    private ManagerStatisticsPresenter mPresenter;
    private List<BarEntry> mSalesData;
    private Boolean mState;
    private Boolean isBtnSet;
    private Boolean isBtnSetLate;
    private Boolean isBtnCInfo;
    private BarChart mBarChartAgeChart;
    private PieChart mPieChartGenderChart;
    private BarChart mBarChartSales;
    private int black;
    private int white;
    private int gray;
    private ImageView mImageViewSendQuery;

    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerStatisticsPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);


        mSalesData = new ArrayList<BarEntry>();
        mState = true;
        isBtnSet = false;
        isBtnSetLate = false;
        isBtnCInfo = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_statistics, container, false);

        initView(view);
        initListener();
        initColor();

        mBtnSales.setBackgroundColor(black);
        mBtnSales.setTextColor(white);
        mBtnCInfo.setBackgroundColor(white);
        mBtnCInfo.setTextColor(gray);



        for (int i = 0; i < 20; i++) {

            mSalesData.add(new BarEntry(i, i * 10));
        }

        BarDataSet barDataSet = new BarDataSet(mSalesData, "datas");

        BarData barData = new BarData(barDataSet);

        mBarChartSales.setData(barData);
        mBarChartSales.animateY(1000);
        mBarChartSales.setClickable(false);
        mBarChartSales.setDragEnabled(true);
        mBarChartSales.invalidate();
        mBarChartSales.setScaleEnabled(false);
        mBarChartSales.setVisibleXRangeMaximum(5);
//        mBarChartSales.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return;
//            }
//        });

        mBarChartAgeChart.setData(barData);
        mBarChartAgeChart.animateY(1000);
        mBarChartAgeChart.setClickable(false);
        mBarChartAgeChart.setDragEnabled(true);
        mBarChartAgeChart.setScaleEnabled(false);
        mBarChartAgeChart.setVisibleXRangeMaximum(5);

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    void showDate(Boolean flag) {
        DatePickerDialog datePickerDialog;
        Calendar date = Calendar.getInstance();
        if (flag) {

            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFirstDay = dayOfMonth;
                    mFirstMonth = month + 1;
                    mFirstYear = year;

                    mBtnStartDate.setText(mPresenter.getDateQuery(mFirstYear, mFirstMonth, mFirstDay).substring(2));

                    isBtnSet = true;
                }
            },2021, 0, 1 );


            date.set(2021, 0, 1);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());

            datePickerDialog.show();
        } else {

            if (isBtnSet) {

                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mLateDay = dayOfMonth;
                        mLateMonth = month + 1;
                        mLateYear = year;

                        mBtnEndDate.setText(mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay).substring(2));

                        isBtnSetLate = true;
                    }
                }, mFirstYear, mFirstMonth - 1, mFirstDay);

                date.set(mFirstYear, mFirstMonth - 1, mFirstDay);
                datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());

                if (!isBtnCInfo) {

                    date.set(mFirstYear, mFirstMonth - 1, mFirstDay + 7);
                    datePickerDialog.getDatePicker().setMaxDate(date.getTime().getTime());
                }

                datePickerDialog.show();
            } else {

                Toast.makeText(getContext(), "시작 날짜를 선택해주세요", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMStatisticsSales) {

            if (!mState) {

                mBarChartSales.invalidate();
                mBarChartSales.animateY(1000);
                mBarChartSales.setVisibility(View.VISIBLE);
                mBarChartAgeChart.setVisibility(View.GONE);
                mPieChartGenderChart.setVisibility(View.GONE);
                mBtnSales.setBackgroundColor(black);
                mBtnSales.setTextColor(white);
                mBtnCInfo.setBackgroundColor(white);
                mBtnCInfo.setTextColor(gray);
                mState = true;
                isBtnCInfo = false;
            }
        }

        if (viewId == R.id.btnMStatisticsCInfo) {

            if (mState) {

                mBarChartAgeChart.invalidate();
                mBarChartAgeChart.animateY(1000);
                mBarChartSales.setVisibility(View.GONE);
                mBarChartAgeChart.setVisibility(View.VISIBLE);
                mPieChartGenderChart.setVisibility(View.VISIBLE);
                mBtnCInfo.setBackgroundColor(black);
                mBtnCInfo.setTextColor(white);
                mBtnSales.setBackgroundColor(white);
                mBtnSales.setTextColor(gray);
                mState = false;
                isBtnCInfo = true;
            }

        }

        if (viewId == R.id.btnMStatisticsStartDate) {

            showDate(true);
        }

        if (viewId == R.id.btnMStatisticsEndDate) {

            showDate(false);
        }

        if (viewId == R.id.imageViewMStatisticsSelect) {

            if (!isBtnSet) {

                Toast.makeText(getContext(), "시작 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else if (!isBtnSetLate) {

                Toast.makeText(getContext(), "마지막 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else if (!isBtnCInfo && mLateDay > mFirstDay + 7) {

                Toast.makeText(getContext(), "마지막 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else if (mFirstYear > mLateYear || mFirstMonth > mLateMonth || mFirstDay > mLateDay) {

                Toast.makeText(getContext(), "마지막 날짜를 다시 선택해주세요", Toast.LENGTH_SHORT).show();
            } else {

                mStartDateQuery = mPresenter.getDateQuery(mFirstYear, mFirstMonth, mFirstDay);
                mEndDateQuery = mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay);
            }
        }
    }

    private void initView(View view) {

        mBtnSales = view.findViewById(R.id.btnMStatisticsSales);
        mBtnCInfo = view.findViewById(R.id.btnMStatisticsCInfo);
        mBtnStartDate = view.findViewById(R.id.btnMStatisticsStartDate);
        mBtnEndDate = view.findViewById(R.id.btnMStatisticsEndDate);

        mBarChartSales = view.findViewById(R.id.barChartMStatisticsSales);
        mBarChartAgeChart = view.findViewById(R.id.barChartMStatisticsAgeChart);
        mPieChartGenderChart = view.findViewById(R.id.pieChartMStatisticsGenderChart);

        mImageViewSendQuery = view.findViewById(R.id.imageViewMStatisticsSelect);
    }

    private void initListener() {

        mBtnSales.setOnClickListener(this);
        mBtnCInfo.setOnClickListener(this);
        mBtnStartDate.setOnClickListener(this);
        mBtnEndDate.setOnClickListener(this);
        mImageViewSendQuery.setOnClickListener(this);
    }


    private void initColor() {
        Activity activity = getActivity();
        black = activity.getColor(R.color.black);
        white = activity.getColor(R.color.white);
        gray = activity.getColor(R.color.gray);
    }
}