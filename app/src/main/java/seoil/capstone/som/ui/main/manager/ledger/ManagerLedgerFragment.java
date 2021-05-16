package seoil.capstone.som.ui.main.manager.ledger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;

public class ManagerLedgerFragment extends Fragment implements ManagerLedgerContract.View{

    private MaterialCalendarView mCalendarView;

    private ArrayList<String> mDataName;
    private ArrayList<Integer> mDataAmount;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mWidthPixels, mHeightPixels;
    private PopupWindow mPopupWindow;
    private Button mBtnClosePopup;
    private TextView mTextViewDetailedSalePopup;
    private TextView mTextViewDate;
    private ManagerLedgerPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private ManagerLedgerTextAdapter mAdapter;

    /*TODO:// getMonth() 사용시 값이 1 작게 리턴됨*/
    public ManagerLedgerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerLedgerPresenter();
        mPresenter.createInteractor();
        mPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_ledger, container, false);

        initView(view);

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        String shopId = bundle.getString("id");



        initListener(shopId);
        mCalendarView.setSelectionColor(R.color.black);


        mDataName = new ArrayList<>();
        mDataAmount = new ArrayList<>();
        mDataName.add("값이 없습니다.");
        mDataAmount.add(-1);

        return view;
    }

    private void initView(View view) {

        mCalendarView = view.findViewById(R.id.calendarViewMLedger);
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

                if (mYear == date.getYear() && mMonth == (date.getMonth() + 1) && mDay == date.getDay()) {

                    return;
                }

                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics metrics = new DisplayMetrics();
                display.getMetrics(metrics);
                mWidthPixels = metrics.widthPixels;
                mHeightPixels = metrics.heightPixels;

                try {
                    Point realSize = new Point();
                    Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                    mWidthPixels = realSize.x;
                    mHeightPixels = realSize.y;
                } catch (Exception e) {

                }

                initPopupWindow();

                setTextViewDate();

                String dateQuery;
                dateQuery = mPresenter.getDateQuery(mYear, mMonth, mDay);

                mPresenter.getSales(shopId, dateQuery);
                mPresenter.getStock(shopId);
            }
        });
    }

    @Override
    public void setSales(int value) {

        mTextViewDetailedSalePopup.setText(mPresenter.getDetailedSale(value));
    }

    @Override
    public void setSaleError(String s) {

        mTextViewDetailedSalePopup.setText(s);
    }


    @Override
    public void setLayoutAdpater(ArrayList<String> listName, ArrayList<Integer> listAmount) {

        mAdapter.setData(listName, listAmount);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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




    private void setTextViewDate() {

        CalendarDay cd = mCalendarView.getSelectedDate();
        mYear = cd.getYear();
        mMonth = cd.getMonth() + 1;
        mDay = cd.getDay();
        String dateSelected = mPresenter.getDate(cd.getDate().toString().substring(0,3));
        String text = "" + mYear + "년 " + mMonth + "월 " + mDay + "일 (" + dateSelected + ")";
        mTextViewDate.setText(text);
    }


    private void initPopupWindow() {

        try {
            //Layout 객체화
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_manager_ledger_show, (ViewGroup)getActivity().findViewById(R.id.popupMLedgerLayout));


            mRecyclerView = layout.findViewById(R.id.recyclerViewMLedgerStockAmount);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new ManagerLedgerTextAdapter(mDataName, mDataAmount);
            mRecyclerView.setAdapter(mAdapter);

            mPopupWindow = new PopupWindow(layout, mWidthPixels, mHeightPixels, true);
            mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0 , 0);
            mBtnClosePopup = layout.findViewById(R.id.btnMLedgerFinish);
            mBtnClosePopup.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    mPopupWindow.dismiss();
                }
            });

            mTextViewDetailedSalePopup = layout.findViewById(R.id.textViewMLedgerShowSalesAmount);
            mTextViewDate = layout.findViewById(R.id.textViewMLedgerDate);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}