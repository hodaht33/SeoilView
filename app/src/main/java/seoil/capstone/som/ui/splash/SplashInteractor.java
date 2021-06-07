package seoil.capstone.som.ui.splash;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Login;

public class SplashInteractor implements SplashContract.Interactor {

    @Override
    public void login(Login.LoginReq req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().serverLogin(req, onFinishApiListener);
    }
}
