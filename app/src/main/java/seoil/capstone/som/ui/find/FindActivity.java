package seoil.capstone.som.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import seoil.capstone.som.R;

public class FindActivity extends AppCompatActivity implements FindContract.View ,View.OnClickListener{

    private final int FIND_ACTIVITY_ID = 1;
    private final int FIND_ACTIVITY_PWD = 2;
    private Button mBtnFindId, mBtnFindPwd, mBtinFinish;
    private int mState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mBtnFindId = findViewById(R.id.btnFindFindId);
        mBtnFindPwd = findViewById(R.id.btnFindFindPassword);
        mBtinFinish = findViewById(R.id.btnFindBack);

        mBtnFindId.setOnClickListener(this);
        mBtnFindPwd.setOnClickListener(this);
        mBtinFinish.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.getStringExtra("ID").equals("1")) {//사용자가 클릭한 버튼에 맞는 fragment 실행

            mState = changeView(FIND_ACTIVITY_ID);
        } else {

            mState = changeView(FIND_ACTIVITY_PWD);
        }
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFindFindId && mState != FIND_ACTIVITY_ID) {//아이디 버튼을 클릭 했을때 아이디 fragment로 변경

            mState = changeView(FIND_ACTIVITY_ID);
        }
        if (v.getId() == R.id.btnFindFindPassword && mState != FIND_ACTIVITY_PWD) {//비밀번호 버튼을 클릭 했을때 비밀번호 fragment로 변경

            mState = changeView(FIND_ACTIVITY_PWD);
        }
        if (v.getId() == R.id.btnFindBack) {

            finish();
        }
    }

    private int changeView(int state) {

        if (state == FIND_ACTIVITY_ID) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayoutFind,new FragmentID()).commitAllowingStateLoss();
            mBtnFindId.setBackgroundColor(Color.BLACK);
            mBtnFindId.setTextColor(Color.WHITE);
            mBtnFindPwd.setBackgroundColor(Color.WHITE);
            mBtnFindPwd.setTextColor(Color.GRAY);

            return FIND_ACTIVITY_ID;
        }
        else {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayoutFind,new FragmentPWD()).commitAllowingStateLoss();
            mBtnFindPwd.setBackgroundColor(Color.BLACK);
            mBtnFindPwd.setTextColor(Color.WHITE);
            mBtnFindId.setBackgroundColor(Color.WHITE);
            mBtnFindId.setTextColor(Color.GRAY);

            return FIND_ACTIVITY_PWD;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}