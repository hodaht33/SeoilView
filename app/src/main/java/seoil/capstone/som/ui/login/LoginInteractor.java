package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.res.Resources;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Login;

public class LoginInteractor implements LoginContract.Interactor {

    @Override
    public void serverLogin(String id, String pwd, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().serverLogin(new Login.LoginReq(id, pwd), onFinishApiListener);//.get(onFinishApiListener, new LoginRequest(id, pwd));
    }

    @Override
    public void kakaoLogin(Context context, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().kakaoLogin(context, onFinishApiListener);
    }

    @Override
    public void naverLogin(Context context, Resources res, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().naverLogin(context, res, onFinishApiListener);
    }
}
