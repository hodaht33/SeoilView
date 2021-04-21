package seoil.capstone.som.ui.register.customer;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.RegisterResponse;

public interface CustomerRegisterContract {

    interface Interactor extends BaseContract.Interactor {

        void register(String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement, OnFinishApiListener<RegisterResponse> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void finishRegister(Intent intent);
    }

    interface Presenter extends BaseContract.Presenter<CustomerRegisterContract.View> {

        void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement);
    }
}
