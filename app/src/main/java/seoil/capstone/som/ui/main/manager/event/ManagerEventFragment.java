package seoil.capstone.som.ui.main.manager.event;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.event.detail.DetailEventActivity;

public class ManagerEventFragment extends Fragment implements ManagerEventContract.View, View.OnClickListener{

    private ManagerEventPresenter mPresenter;           //Fragment에서 발생하는 데이터를 처리 및 View 와 Interactor 연결
    private RecyclerView mRecyclerViewMain;             //진행중 이벤트를 보여줄 리사이클러뷰
    private ManagerEventAdapter mAdapterMain;           //진행중 이벤트의 어댑터

    private ArrayList<ManagerEventAdapter.Item> mEventName;                 //어댑터 초기화용 변수
    private ArrayList<String> mEventDate;                                   //
    private ArrayList<Integer> mEventCode;                                  //

    private String mShopId;                              //점주의 아이디
    private Button mBtnAddEvent;                         //이벤트 추가 버튼
    private AlertDialog mAlertDialogInsertEvent;         //이벤트 추가창

    private int mFirstDay, mFirstMonth, mFirstYear;      //이벤트 시작 날짜
    private int mLateDay, mLateMonth, mLateYear;         //이벤트 종료 날짜

    private String mStartDateQuery;
    private String mEndDateQuery;

    private Button mBtnEventStartDate;
    private Button mBtnEventEndDate;

    private Boolean isBtnStartDateSelected;
    private Boolean isBtnEndDateSelected;
    public ManagerEventFragment() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.getEvent(mShopId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerEventPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mEventName = new ArrayList<>();
        mEventCode = new ArrayList<>();
        mEventDate = new ArrayList<>();

        isBtnStartDateSelected = false;
        isBtnEndDateSelected = false;

        mFirstDay = mFirstYear = mFirstMonth = 0;
        mLateDay = mLateYear = mLateMonth = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_event, container, false);

        initView(view);

        initListener();

        mShopId = ((GlobalApplication) getActivity().getApplicationContext()).getUserId();

        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapterMain = new ManagerEventAdapter(mEventName, mEventCode, mEventDate, mPresenter);
        mRecyclerViewMain.setAdapter(mAdapterMain);

        mPresenter.getEvent(mShopId);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //ManagerEventFragment 뷰 초기화
    private void initView(View view) {

        mRecyclerViewMain = view.findViewById(R.id.recyclerViewMEventMain);
        mBtnAddEvent = view.findViewById(R.id.btnMEventAdd);
    }

    //현재 진행중 이벤트, 종료된 이벤트를 Event Adapter에 갱신
    @Override
    public synchronized void setAdapter(ArrayList<ManagerEventAdapter.Item> eventName, ArrayList<Integer> eventCode, ArrayList<String> eventDate) {

        mAdapterMain.setData(eventName, eventCode, eventDate);
        mAdapterMain.notifyDataSetChanged();
    }

    //상세 정보 액티비티로 이동
    @Override
    public void startDetailedEvent(int eventCode) {

        Intent intent = new Intent(getActivity(), DetailEventActivity.class);
        intent.putExtra("eventCode", eventCode);
        this.startActivityForResult(intent, 10);
    }

    //다이얼로그 입력 완료시 종료 및 이벤트 갱신
    @Override
    public void endInsert() {

        mAlertDialogInsertEvent.dismiss();
        mPresenter.getEvent(mShopId);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMEventAdd) {

            if (mAlertDialogInsertEvent != null) {

                mAlertDialogInsertEvent.dismiss();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_manager_add_event, null, false);

            builder.setView(view);

            EditText editTextName = view.findViewById(R.id.editTextMAddEventName);
            EditText editTextContent = view.findViewById(R.id.editTextMAddEventContent);
            mBtnEventStartDate = view.findViewById(R.id.btnMAddEventStartDate);
            mBtnEventEndDate = view.findViewById(R.id.btnMAddEventEndDate);
            Button btnClose = view.findViewById(R.id.btnMAddEventClose);
            Button btnFinish = view.findViewById(R.id.btnMAddEventFinish);

            mBtnEventStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDate(true);
                }
            });

            mBtnEventEndDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showDate(false);
                }
            });

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAlertDialogInsertEvent.dismiss();
                }
            });

            btnFinish.setOnClickListener(new View.OnClickListener() { //이벤트 추가 버튼 눌렀을 때 데이터 검사
                @Override
                public void onClick(View v) {

                    if (!mPresenter.isTextSet(editTextName.getText().toString())) {

                        editTextName.setError("이벤트 이름을 입력해주세요");
                        editTextName.requestFocus();
                    } else if (!mPresenter.isTextSet(editTextContent.getText().toString())) {

                        editTextContent.setError("이벤트 내용을 입력해주세요");
                        editTextContent.requestFocus();
                    } else if (!isBtnStartDateSelected) {

                        Toast.makeText(getContext(),"시작 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                    } else if (!isBtnEndDateSelected) {

                        Toast.makeText(getContext(), "종료 날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                    } else if (mFirstYear ==  mLateYear && mFirstMonth == mLateMonth && mLateDay < mFirstDay) {

                        Toast.makeText(getContext(), "종료 날짜를 다시 선택해주세요", Toast.LENGTH_SHORT).show();
                    } else {

                        mPresenter.insertEvent(mShopId, editTextName.getText().toString(), editTextContent.getText().toString(),
                                                mStartDateQuery, mEndDateQuery, false); //후에 푸시 기능 넣을 시 변경
                    }
                        
                }
            });

            mAlertDialogInsertEvent = builder.create();             //
            mAlertDialogInsertEvent.show();                         //다이얼로그 생성 및 보여줌
        }
    }

    //리스너 등록
    private void initListener() {

        mBtnAddEvent.setOnClickListener(this);
    }

    void showDate(Boolean flag) {
        DatePickerDialog datePickerDialog;
        Calendar date = Calendar.getInstance();
        if (flag) {                 //이벤트 시작 날짜 선택

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
                    mBtnEventStartDate.setText(mStartDateQuery);

                    isBtnStartDateSelected = true;
                }
            },year, month - 1, day );


            date.set(year, month - 1, day);
            datePickerDialog.getDatePicker().setMinDate(date.getTime().getTime());

            datePickerDialog.show();
        } else {                    //이벤트 종료 날짜 선택

            datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    mLateDay = dayOfMonth;
                    mLateMonth = month + 1;
                    mLateYear = year;

                    mEndDateQuery = mPresenter.getDateQuery(mLateYear, mLateMonth, mLateDay);
                    mBtnEventEndDate.setText(mEndDateQuery);

                    isBtnEndDateSelected = true;
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