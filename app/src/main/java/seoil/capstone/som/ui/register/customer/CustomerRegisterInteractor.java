package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Register;

public class CustomerRegisterInteractor implements CustomerRegisterContract.Interactor {

    @Override
    public void register(Register.Customer customer, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        AppApiHelper.getInstance().customerRegister(customer, onFinishApiListener);
    }
}
