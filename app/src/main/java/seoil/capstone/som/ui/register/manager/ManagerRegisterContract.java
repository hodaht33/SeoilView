package seoil.capstone.som.ui.register.manager;

import android.content.Context;
import android.content.Intent;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

// 점주 회원가입 MVP인터페이스
public interface ManagerRegisterContract {

    interface Interactor extends BaseContract.Interactor {

        void register(UserData.Manager manager, OnFinishApiListener<UserData.StatusRes> onFinishApiListener);
        void sendSms(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener);
        void sendAuthCode(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void changePhoneAuthButton(int status);
        void finishRegister(Intent intent);
    }

    interface Presenter extends BaseContract.Presenter<ManagerRegisterContract.View> {

        void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber,
                      String shopCode, String shopName, String shopPostCode, String shopAddress, String shopCategory, boolean marketingAgreement);
        void sendSms(String phoneNumber);
        void sendAuthCode(String phoneNumber, String authCode);
    }
}
