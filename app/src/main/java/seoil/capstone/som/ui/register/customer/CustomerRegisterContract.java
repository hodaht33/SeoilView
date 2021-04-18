package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.RegisterResponse;

public interface CustomerRegisterContract {

    interface Interactor extends BaseContract.Interactor {

        void register(String id, String pwd, String birthdate, int gender, String email, String phoneNumber, OnFinishApiListener<RegisterResponse> onFinishApiListener);
    }

    interface View extends BaseContract.View {


    }

    interface Presenter extends BaseContract.Presenter<CustomerRegisterContract.View> {


    }
}
