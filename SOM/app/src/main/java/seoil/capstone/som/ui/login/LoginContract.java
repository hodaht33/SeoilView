package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.google.android.material.textfield.TextInputEditText;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import seoil.capstone.som.base.BaseContract;

public interface LoginContract {

    interface View extends BaseContract.View {
        void toMain(Intent intent);
        void toRegit(Intent intent);
        void showToast(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void setContext(Context content);
        void releaseContext();
        void setResources(Resources res);
        void releaseResources();
        int login(TextInputEditText idView, TextInputEditText pwView);
        void setLoginData(String userId, int userCode);
        void kakaoLogin();
        void setKakaoLoginData();
        void naverLogin();
        void setNaverLoginData(OAuthLogin oAuthLogin) throws InterruptedException;
    }
}
