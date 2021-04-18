package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;

public class CustomerRegisterInteractor implements CustomerRegisterContract.Interactor {

    @Override
    public void register(String id, String pwd, String birthdate, int gender, String email, String phoneNumber, OnFinishApiListener<RegisterResponse> onFinishApiListener) {

        AppApiHelper.getInstance().customerRegister(new RegisterRequest.Customer(id, pwd, birthdate, gender, email, phoneNumber), onFinishApiListener);
    }
}
