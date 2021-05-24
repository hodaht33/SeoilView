package seoil.capstone.som.ui.find.pwd;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

public class FindPwdInteractor implements FindPwdContract.Interactor {


    @Override
    public void getPhoneNumber(String userId, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().getUserPhoneNumber(userId, onFinishApiListener);
    }

    @Override
    public void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    @Override
    public void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthCode(req, onFinishApiListener);
    }

    @Override
    public void changePassword(String userId, UserData.ChangePasswordReq req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().updatePassword(userId, req, onFinishApiListener);
    }
}
