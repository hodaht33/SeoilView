 package seoil.capstone.som.ui.main.manager.statistics;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;

public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View, View.OnClickListener{

    private final int SELECTED_SALES = 0;
    private final int SELECTED_AGE = 1;
    private final int SELECTED_GENDER = 2;
    private int mFirstYear, mFirstMonth, mFirstDay;
    private int mLateYear, mLateMonth, mLateDay;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private String mStartDateQuery, mEndDateQuery;
    private String mShopId;
    private int selectedIndexMain;
    private Boolean isBtnStartDateSelected;
    private Boolean isBtnEndDateSelected;
    private ManagerStatisticsPresenter mPresenter;
    private TabLayout mTabLayoutMain;
    private RecyclerView mRecyclerViewMain;
    private ArrayList<ManagerStatisticsAdapter.Item> testData;
    private ManagerStatisticsAdapter mAdapter;
    private AnyChartView mChartView;



    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerStatisticsPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);

        isBtnEndDateSelected = false;
        isBtnEndDateSelected = false;

        selectedIndexMain = SELECTED_SALES;

        testData = new ArrayList<>();

        for (int i = 1; i < 10; i ++) {

            ManagerStatisticsAdapter.Item temp = new ManagerStatisticsAdapter.Item();
            temp.date = "2021-01-0" + String.valueOf(i);
            temp.sales = String.valueOf(2 * i);
            temp.cost = String.valueOf(3* i);
            testData.add(temp);
        }

        mAdapter = new ManagerStatisticsAdapter(testData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_statistics, container, false);

        initView(view);
        initListener();

        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("매출"), SELECTED_SALES);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("나이별 통계"), SELECTED_AGE);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("성별 통계"), SELECTED_GENDER);

        mShopId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewMain.setAdapter(mAdapter);
        mAdapter.setData(testData);

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

    void showDate(Boolean flag) {
        DatePickerDialog datePickerDialog;
        Calendar date = Calendar.getInstance();
        if (flag) {                 //시작 날짜 선택

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
                    mBtnStartDate.setText(mStartDateQuery);

                    isBtnStartDateSelected = true;
                }
            },year, month - 1, day );


            date.set(year, month - 1, day);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());

            datePickerDialog.show();
        } else {                    //종료 날짜 선택

            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    mLateDay = dayOfMonth;
                    mLateMonth = month + 1;
                    mLateYear = year;

                    mEndDateQuery = mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay);
                    mBtnEndDate.setText(mEndDateQuery);

                    isBtnEndDateSelected = true;
                }
            }, mFirstYear, mFirstMonth - 1, mFirstDay);

            date.set(mFirstYear, mFirstMonth - 1, mFirstDay);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());
            date.set(mFirstYear, mFirstMonth - 1, mFirstDay + 7);
            datePickerDialog.getDatePicker().setMaxDate(date.getTime().getTime());

            datePickerDialog.show();
        }
    }


    @Override
    public void onClick(View v) {

    }

    private void initView(View view) {

        mBtnStartDate = view.findViewById(R.id.btnMStatisticsStartDate);
        mBtnEndDate = view.findViewById(R.id.btnMStatisticsEndDate);
        mTabLayoutMain = view.findViewById(R.id.tabLayoutMStatisticsSelectView);
        mRecyclerViewMain = view.findViewById(R.id.recyclerViewMStatisticsSales);
        mChartView = view.findViewById(R.id.anyChartViewMStatistics);
    }

    private void initListener() {

        mBtnStartDate.setOnClickListener(this);
        mBtnEndDate.setOnClickListener(this);

        mTabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int id = tab.getPosition();

                if (id == SELECTED_SALES) {

                    mChartView.setVisibility(View.GONE);
                    mRecyclerViewMain.setVisibility(View.VISIBLE);
                } else if (id == SELECTED_AGE) {

                    mChartView.setVisibility(View.VISIBLE);
                    mRecyclerViewMain.setVisibility(View.GONE);
                } else if (id == SELECTED_GENDER) {

                    mChartView.setVisibility(View.VISIBLE);
                    mRecyclerViewMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void sendSalesData(ArrayList<String> listDates, ArrayList<Integer> listAmounts) {

    }

    @Override
    public void sendGenderTotal(ArrayList<String> genderList, ArrayList<Integer> countList) {


    }

    @Override
    public void sendAgeTotal(ArrayList<String> ageList, ArrayList<Integer> amountList) {


    }
}
