package seoil.capstone.som.ui.find.id;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;

// 아이디 찾기 MVP 인터페이스
public interface FindIdContract {

    interface Interactor extends BaseContract.Interactor {

        void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener);
        void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void visibleAuthView();
        void visibleResultView(ArrayList<String> idResults);
    }

    interface Presenter extends BaseContract.Presenter<FindIdContract.View> {

        void sendSms(String phoneNumber);
        void sendAuthCode(String authCode);
    }
}
