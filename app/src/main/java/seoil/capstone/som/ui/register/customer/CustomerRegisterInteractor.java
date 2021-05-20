package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.Register;

public class CustomerRegisterInteractor implements CustomerRegisterContract.Interactor {

    @Override
    public void register(Register.Customer customer, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        AppApiHelper.getInstance().customerRegister(customer, onFinishApiListener);
    }

    @Override
    public void sendSms(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    @Override
    public void sendAuthCode(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthCode(req, onFinishApiListener);
    }
}
