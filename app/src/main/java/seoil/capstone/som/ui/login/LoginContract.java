package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.nhn.android.naverlogin.OAuthLogin;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.LoginResponse;

public interface LoginContract {

    interface Interactor {

        void serverLogin(String id, String pwd, OnFinishApiListener<LoginResponse.SomRestLoginApi> onFinishApiListener);
        void kakaoLogin(Context context, OnFinishApiListener<LoginResponse.KakaoLoginApi> onFinishApiListener);
        void naverLogin(Context context, Resources resources, OnFinishApiListener<LoginResponse.NaverLoginApi> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void loginFail(int errorCode);
        void toMain(Intent intent);
        void toRegit(Intent intent);
        void showToast(String text);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void serverLogin(String id, String pwd, Context context, OnFinishApiListener onFinishApiListener);
        void kakaoLogin(Context context);
        void naverLogin(Context context, Resources resources);
    }
}
