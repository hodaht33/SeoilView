package seoil.capstone.som.ui.splash;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.LoginDTO;

public class SplashInteractor implements SplashContract.Interactor {

    // api로 로그인 요청
    @Override
    public void login(LoginDTO.LoginReq req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().serverLogin(req, onFinishApiListener);
    }
}
