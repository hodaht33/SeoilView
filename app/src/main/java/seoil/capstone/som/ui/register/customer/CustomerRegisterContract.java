package seoil.capstone.som.ui.register.customer;

import android.content.Context;
import android.content.Intent;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

public interface CustomerRegisterContract {

    interface Interactor extends BaseContract.Interactor {

        void register(UserData.Customer customer, OnFinishApiListener<UserData.StatusRes> onFinishApiListener);
        void sendSms(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener);
        void sendAuthCode(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void showDialog(String msg);
        void changePhoneAuthButton(int status);
        void finishRegister(Intent intent);
    }

    interface Presenter extends BaseContract.Presenter<CustomerRegisterContract.View> {

        void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement);
        void sendSms(String phoneNumber);
        void sendAuthCode(String phoneNumber, String authCode);
    }
}
