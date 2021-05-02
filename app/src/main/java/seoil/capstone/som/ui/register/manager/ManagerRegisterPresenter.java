package seoil.capstone.som.ui.register.manager;

import android.content.Context;

import seoil.capstone.som.ui.register.ValidChecker;

public class ManagerRegisterPresenter extends ValidChecker implements ManagerRegisterContract.Presenter {

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

    @Override
    public void register(Context context, String platform, String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement) {

    }
}
