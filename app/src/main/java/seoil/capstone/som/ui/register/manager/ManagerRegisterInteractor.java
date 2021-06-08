package seoil.capstone.som.ui.register.manager;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;

public class ManagerRegisterInteractor implements ManagerRegisterContract.Interactor {

    @Override
    public void register(UserDTO.Manager manager, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().managerRegister(manager, onFinishApiListener);
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
