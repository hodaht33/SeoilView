package seoil.capstone.som.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.ui.find.FindActivity;
import seoil.capstone.som.ui.register.RegisterActivity;
import seoil.capstone.som.util.Utility;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener, TextView.OnEditorActionListener {

    private LoginPresenter mPresenter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    // resource
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPw;
    private ImageView mNaverLogin;
    private ImageView mKakaoLogin;
    private Button mBtnLogin;
    private Button mBtnToRegit;
    private Button mBtnFindIdPwd;
    private CheckBox mChkBoxKeepLogin;
    private long mLastTimeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mSharedPreferences = getSharedPreferences("keepLogin", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        keepLogin();

        mEditTextId = findViewById(R.id.editTextLoginId);
        mEditTextPw = findViewById(R.id.editTextLoginPw);
        mNaverLogin = findViewById(R.id.btnLoginNaverLogin);
        mKakaoLogin = findViewById(R.id.btnLoginKakaoLogin);
        mBtnLogin = findViewById(R.id.btnLoginLogin);
        mBtnToRegit = findViewById(R.id.btnLoginToRegit);
        mBtnFindIdPwd = findViewById(R.id.btnLoginFindIdPwd);
        mChkBoxKeepLogin = findViewById(R.id.chBoxLoginKeepLogin);

        setImgWithGlide(R.drawable.image_naver_login, mNaverLogin);
        setImgWithGlide(R.drawable.image_kakao_login, mKakaoLogin);

        mEditTextPw.setOnEditorActionListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnToRegit.setOnClickListener(this);
        mNaverLogin.setOnClickListener(this);
        mKakaoLogin.setOnClickListener(this);
        mBtnFindIdPwd.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis() - mLastTimeBackPressed < 1000) {
            finish();

            return;
        }

        mLastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {

        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        super.onPause();
    }

    // 각 뷰의 클릭 이벤트
    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        // 로그인 버튼
        if (viewId == R.id.btnLoginLogin) {

            if (mEditTextId.getText().toString().equals("")) {

                mEditTextId.setError("아이디를 입력해주세요.");
                mEditTextId.requestFocus();

                Utility.getInstance().activateKeyboard(this);
            } else if (mEditTextPw.getText().toString().equals("")) {

                mEditTextPw.setError("비밀번호를 입력해주세요.");
                mEditTextPw.requestFocus();

                Utility.getInstance().activateKeyboard(this);
            } else {

                mPresenter.serverLogin(
                        mEditTextId.getText().toString(),
                        mEditTextPw.getText().toString(),
                        this,
                        null
                );
            }
        } else if (viewId == R.id.btnLoginToRegit) {   // 회원가입 버튼

            Bundle bundle = new Bundle();
            bundle.putString("platform", "");

            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("data", bundle);

            startActivity(intent);
        } else if (viewId == R.id.btnLoginNaverLogin) { // 네이버 로그인 버튼

            mPresenter.naverLogin(this, getResources());
        } else if (viewId == R.id.btnLoginKakaoLogin) { // 카카오 로그인 버튼

            mPresenter.kakaoLogin(this);
        } else if (viewId == R.id.btnLoginFindIdPwd) {

            Intent intent = new Intent(this, FindActivity.class);
            intent.putExtra("ID", "1");
            startActivity(intent);
        }
    }

    // 로그인 실패 에러 코드에 따라 알림창 출력
    @Override
    public void loginFail(int errorCode) {
        if (errorCode == UserApi.LOGIN_FAIL_ID) {

            mEditTextId.setError("아이디가 존재하지 않습니다.\n다시 확인해주세요.");
            mEditTextId.requestFocus();

            Utility.getInstance().activateKeyboard(this);
        } else if (errorCode == UserApi.LOGIN_FAIL_PWD) {

            mEditTextPw.setError("비밀번호가 다릅니다.\n다시 확인해주세요.");
            mEditTextPw.requestFocus();

            Utility.getInstance().activateKeyboard(this);
        } else {

            showToast("알 수 없는 문제가 발생했습니다.\n개발자에게 문의해 주세요.");
        }
    }

    @Override
    public void toMain(Intent intent) {

        // finish대신 flag를 지정하여 메인 액티비티로 이동 시 스택 내 액티비티 인스턴스 모두 삭제
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        boolean isKeepLoginChecked = mChkBoxKeepLogin.isChecked();

        // 네이버나 카톡 로그인이 아닐 경우 platform의 값은 ""
        if (intent.getBundleExtra("data").getString("platform").isEmpty()
            && isKeepLoginChecked) {

            mEditor.putBoolean("keepLoginState", true);
            mEditor.putString("id", mEditTextId.getText().toString());
            mEditor.putString("pwd", mEditTextPw.getText().toString());
            mEditor.commit();
        }

        startActivity(intent);
    }

    @Override
    public void toRegit(Intent intent) {

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {

        mPresenter.releaseInteractor();
        mPresenter.releaseView();

        mPresenter = null;

        super.onDestroy();
    }

    // 글라이드로 이미지를 처리
    private void setImgWithGlide(int imgResId, ImageView targetView) {

        Glide.with(this)
                .load(imgResId)
                .circleCrop()
                .into(targetView);
    }

    @Override
    public void showToast(String text) {

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserData(String userID, String userCode) {

        GlobalApplication app = (GlobalApplication) getApplicationContext();
        app.setUserId(userID);
        app.setUserCode(userCode);
    }

    @Override
    public void showProgress() {

        Utility.getInstance().activateProgressAnim(this, getWindow().findViewById(R.id.activityLoginLayout));
    }

    @Override
    public void hideProgress() {

    }

    // 비밀번호 텍스트 에디트에서 엔터(완료) 버튼 클릭 시 바로 로그인 버튼이 클릭 되도록 구현
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (event != null
                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                || actionId == EditorInfo.IME_ACTION_DONE) {

            mBtnLogin.performClick();

            return true;
        }

        return false;
    }

    private void keepLogin() {

        String id = mSharedPreferences.getString("id","");
        String pwd = mSharedPreferences.getString("pwd", "");

        if (mSharedPreferences.getBoolean("keepLoginState", false)
            && !id.isEmpty()
            && !pwd.isEmpty()) {

            mPresenter.serverLogin(
                    id,
                    pwd,
                    this,
                    null
            );
        }
    }
}