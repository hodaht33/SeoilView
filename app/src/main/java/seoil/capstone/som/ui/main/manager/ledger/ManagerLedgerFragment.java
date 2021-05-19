package seoil.capstone.som.ui.main.manager.ledger;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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

public class ManagerLedgerFragment extends Fragment implements ManagerLedgerContract.View{

    private MaterialCalendarView mCalendarView;

    public final int SELECTED_SALE = 0;
    public final int SELECTED_COST = 1;
    public final int SELECTED_STOCK = 2;
    private ArrayList<String> mDataName;
    private ArrayList<String> mDataAmount;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mWidthPixels, mHeightPixels;
    private int selectedIndex;
    private String mDateQuery;
    private String mShopId;
    private PopupWindow mPopupWindow;
    private Button mBtnClosePopup;
    private TextView mTextViewDate;
    private TextView mTextViewTitle;
    private ManagerLedgerPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private ManagerLedgerTextAdapter mAdapter;
    private TabLayout mTabLayout;
    private ImageView mImageViewAdd;
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

        selectedIndex = SELECTED_SALE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manager_ledger, container, false);

        initView(view);


        Bundle bundle = getActivity().getIntent().getBundleExtra("data");
        mShopId = bundle.getString("id");



        initListener(mShopId);
        mCalendarView.setSelectionColor(R.color.black);


        mDataName = new ArrayList<>();
        mDataAmount = new ArrayList<>();

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

                mDateQuery = mPresenter.getDateQuery(mYear, mMonth, mDay);

                mPresenter.getSales(shopId, mDateQuery);
            }
        });

    }


    @Override
    public void setLayoutAdapter(ArrayList<String> listName, ArrayList<String> listAmount) {



        mAdapter.setData(listName, listAmount);
    }

    @Override
    public void initCost() {

        mPresenter.getCost(mShopId, mDateQuery);
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

            if (mPopupWindow != null) {

                mPopupWindow.dismiss();
            }

            //Layout 객체화
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View layout = inflater.inflate(R.layout.popup_manager_ledger_show, getActivity().findViewById(R.id.popupMLedgerLayout));

            initViewPopup(layout);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new ManagerLedgerTextAdapter(mDataName, mDataAmount);
            mRecyclerView.setAdapter(mAdapter);

            mPopupWindow = new PopupWindow(layout, mWidthPixels, mHeightPixels, true);
            mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0 , 0);

            mTabLayout.addTab(mTabLayout.newTab().setText("매출"), SELECTED_SALE);
            mTabLayout.addTab(mTabLayout.newTab().setText("지출"), SELECTED_COST);
            mTabLayout.addTab(mTabLayout.newTab().setText("재고"), SELECTED_STOCK);

            initListenerPopup();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void initListenerPopup() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();


                if (position == SELECTED_SALE) {

                    mImageViewAdd.setVisibility(View.INVISIBLE);
                    selectedIndex = SELECTED_SALE;
                    mTextViewTitle.setText("매출");
                    mAdapter.clear();
                    mPresenter.getSales(mShopId, mDateQuery);
                } else if (position == SELECTED_COST) {

                    mImageViewAdd.setVisibility(View.VISIBLE);
                    selectedIndex = SELECTED_COST;
                    mTextViewTitle.setText("지출");
                    mAdapter.clear();
                    mPresenter.getCost(mShopId, mDateQuery);
                } else if (position == SELECTED_STOCK) {

                    mImageViewAdd.setVisibility(View.VISIBLE);
                    selectedIndex = SELECTED_STOCK;
                    mTextViewTitle.setText("재고");
                    mAdapter.clear();
                    mPresenter.getStock(mShopId);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mBtnClosePopup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mPopupWindow.dismiss();
            }
        });

        mImageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAlertDialogInsert != null) {
                    mAlertDialogInsert.dismiss();
                }
                //다이얼로그로 데이터 추가창 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_manager_ledger_insert, null, false);

                builder.setView(view);

                Button btnSubmit = view.findViewById(R.id.btnMLedgerSubmitDialog);
                EditText editTextName = view.findViewById(R.id.editTextMLedgerNameDialog);
                EditText editTextAmount = view.findViewById(R.id.editTextMLedgerAmountDialog);

                btnSubmit.setText("삽입");

                mAlertDialogInsert = builder.create();
                mAlertDialogInsert.show();

                btnSubmit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        String name = editTextName.getText().toString();
                        String amount = editTextAmount.getText().toString();

                        if (mPresenter.isTextSet(name) != mPresenter.TEXT_LENGTH_INVALID) {
                            editTextName.setError("11자 이하로 입력해주세요");
                            editTextName.requestFocus();
                            return;
                        } else if (mPresenter.isTextSet(amount) != mPresenter.TEXT_LENGTH_INVALID) {

                            editTextAmount.setError("11자 이하로 입력해주세요");
                            editTextAmount.requestFocus();
                        } else if (!mPresenter.isNumeric(amount)) {

                            editTextAmount.setError("숫자만 입력해주세요");
                            editTextAmount.requestFocus();
                        } else {

                            if (selectedIndex == SELECTED_COST) {

                                mPresenter.insertSalesWithDate(mShopId, name, Integer.parseInt(amount) * -1, mDateQuery);
                            } else if (selectedIndex == SELECTED_STOCK) {

                                mPresenter.setStock(mShopId, name, Integer.parseInt(amount));
                            }

                            mAlertDialogInsert.dismiss();
                        }

                    }
                });
            }
        });
    }

    private void initViewPopup(View layout) {

        mRecyclerView = layout.findViewById(R.id.recyclerViewMLedgerStockAmount);
        mBtnClosePopup = layout.findViewById(R.id.btnMLedgerFinish);
        mTabLayout = layout.findViewById(R.id.tabLayoutMLedgerTop);
        mTextViewDate = layout.findViewById(R.id.textViewMLedgerDate);
        mImageViewAdd = layout.findViewById(R.id.imageViewMLedgerInsert);
        mTextViewTitle = layout.findViewById(R.id.textViewMLedgerShowTitle);
    }
}