package seoil.capstone.som.ui.login;

import android.content.Context;
import android.content.res.Resources;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Login;

public class LoginInteractor implements LoginContract.Interactor {

    @Override
    public void serverLogin(Login.LoginReq req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().serverLogin(req, onFinishApiListener);
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
