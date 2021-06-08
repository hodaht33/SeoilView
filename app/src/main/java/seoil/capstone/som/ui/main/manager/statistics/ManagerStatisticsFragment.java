 package seoil.capstone.som.ui.main.manager.statistics;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.util.Utility;

 public class ManagerStatisticsFragment extends Fragment implements ManagerStatisticsContract.View, View.OnClickListener{
     
    //탭 구분 
    private final int SELECTED_SALES = 0;
    private final int SELECTED_AGE = 1;
    private final int SELECTED_GENDER = 2;

    //선택 날짜 저장
    private int mFirstYear, mFirstMonth, mFirstDay;
    private int mLateYear, mLateMonth, mLateDay;
    private String mStartDateQuery, mEndDateQuery;

    private Button mBtnStartDate;                                   //시작 날짜 선택 버튼
    private Button mBtnEndDate;                                     //종료 날짜 선택 버튼

    private String mShopId;                                         //점주의 아이디
    private Boolean isBtnStartDateSelected;                         //시작 날짜 선택 확인 변수
    private Boolean isBtnEndDateSelected;                           //종료 날짜 선택 확인 변수
    private ManagerStatisticsPresenter mPresenter;                  //점주 통계 프레그먼트의 프레젠터
    private TabLayout mTabLayoutMain;                               //점주 통계 프레그먼트의 탭
    private RecyclerView mRecyclerViewMain;                         //점주 통계 프레그먼트의 매출 리사이클러뷰
    private ArrayList<ManagerStatisticsAdapter.Item> mSalesData;    //매출 통계 정보
    private ManagerStatisticsAdapter mAdapter;                      //점주 통계 프레그먼트의 리사이클러뷰의 어댑터
    private AnyChartView mChartView;                                //차트를 보여줄 뷰
    private Pie mPieChart;                                          //차트 정보
    private Dialog mDialog;                                         //확인창
    private ImageView mImageViewDate;                               //조회 버튼
    private ArrayList<DataEntry> mAgeStatistics;                    //나이대 별 통계 정보
    private ArrayList<DataEntry> mGenderStatistics;                 //성별 통계 정보



    public ManagerStatisticsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerStatisticsPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);

        isBtnStartDateSelected = false;
        isBtnEndDateSelected = false;

        mSalesData = new ArrayList<>();

        mAgeStatistics = new ArrayList<>();
        mGenderStatistics = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_statistics, container, false);

        initView(view);
        initListener();

        mPieChart = makePieChart();
        mChartView.setChart(mPieChart);

        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("매출"), SELECTED_SALES);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("나이"), SELECTED_AGE);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("성별"), SELECTED_GENDER);

        mShopId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ManagerStatisticsAdapter(mSalesData);
        mRecyclerViewMain.setAdapter(mAdapter);

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

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (mDialog != null) {

                    mDialog = null;
                }
            }
        };

        if (mDialog == null) {

            Utility.getInstance().showDialog(mDialog, msg, getContext(), onClickListener);
        }
    }

    //날짜 선택
    private void showDate(Boolean flag) {

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
                    isBtnEndDateSelected = false;
                    mBtnEndDate.setText("종료 날짜");
                    mLateYear = -1;
                    mLateMonth = -1;
                    mLateDay = -1;
                    mEndDateQuery = "";
                }
            },year, month - 1, day );

            date.set(2021, 0, 0);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());
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
        }

        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMStatisticsStartDate) {       //시작 날짜 선택 버튼

            showDate(true);
        }

        if (viewId == R.id.btnMStatisticsEndDate) {         //종료 날짜 선택 버튼

            if (!isBtnStartDateSelected) {

                showDialog("시작 날짜를 선택해주세요.");
            } else {

                showDate(false);
            }
        }

        if (viewId == R.id.imageViewMStatisticsSelect) {    //조회 이미지 버튼

            if (!isBtnStartDateSelected) {                  //시작 날짜가 선택되지 않을 경우

                showDialog("시작 날짜를 선택해주세요.");
            } else if (!isBtnEndDateSelected) {       //종료 날짜가 선택 되지 않았을 때

                showDialog("종료 날짜를 선택해주세요.");
            } else {//날짜 선택 완료시 데이터 조회

                mPresenter.getDailySales(mShopId, mStartDateQuery, mEndDateQuery);
                mPresenter.getGenderTotal(mShopId, mStartDateQuery, mEndDateQuery);
                mPresenter.getAgeTotal(mShopId, mStartDateQuery, mEndDateQuery);
            }
        }
    }

    private void initView(View view) {

        mBtnStartDate = view.findViewById(R.id.btnMStatisticsStartDate);
        mBtnEndDate = view.findViewById(R.id.btnMStatisticsEndDate);
        mTabLayoutMain = view.findViewById(R.id.tabLayoutMStatisticsSelectView);
        mRecyclerViewMain = view.findViewById(R.id.recyclerViewMStatisticsSales);
        mChartView = view.findViewById(R.id.anyChartViewMStatistics);
        mImageViewDate = view.findViewById(R.id.imageViewMStatisticsSelect);
    }

    private void initListener() {

        mBtnStartDate.setOnClickListener(this);
        mBtnEndDate.setOnClickListener(this);
        mImageViewDate.setOnClickListener(this);

        mTabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int id = tab.getPosition();

                if (id == SELECTED_SALES) {             //현재 선택된 탭이 매출일 때

                    mChartView.setVisibility(View.GONE);
                    mRecyclerViewMain.setVisibility(View.VISIBLE);
                } else if (id == SELECTED_AGE) {        //현재 선택된 탭이 나이대 별 통계일 때

                    mChartView.setVisibility(View.VISIBLE);
                    mRecyclerViewMain.setVisibility(View.GONE);
                    mPieChart.title("나이 통계");
                    mPieChart.data(mAgeStatistics);
                } else if (id == SELECTED_GENDER) {     //현재 선택된 탭이 성별 통계일 때

                    mChartView.setVisibility(View.VISIBLE);
                    mRecyclerViewMain.setVisibility(View.GONE);
                    mPieChart.title("성별 통계");
                    mPieChart.data(mGenderStatistics);
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
    
    private Pie makePieChart() {        //차트 생성 및 초기화

        Pie pie = AnyChart.pie();

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("분류")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        return pie;
    }
    
    //프레젠터에서 성별 통계 전달
     @Override
     public void setGenderChart(ArrayList<DataEntry> genderData) {

        mGenderStatistics = genderData;
     }

     //프레젠터에서 나이대 별 통계 전달
     @Override
     public void setAgeChart(ArrayList<DataEntry> ageData) {

        mAgeStatistics = ageData;
     }

     //프레젠터에서 일별 통계 전달
     @Override
     public void setAdapterDaily(ArrayList<ManagerStatisticsAdapter.Item> salesData) {

        mSalesData = salesData;
        mAdapter.setData(salesData);
     }
 }
