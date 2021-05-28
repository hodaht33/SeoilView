 package seoil.capstone.som.ui.main.manager.statistics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;

public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View, View.OnClickListener{

    private int mFirstYear, mFirstMonth, mFirstDay;
    private int mLateYear, mLateMonth, mLateDay;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private Button mBtnSales;
    private Button mBtnCInfo;
    private String mStartDateQuery, mEndDateQuery;
    private String mShopId;
    private ManagerStatisticsPresenter mPresenter;
    private List<PieEntry> mGenderData;
    private List<PieEntry> mAgeData;
    private Boolean mState;
    private Boolean isBtnSet;
    private Boolean isBtnSetLate;
    private Boolean isBtnCInfo;
    private PieChart mPieChartAgeChart;
    private PieChart mPieChartGenderChart;
    private int black;
    private int white;
    private int gray;
    private int red;
    private int blue;
    private int lightGreen;
    private int purple;
    private int pink;
    private ImageView mImageViewSendQuery;
    private int[] mColorArrayGender;
    private int[] mColorArrayAge;

    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerStatisticsPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);


        mAgeData = new ArrayList<>();
        mGenderData = new ArrayList<>();
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

        mColorArrayGender = new int[]{red, blue};
        mColorArrayAge = new int[]{red, black, gray, purple, lightGreen, pink};

        initGenderChart();
        initAgeChart();

        mBtnSales.setBackgroundColor(black);
        mBtnSales.setTextColor(white);
        mBtnCInfo.setBackgroundColor(white);
        mBtnCInfo.setTextColor(gray);

        mShopId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

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

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String getTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            int year, month, day;
            year = Integer.parseInt(getTime.substring(0,4));
            month = Integer.parseInt(getTime.substring(5,7));
            day = Integer.parseInt(getTime.substring(8));

            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFirstDay = dayOfMonth;
                    mFirstMonth = month + 1;
                    mFirstYear = year;

                    mStartDateQuery = mPresenter.getDateQuery(mFirstYear, mFirstMonth, mFirstDay);
                    mBtnStartDate.setText(mStartDateQuery.substring(2));

                    isBtnSet = true;
                }
            },year, month - 1, day );


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

                        mEndDateQuery = mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay);
                        mBtnEndDate.setText(mEndDateQuery.substring(2));

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


                mPieChartAgeChart.setVisibility(View.GONE);
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

                mPieChartAgeChart.setVisibility(View.VISIBLE);
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

                if (!mState) {

                    mPresenter.getGenderTotal(mShopId, mStartDateQuery, mEndDateQuery);
                    mPresenter.getAgeTotal(mShopId, mStartDateQuery, mEndDateQuery);
                }
            }
        }
    }

    private void initView(View view) {

        mBtnSales = view.findViewById(R.id.btnMStatisticsSales);
        mBtnCInfo = view.findViewById(R.id.btnMStatisticsCInfo);
        mBtnStartDate = view.findViewById(R.id.btnMStatisticsStartDate);
        mBtnEndDate = view.findViewById(R.id.btnMStatisticsEndDate);

        mPieChartAgeChart = view.findViewById(R.id.pieChartMStatisticsAgeChart);
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
        red = activity.getColor(R.color.red);
        blue = activity.getColor(R.color.blue);
        lightGreen = activity.getColor(R.color.light_green);
        purple = activity.getColor(R.color.purple_200);
        pink = activity.getColor(R.color.pink);
    }

    @Override
    public void sendSalesData(ArrayList<String> listDates, ArrayList<Integer> listAmounts) {

    }

    @Override
    public void sendGenderTotal(ArrayList<String> genderList, ArrayList<Integer> countList) {


        mGenderData = new ArrayList<>();
        for (int i = 0; i < genderList.size(); i++) {

            mGenderData.add(new PieEntry(countList.get(i), genderList.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(mGenderData, "");
        dataSet.setColors(mColorArrayGender);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value) + "%";
            }
        });

        PieData pieData =  new PieData(dataSet);
        pieData.setValueTextSize(20f);
        pieData.setValueTextColor(white);

        mPieChartGenderChart.setData(pieData);
        mPieChartGenderChart.animateXY(1000, 1000);
        mPieChartGenderChart.invalidate();
    }

    @Override
    public void sendAgeTotal(ArrayList<String> ageList, ArrayList<Integer> amountList) {

        mAgeData = new ArrayList<>();
        for(int i = 0; i < ageList.size(); i++) {
            mAgeData.add(new PieEntry(amountList.get(i), ageList.get(i)));
        }

        PieDataSet dataSet = new PieDataSet(mAgeData, "");
        dataSet.setColors(mColorArrayAge);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Math.round(value) + "%";
            }
        });

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(15f);
        pieData.setValueTextColor(white);

        mPieChartAgeChart.setData(pieData);
        mPieChartAgeChart.animateXY(1000, 1000);
        mPieChartAgeChart.invalidate();
    }

    public void initGenderChart() {

        mPieChartGenderChart.animateXY(1000, 1000);
        mPieChartGenderChart.setRotationEnabled(false);
        mPieChartGenderChart.setDrawEntryLabels(false);
        mPieChartGenderChart.setDrawHoleEnabled(false);
        mPieChartGenderChart.setUsePercentValues(true);
        mPieChartGenderChart.setDescription(null);
        mPieChartGenderChart.invalidate();
    }

    public void initAgeChart() {

        mPieChartAgeChart.animateXY(1000, 1000);
        mPieChartAgeChart.setRotationEnabled(false);
        mPieChartAgeChart.setDrawEntryLabels(false);
        mPieChartAgeChart.setDrawHoleEnabled(false);
        mPieChartAgeChart.setUsePercentValues(true);
        mPieChartAgeChart.setDescription(null);
        mPieChartAgeChart.invalidate();
    }
}
