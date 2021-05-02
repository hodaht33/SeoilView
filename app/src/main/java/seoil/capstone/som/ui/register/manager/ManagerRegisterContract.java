package seoil.capstone.som.ui.register.manager;

import android.content.Context;
import android.content.Intent;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Register;

public interface ManagerRegisterContract {

    interface Interactor extends BaseContract.Interactor {

        void register(Register.Manager manager, OnFinishApiListener<Register.RegisterRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void finishRegister(Intent intent);
    }

    interface Presenter extends BaseContract.Presenter<ManagerRegisterContract.View> {

        void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement);
    }
}
