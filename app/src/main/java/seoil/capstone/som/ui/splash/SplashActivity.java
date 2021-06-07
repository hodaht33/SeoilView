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

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SharedPreferences mSharedPreferences;
    private SplashPresenter mPresenter;

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

    @Override
    protected void onDestroy() {

        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

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

    @Override
    public void changeView(boolean isMain) {

        Intent intent = new Intent();

        if (isMain) {

            intent.setComponent(new ComponentName(this, MainActivity.class));
        } else {

            intent.setComponent(new ComponentName(this, LoginActivity.class));
        }

        // 3초 지연시간 후 해당 액티비티로 전환
        Handler delayHandler = new Handler(Looper.getMainLooper());
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 3000);
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