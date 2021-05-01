package seoil.capstone.som.ui.register.manager;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Register;

public class ManagerRegisterInteractor implements ManagerRegisterContract.Interactor {

    @Override
    public void register(Register.Manager manager, OnFinishApiListener<Register.RegisterRes> onFinishApiListener) {

        AppApiHelper.getInstance().managerRegister(manager, onFinishApiListener);
    }
}
