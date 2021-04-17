package seoil.capstone.som.ui.register.customer;

import android.content.Context;
import android.content.res.Resources;

public class CustomerRegisterPresenter implements CustomerRegisterContract.Presenter {

    private CustomerRegisterContract.View view;

    @Override
    public void setView(CustomerRegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {

    }

    @Override
    public void releaseInteractor() {

    }
}
