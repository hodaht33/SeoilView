package seoil.capstone.som.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;
import seoil.capstone.som.ui.main.MainActivity;

// 시작 스플래시 뷰(액티비티)
public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SharedPreferences mSharedPreferences;
    private SplashPresenter mPresenter;

    // 프레젠터 생성, 로그인 상태 유지가 되어있는지 검사
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new SplashPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mSharedPreferences = getSharedPreferences("keepLogin", MODE_PRIVATE);

        // 로그인 상태 유지 시켰는지 검사
        keepLogin();
    }

    // 프레젠터 삭제
    @Override
    protected void onDestroy() {

        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    // sharedPreferences에 아이디와 비밀번호가 저장되어 있으면 로그인
    // TODO: 추후 비밀번호가 아닌 손님 또는 점주에 따른 정보만 저장해두고 서버에 로그인 요청을 하지 않도록 변경
    private void keepLogin() {

        String id = mSharedPreferences.getString("id","");
        String pwd = mSharedPreferences.getString("pwd", "");

        if (mSharedPreferences.getBoolean("keepLoginState", false)
                && !id.isEmpty()
                && !pwd.isEmpty()) {

            mPresenter.login(id, pwd, this);
        } else {

            changeView(false);
        }
    }

    // 메인 뷰 또는 로그인 뷰로 화면 전환
    @Override
    public void changeView(boolean isMain) {

        Intent intent = new Intent();

        if (isMain) {

            intent.setComponent(new ComponentName(this, MainActivity.class));
        } else {

            intent.setComponent(new ComponentName(this, LoginActivity.class));
        }

        // 2초 지연시간 후 해당 액티비티로 전환
        Handler delayHandler = new Handler(Looper.getMainLooper());
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog(String msg) {

    }
}