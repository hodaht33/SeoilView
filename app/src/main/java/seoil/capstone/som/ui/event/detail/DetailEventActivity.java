package seoil.capstone.som.ui.event.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import seoil.capstone.som.R;

public class DetailEventActivity extends AppCompatActivity implements DetailEventContract.View, View.OnClickListener{

    private DetailEventPresenter mPresenter;
    private int mEventCode;
    private TextView mTextViewMarketName;
    private TextView mTextViewMarketCategory;
    private TextView mTextViewMarketLocation;
    private ImageView mImageViewFavorite;
    private EditText mEditTextEventName;
    private EditText mEditTextEventContent;
    private TextView mTextViewStartDate;
    private TextView mTextViewEndDate;
    private Button mBtnRemove;
    private Button mBtnUpdate;

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

        mImageViewFavorite = findViewById(R.id.imageViewDetailEventFavorite);

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

        mImageViewFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnDetailEventRemove) { //이벤트 삭제 버튼

        }

        if (viewId == R.id.btnDetailEventUpdate) { //이벤트 수정 버튼

        }

        if (viewId == R.id.textViewShopDetailEventStartDate) { //시작 날짜 선택
            
        }

        if (viewId == R.id.textViewShopDetailEventDateEnd) { //종료 날짜 선택

        }

        if (viewId == R.id.imageViewDetailEventFavorite) { //즐겨찾기 이미지 버튼
            
        }
    }
}