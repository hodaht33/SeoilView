package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.main.manager.ledger.Sales.ManagerLedgerSalesActivity;
import seoil.capstone.som.ui.main.manager.ledger.Sales.ManagerLedgerTextAdapter;

public class ManagerLedgerFragment extends Fragment implements ManagerLedgerContract.View{

    private MaterialCalendarView mCalendarView;

    public final int SELECTED_CALENDAR = 10;
    public final int SELECTED_STOCK = 11;
    private ArrayList<String> mDataNameStock;
    private ArrayList<String> mDataAmountStock;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int selectedIndexMain;
    private String mDateQuery;
    private String mShopId;
    private Button mBtnInsertStock;
    private ManagerLedgerPresenter mPresenter;
    private RecyclerView mRecyclerViewMain;
    private ManagerLedgerStockAdapter mAdapterStock;
    private TabLayout mTabLayoutMain;
    private AlertDialog mAlertDialogInsert;

    /*TODO:// getMonth() 사용시 값이 1 작게 리턴됨*/
    public ManagerLedgerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerLedgerPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);

        mDataNameStock = new ArrayList<>();
        mDataAmountStock = new ArrayList<>();

        selectedIndexMain = SELECTED_CALENDAR;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_ledger, container, false);

        initView(view);

        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("가계부"), 0);
        mTabLayoutMain.addTab(mTabLayoutMain.newTab().setText("재고"), 1);

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mShopId = bundle.getString("id");

        initListener(mShopId);
        mCalendarView.setSelectionColor(R.color.black);

        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapterStock = new ManagerLedgerStockAdapter(mDataNameStock, mDataAmountStock, mPresenter, mShopId, getContext());
        mRecyclerViewMain.setAdapter(mAdapterStock);


        return view;
    }

    private void initView(View view) {

        mCalendarView = view.findViewById(R.id.calendarViewMLedger);
        mTabLayoutMain = view.findViewById(R.id.tabLayoutMLedgerTopView);
        mRecyclerViewMain = view.findViewById(R.id.recyclerViewMLedgerStockAmountMain);
        mBtnInsertStock = view.findViewById(R.id.btnMLedgerInsertStock);
    }

    private String setDate() {

        CalendarDay cd = mCalendarView.getSelectedDate();
        mYear = cd.getYear();
        mMonth = cd.getMonth() + 1;
        mDay = cd.getDay();
        String dateSelected = mPresenter.getDate(cd.getDate().toString().substring(0,3));
        String text = "" + mYear + "년 " + mMonth + "월 " + mDay + "일 (" + dateSelected + ")";
        return text;
    }

    private void intentSales(Intent intent) {

        this.startActivityForResult(intent, 0);
    }

    private void initListener(String shopId) {

        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2021, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new WeekDecorator(),
                new TodayDecorator()
        );

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                String temp = setDate();

                mDateQuery = mPresenter.getDateQuery(mYear, mMonth, mDay);

                Intent intent = new Intent(getActivity(), ManagerLedgerSalesActivity.class);
                intent.putExtra("dateQuery", mDateQuery);
                intent.putExtra("id", mShopId);
                intent.putExtra("date", temp);

                intentSales(intent);
            }
        });

        mTabLayoutMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {

                    mCalendarView.setVisibility(View.VISIBLE);
                    selectedIndexMain = SELECTED_CALENDAR;
                    mRecyclerViewMain.setVisibility(View.GONE);
                    mBtnInsertStock.setVisibility(View.GONE);
                } else if (position == 1) {

                    mCalendarView.setVisibility(View.GONE);
                    selectedIndexMain = SELECTED_STOCK;
                    if (mAdapterStock != null) {

                        mAdapterStock.clear();
                    }
                    mPresenter.getStock(mShopId);
                    mRecyclerViewMain.setVisibility(View.VISIBLE);
                    mBtnInsertStock.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mBtnInsertStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAlertDialogInsert != null) {

                    mAlertDialogInsert.dismiss();
                }
                //다이얼로그로 데이터 추가창 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_manager_ledger_insert, null, false);

                builder.setView(view);

                Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitDialogStock);
                TextView textViewName = view.findViewById(R.id.textViewMLedgerNameDialogStock);
                EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountDialogStock);

                btnSubmit.setText("삽입");

                mAlertDialogInsert = builder.create();
                mAlertDialogInsert.show();

                btnSubmit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        String name = textViewName.getText().toString();
                        String amount = editTextAmount.getText().toString();

                        if (mPresenter.isTextSet(amount) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextAmount.setError("11자 이하로 입력해주세요");
                            editTextAmount.requestFocus();
                        } else if (!mPresenter.isNumeric(amount)) {

                            editTextAmount.setError("숫자만 입력해주세요");
                            editTextAmount.requestFocus();
                        } else {

                            if (selectedIndexMain == SELECTED_STOCK) {

                                mPresenter.insertStock(mShopId, name, Integer.parseInt(amount));
                            }

                            mAlertDialogInsert.dismiss();
                        }

                    }
                });
            }
        });
    }

    @Override
    public void setLayoutAdapterStock(ArrayList<String> listName, ArrayList<String> listAmount) {

        mAdapterStock.setData(listName, listAmount);
    }


    @Override
    public void initStock() {

        mPresenter.getStock(mShopId);
    }


    @Override
    public void showProgress() {

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgress() {

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.red)));
        }
    }

    class SaturdayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.blue)));
        }
    }


    class WeekDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public WeekDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {

            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return (weekDay == Calendar.MONDAY) || (weekDay == Calendar.TUESDAY) || (weekDay == Calendar.WEDNESDAY)
                    || (weekDay == Calendar.THURSDAY) || (weekDay == Calendar.FRIDAY);
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.addSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.black)));
        }
    }

    class TodayDecorator implements DayViewDecorator {

        private CalendarDay date;

        public TodayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {

            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.GREEN));
        }

        public void setDate(Date date) {

            this.date = CalendarDay.from(date);
        }
    }

}