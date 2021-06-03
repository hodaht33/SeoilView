package seoil.capstone.som.ui.event.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;

public class DetailEventActivity extends AppCompatActivity implements DetailEventContract.View, View.OnClickListener{

    private DetailEventPresenter mPresenter;
    private int mFirstYear, mFirstMonth, mFirstDay;
    private int mLateYear, mLateMonth, mLateDay;
    private int mEventCode;
    private TextView mTextViewMarketName;
    private TextView mTextViewMarketCategory;
    private TextView mTextViewMarketLocation;
    private EditText mEditTextEventName;
    private EditText mEditTextEventContent;
    private TextView mTextViewStartDate;
    private TextView mTextViewEndDate;
    private Button mBtnRemove;
    private Button mBtnUpdate;
    private String mUserCode;
    private String mStartDateQuery;
    private String mEndDateQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        mPresenter = new DetailEventPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        initView();
        initListener();

        mEventCode = getIntent().getIntExtra("eventCode", -1);
        mUserCode = ((GlobalApplication) getApplicationContext()).getUserCode();

        if (mUserCode.equals("C")) {

            mBtnUpdate.setVisibility(View.GONE);
            mBtnRemove.setVisibility(View.GONE);
            mBtnUpdate.setEnabled(false);
            mBtnRemove.setEnabled(false);
            mEditTextEventName.setInputType(InputType.TYPE_NULL);
            mEditTextEventContent.setInputType(InputType.TYPE_NULL);
            mTextViewStartDate.setClickable(false);
            mTextViewEndDate.setClickable(false);
        } else if (mUserCode.equals("M")){

            mBtnUpdate.setVisibility(View.VISIBLE);
            mBtnRemove.setVisibility(View.VISIBLE);
            mBtnUpdate.setEnabled(true);
            mBtnRemove.setEnabled(true);
            mEditTextEventName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            mEditTextEventContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            mTextViewStartDate.setClickable(true);
            mTextViewEndDate.setClickable(true);
        }

        mPresenter.getEvent(mEventCode);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //뷰 등록
    private void initView() {

        mTextViewMarketName = findViewById(R.id.textViewShopDetailEventName);
        mTextViewMarketCategory = findViewById(R.id.textViewShopDetailCategory);
        mTextViewMarketLocation = findViewById(R.id.textViewShopDetailEventAddress);

        mEditTextEventName = findViewById(R.id.editTextShopDetailEventTitle);
        mEditTextEventContent = findViewById(R.id.editTextShopDetailEventContent);

        mTextViewStartDate = findViewById(R.id.textViewShopDetailEventStartDate);
        mTextViewEndDate = findViewById(R.id.textViewShopDetailEventDateEnd);

        mBtnUpdate = findViewById(R.id.btnDetailEventUpdate);
        mBtnRemove = findViewById(R.id.btnDetailEventRemove);
    }

    //리스너 등록
    private void initListener() {

        mBtnRemove.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);

        mTextViewStartDate.setOnClickListener(this);
        mTextViewEndDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnDetailEventRemove) { //이벤트 삭제 버튼

            mPresenter.deleteEvent(mEventCode);
        }

        if (viewId == R.id.btnDetailEventUpdate) { //이벤트 수정 버튼

            if (!mPresenter.isTextSet(mEditTextEventName.getText().toString())) {

                Toast.makeText(this, "이벤트명을 입력해주세요", Toast.LENGTH_SHORT).show();
            } else if (mFirstYear > mLateYear
                        || mFirstMonth > mLateMonth
                        || (mFirstYear == mLateYear && mFirstMonth == mLateMonth && mFirstDay > mLateDay)) {

                Toast.makeText(this, "마지막 날짜를 다시 선택해주세요", Toast.LENGTH_SHORT).show();
            } else {
                mPresenter.updateEvent(mEventCode, mEditTextEventName.getText().toString(), mEditTextEventContent.getText().toString(),
                        mStartDateQuery, mEndDateQuery);
            }
        }

        if (viewId == R.id.textViewShopDetailEventStartDate) { //시작 날짜 선택

            showDate(true);
        }

        if (viewId == R.id.textViewShopDetailEventDateEnd) { //종료 날짜 선택

            showDate(false);
        }

    }

    @Override
    public void setEvent(HashMap<String, String> eventHashMap) {

        mStartDateQuery = eventHashMap.get("startDate");
        mEndDateQuery = eventHashMap.get("endDate");

        mFirstYear = Integer.parseInt(mStartDateQuery.substring(0, 4));
        mFirstMonth = Integer.parseInt(mStartDateQuery.substring(5, 7));
        mFirstDay = Integer.parseInt(mStartDateQuery.substring(8, 10));

        mLateYear = Integer.parseInt(mEndDateQuery.substring(0, 4));
        mLateMonth = Integer.parseInt(mEndDateQuery.substring(5, 7));
        mLateDay = Integer.parseInt(mEndDateQuery.substring(8, 10));

        mTextViewMarketName.setText(eventHashMap.get("shopName"));
        mTextViewMarketCategory.setText(eventHashMap.get("shopCategory"));
        mTextViewMarketLocation.setText(eventHashMap.get("shopAddress"));
        mTextViewStartDate.setText(mStartDateQuery);
        mTextViewEndDate.setText(mEndDateQuery);
        mEditTextEventName.setText(eventHashMap.get("eventName"));
        mEditTextEventContent.setText(eventHashMap.get("eventContent"));
    }

    @Override
    public void setDeleted() {

        Toast.makeText(this, "이벤트가 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void initDetailEvent() {

        mPresenter.getEvent(mEventCode);
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

            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mFirstDay = dayOfMonth;
                    mFirstMonth = month + 1;
                    mFirstYear = year;

                    mStartDateQuery = mPresenter.getDateQuery(mFirstYear, mFirstMonth, mFirstDay);
                    mTextViewStartDate.setText(mStartDateQuery);
                }
            },year, month - 1, day );


            date.set(year, month - 1, day);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());

            datePickerDialog.show();
        } else {

                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mLateDay = dayOfMonth;
                        mLateMonth = month + 1;
                        mLateYear = year;

                        mEndDateQuery = mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay);
                        mTextViewEndDate.setText(mEndDateQuery);

                    }
                }, mFirstYear, mFirstMonth - 1, mFirstDay);

                date.set(mFirstYear, mFirstMonth - 1, mFirstDay);
                datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());
                date.set(mFirstYear + 1, mFirstMonth, mFirstDay);
                datePickerDialog.getDatePicker().setMaxDate(date.getTime().getTime());

                datePickerDialog.show();

        }
    }
}