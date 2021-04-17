package seoil.capstone.som.ui.register.manager;

import android.content.Context;
import android.content.res.Resources;

public class ManagerRegisterPresenter implements ManagerRegisterContract.Presenter {

    private ManagerRegisterContract.View mView;
    private ManagerRegisterInteractor mInteractor;

    @Override
    public void setView(ManagerRegisterContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new ManagerRegisterInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }
}
