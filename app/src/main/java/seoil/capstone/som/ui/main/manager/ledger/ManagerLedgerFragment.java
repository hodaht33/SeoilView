package seoil.capstone.som.ui.main.manager.ledger;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

import seoil.capstone.som.R;

public class ManagerLedgerFragment extends Fragment {

    private MaterialCalendarView mCalendarView;
    private TextView mTextViewDate;
    private TextView mTextViewDetailedSale;
    private int mYear;
    private int mMonth;
    private int mDay;

    /*TODO:// getMonth() 사용시 값이 1 작게 리턴됨*/
    public ManagerLedgerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_ledger, container, false);

        initView(view);
        initListener();

        CalendarDay cd = mCalendarView.getSelectedDate();
        mYear = cd.getYear();
        mMonth = cd.getMonth() + 1;
        mDay = cd.getDay();
        String dateSelected = getDate(cd.getDate().toString().substring(0,3));
        String text = "" + mYear + "년 " + mMonth + "월 " + mDay + "일 (" + dateSelected + ")";
        mTextViewDate.setText(text);

        return view;
    }

    private void initView(View view) {

        mCalendarView = view.findViewById(R.id.calendarViewMLedger);
        mTextViewDate = view.findViewById(R.id.textViewMLedgeDate);
        mTextViewDetailedSale = view.findViewById(R.id.textViewMLedgeDetailedSale);
    }

    private void initListener() {

        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2021, 0, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new TodayDecorator()
        );

        mCalendarView.setSelectedDate(CalendarDay.today());

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                CalendarDay cd = mCalendarView.getSelectedDate();
                mYear = cd.getYear();
                mMonth = cd.getMonth() + 1;
                mDay = cd.getDay();
                String dateSelected = getDate(cd.getDate().toString().substring(0,3));
                String text = "" + mYear + "년 " + mMonth + "월 " + mDay + "일 (" + dateSelected + ")";
                mTextViewDate.setText(text);


                //여기에 매출 조회 입력
            }
        });
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

            view.addSpan(new ForegroundColorSpan(Color.RED));
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

            view.addSpan(new ForegroundColorSpan(Color.BLUE));
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
            view.addSpan(new BackgroundColorSpan(Color.WHITE));
        }

        public void setDate(Date date) {

            this.date = CalendarDay.from(date);
        }
    }

    private String getDate(String date) {

        if (date.equals("Sun")) {

            return "일";
        } else if (date.equals("Mon")) {

            return "월";
        } else if (date.equals("Tue")) {

            return "화";
        } else if (date.equals("Wed")) {

            return "수";
        } else if (date.equals("Thu")) {

            return "목";
        } else if (date.equals("Fri")) {

            return "금";
        } else if (date.equals("Sat")) {

            return "토";
        } else {

            return "Error";
        }
    }
}