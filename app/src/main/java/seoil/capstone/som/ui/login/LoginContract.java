package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Login;

public interface LoginContract {

    interface Interactor {

        void serverLogin(Login.LoginReq req, OnFinishApiListener<Login.LoginRes> onFinishApiListener);
        void kakaoLogin(Context context, OnFinishApiListener<Login.KakaoLoginRes> onFinishApiListener);
        void naverLogin(Context context, Resources resources, OnFinishApiListener<Login.NaverLoginRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void loginFail(int errorCode);
        void toMain(Intent intent);
        void toRegit(Intent intent);
        void showToast(String text);
        void setUserData(String userID, String userCode);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void serverLogin(String id, String pwd, Context context, OnFinishApiListener onFinishApiListener);
        void kakaoLogin(Context context);
        void naverLogin(Context context, Resources resources);
    }
}
