package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;

public class CustomerRegisterInteractor implements CustomerRegisterContract.Interactor {

    @Override
    public void register(UserDTO.Customer customer, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().customerRegister(customer, onFinishApiListener);
    }

    @Override
    public void sendSms(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    @Override
    public void sendAuthCode(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthCode(req, onFinishApiListener);
    }
}
