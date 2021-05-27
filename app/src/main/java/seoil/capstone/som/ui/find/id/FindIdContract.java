package seoil.capstone.som.ui.find.id;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;

public interface FindIdContract {

    interface Interactor extends BaseContract.Interactor {

        void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener);
        void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void showDialog(String msg);
        void visibleAuthView();
        void visibleResultView(ArrayList<String> idResults);
    }

    interface Presenter extends BaseContract.Presenter<FindIdContract.View> {

        void sendSms(String phoneNumber);
        void sendAuthCode(String authCode);
    }
}
