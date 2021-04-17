package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.google.android.material.textfield.TextInputEditText;
import com.nhn.android.naverlogin.OAuthLogin;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.repository.OnFinishRepositoryListener;

public interface LoginContract {

    interface Interactor {

        void login(String id, String pwd, OnFinishRepositoryListener onFinishRepositoryListener);
    }

    interface View extends BaseContract.View {

        void loginFail(int errorCode);
        void toMain(Intent intent);
        void toRegit(Intent intent);
        void showToast(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void setContext(Context content);
        void releaseContext();
        void setResources(Resources res);
        void releaseResources();
        void login(String id, String pwd);
        void kakaoLogin();
        void setKakaoLoginData();
        void naverLogin();
        void setNaverLoginData(OAuthLogin oAuthLogin) throws InterruptedException;
    }
}
