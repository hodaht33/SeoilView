package seoil.capstone.som.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.find.id.FindIdFragment;
import seoil.capstone.som.ui.find.pwd.FindPwdFragment;

// 아이디, 비밀번호 찾기 뷰(액티비티)
public class FindActivity extends AppCompatActivity implements View.OnClickListener{

    private final int FIND_ACTIVITY_ID = 1;
    private final int FIND_ACTIVITY_PWD = 2;
    private Button mBtnFindId;
    private Button mBtnFindPwd;
    private int mState;

    // UI, 리스너 초기화
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        mBtnFindId = findViewById(R.id.btnFindFindId);
        mBtnFindPwd = findViewById(R.id.btnFindFindPassword);

        mBtnFindId.setOnClickListener(this);
        mBtnFindPwd.setOnClickListener(this);

        Intent intent = getIntent();

        //사용자가 클릭한 버튼에 맞는 fragment 실행
        if (intent.getStringExtra("ID").equals("1")) {

            mState = FIND_ACTIVITY_ID;
        } else {

            mState = FIND_ACTIVITY_PWD;
        }

        changeView(mState);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnFindFindId
                && mState != FIND_ACTIVITY_ID) {    //아이디 버튼을 클릭 했을때 아이디찾기 fragment로 변경

            mState = FIND_ACTIVITY_ID;
            changeView(mState);
        } else if (v.getId() == R.id.btnFindFindPassword
                && mState != FIND_ACTIVITY_PWD) {   //비밀번호 버튼을 클릭 했을때 비밀번호찾기 fragment로 변경

            mState = FIND_ACTIVITY_PWD;
            changeView(mState);
        }
    }

    // 아이디 또는 비밀번호 찾기 뷰(프레그먼트) 설정
    private void changeView(int state) {

        if (state == FIND_ACTIVITY_ID) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentLayoutFind, new FindIdFragment())
                    .commitAllowingStateLoss();

            mBtnFindId.setBackgroundColor(Color.BLACK);
            mBtnFindId.setTextColor(Color.WHITE);
            mBtnFindPwd.setBackgroundColor(Color.WHITE);
            mBtnFindPwd.setTextColor(Color.GRAY);
        } else {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentLayoutFind, new FindPwdFragment())
                    .commitAllowingStateLoss();

            mBtnFindPwd.setBackgroundColor(Color.BLACK);
            mBtnFindPwd.setTextColor(Color.WHITE);
            mBtnFindId.setBackgroundColor(Color.WHITE);
            mBtnFindId.setTextColor(Color.GRAY);
        }
    }
}