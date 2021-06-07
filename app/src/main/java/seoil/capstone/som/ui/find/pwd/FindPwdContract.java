package seoil.capstone.som.ui.find.pwd;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

// 비밀번호 찾기(변경) MVP 인터페이스
public interface FindPwdContract {

    interface Interactor extends BaseContract.Interactor {

        void getPhoneNumber(String userId, OnFinishApiListener onFinishApiListener);
        void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener);
        void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener);
        void changePassword(String userId, UserData.ChangePasswordReq req, OnFinishApiListener onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void visibleAuthView();
        void changeToPasswordView();
        void changeToResultView();
    }

    interface Presenter extends BaseContract.Presenter<FindPwdContract.View> {

        void sendSms(String userId);
        void sendAuthCode(String authCode);
        void changePassword(String password, String passwordCheck);
        void releaseAnother();
    }
}
