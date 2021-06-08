package seoil.capstone.som.ui.find.pwd;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;

// 비밀번호 찾기(변경) MVP 인터페이스
public interface FindPwdContract {

    interface Interactor extends BaseContract.Interactor {

        void getPhoneNumber(String userId, OnFinishApiListener onFinishApiListener);
        void sendSms(AuthDTO.Req req, OnFinishApiListener onFinishApiListener);
        void sendAuthCode(AuthDTO.Req req, OnFinishApiListener onFinishApiListener);
        void changePassword(String userId, UserDTO.ChangePasswordReq req, OnFinishApiListener onFinishApiListener);
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
