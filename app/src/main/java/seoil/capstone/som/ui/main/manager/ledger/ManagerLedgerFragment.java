package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesInfo;

public class ManagerLedgerFragment extends Fragment {

    private MaterialCalendarView mCalendarView;

    private int mYear;
    private int mMonth;
    private int mDay;
    private String mShopCode;
    private int mWidthPixels, mHeightPixels;
    private PopupWindow mPopupWindow;
    private Button mBtnClosePopup;
    private TextView mTextViewDetailedSalePopup;
    private TextView mTextViewStockPopup;
    private TextView mTextViewDate;

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

        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        String shopId = bundle.getString("id");

        Log.d("shopId", shopId);
//        사업자 번호 불러오기
//        OnFinishApiListener<ShopInfo.GetRes> onFinishApiListener = new OnFinishApiListener<ShopInfo.GetRes>() {
//            @Override
//            public void onSuccess(ShopInfo.GetRes getRes) {
//
//                List<ShopInfo.GetRes.Result> list = getRes.getResults();
//
//                for (ShopInfo.GetRes.Result result : list) {
//
//                    mShopCode = result.getShopCode();
//                }
//                Log.d("shopcode",mShopCode);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//                Log.d("test", t.toString());
//            }
//        };
//
//        AppApiHelper.getInstance().getShopInfo(shopId, onFinishApiListener);



        initListener(shopId);
        mCalendarView.setSelectionColor(R.color.black);


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

                OnFinishApiListener<SalesInfo.GetRes> onFinishApiListener = new OnFinishApiListener<SalesInfo.GetRes>() {
                    @Override
                    public void onSuccess(SalesInfo.GetRes getRes) {

                        if (getRes.getStatus() == SalesApi.SUCCESS) {

                            List<SalesInfo.GetRes.Result> list = getRes.getResults();

                            int sum = 0;
                            for (SalesInfo.GetRes.Result result : list) {

                                sum += result.getSalesAmount();
                            }
                            setTextViewDetailedSale(sum);
                        } else {

                            mTextViewDetailedSalePopup.setText("값이 없습니다.");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                String dateQeury;
                if (mDay < 10) {

                    if (mMonth < 10) {

                        dateQeury = "" + mYear + "-0" + mMonth + "-0" + mDay;
                    } else {

                        dateQeury = "" + mYear + "-" + mMonth + "-0" + mDay;
                    }

                } else {

                    if (mMonth < 10) {

                        dateQeury = "" + mYear + "-0" + mMonth + "-" + mDay;
                    } else {

                        dateQeury = "" + mYear + "-" + mMonth + "-" + mDay;
                    }
                }
                // date는 null을 가질 수 있음(단, null일 경우 shopId의 모든 날짜에 발생한 매출 데이터를 받아오는 것)
                // date형식은 YYYY-MM-DD로 할 것 (ex: 2021-05-03)
                AppApiHelper.getInstance().getSalesInfo(shopId, dateQeury, onFinishApiListener);
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

    private void setTextViewDate() {

        CalendarDay cd = mCalendarView.getSelectedDate();
        mYear = cd.getYear();
        mMonth = cd.getMonth() + 1;
        mDay = cd.getDay();
        String dateSelected = getDate(cd.getDate().toString().substring(0,3));
        String text = "" + mYear + "년 " + mMonth + "월 " + mDay + "일 (" + dateSelected + ")";
        mTextViewDate.setText(text);
    }

    private void setTextViewDetailedSale (int value) {

        StringBuffer temp = new StringBuffer(String.valueOf(value));

        for(int i = temp.length() - 3; i > 0; i -= 3) {

            temp.insert(i,",");
        }
        temp.append("원");

        mTextViewDetailedSalePopup.setText(temp);
    }

    private void initPopupWindow() {

        try {
            //Layout 객체화
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_manager_ledger_show, (ViewGroup)getActivity().findViewById(R.id.popupMLedgerLayout));

            mPopupWindow = new PopupWindow(layout, mWidthPixels - 100, mHeightPixels - 500, true);
            mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0 , 0);
            mBtnClosePopup = layout.findViewById(R.id.btnMLedgerFinish);
            mBtnClosePopup.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mPopupWindow.dismiss();
                }
            });

            mTextViewDetailedSalePopup = layout.findViewById(R.id.textViewMLedgerShowSalesAmount);
            mTextViewStockPopup = layout.findViewById(R.id.textViewMLedgerStockAmount);
            mTextViewDate = layout.findViewById(R.id.textViewMLedgerDate);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}