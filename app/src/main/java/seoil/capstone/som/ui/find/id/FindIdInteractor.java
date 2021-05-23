package seoil.capstone.som.ui.find.id;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;

public class FindIdInteractor implements FindIdContract.Interactor {

    @Override
    public void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    @Override
    public void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthForFindId(req, onFinishApiListener);
    }
}
