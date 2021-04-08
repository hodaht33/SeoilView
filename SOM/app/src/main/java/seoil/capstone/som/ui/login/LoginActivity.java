package seoil.capstone.som.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private LoginPresenter mPresenter;

    // resource
    private TextInputEditText mEditTextId;
    private TextInputEditText mEditTextPw;
    private Button mBtnLogin;
    private Button mBtnToRegit;
    private ImageView naverLogin;
    private ImageView kakaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter();

        mEditTextId = findViewById(R.id.editTextLoginId);
        mEditTextPw = findViewById(R.id.editTextLoginPw);
        mBtnLogin = findViewById(R.id.btnLoginLogin);
        mBtnToRegit = findViewById(R.id.btnLoginToRegit);
        naverLogin = findViewById(R.id.btnLoginNaverLogin);
        kakaoLogin = findViewById(R.id.btnLoginKakaoLogin);

        setImgWithGlide(R.drawable.image_naver_login, naverLogin);
        setImgWithGlide(R.drawable.image_kakao_login, kakaoLogin);

        mPresenter.setView(this);
        mPresenter.setContext(this);
        mPresenter.setResources(getResources());
        mBtnLogin.setOnClickListener(this);
        mBtnToRegit.setOnClickListener(this);
        naverLogin.setOnClickListener(this);
        kakaoLogin.setOnClickListener(this);
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
        if (v.getId() == R.id.btnLoginLogin) {
            Log.d(TAG, "Login");
            int loginResult = mPresenter.login(mEditTextId, mEditTextPw);
            if (loginResult == LoginPresenter.ERROR_WRONG_ID) {
                mEditTextId.setError("customer 또는 manager 입력하세요.");
                mEditTextId.requestFocus();

                renderKeyboard();
            } else if (loginResult == LoginPresenter.ERROR_WRONG_PW) {
                mEditTextPw.setError("1만 입력하세요.");
                mEditTextPw.requestFocus();

                renderKeyboard();
            } else if (loginResult == (LoginPresenter.ERROR_WRONG_ID | LoginPresenter.ERROR_WRONG_PW)) {
                mEditTextId.setError("customer 또는 manager 입력하세요.");
                mEditTextPw.setError("1만 입력하세요.");
                mEditTextId.requestFocus();

                renderKeyboard();
            }
        }

        if (v.getId() == R.id.btnLoginToRegit) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btnLoginNaverLogin) {
            mPresenter.naverLogin();
        }

        if (v.getId() == R.id.btnLoginKakaoLogin) {
            mPresenter.kakaoLogin();
        }
    }

    @Override
    public void toMain(Intent intent) {
        intent.putExtra("id", mEditTextId.getText().toString());

        startActivity(intent);

        finish();
    }

    @Override
    public void toRegit(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mPresenter.releaseResources();;
        mPresenter.releaseContext();
        mPresenter.releaseView();

        super.onDestroy();
    }

    // 글라이드로 이미지를 처리
    private void setImgWithGlide(int imgResId, ImageView targetView) {
        Glide.with(this)
                .load(imgResId)
                .circleCrop()
                .into(targetView);
    }

    private void renderKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showProgressBar() {

    }
}