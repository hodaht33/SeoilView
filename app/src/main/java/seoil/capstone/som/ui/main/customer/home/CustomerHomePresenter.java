package seoil.capstone.som.ui.main.customer.home;

import android.content.Context;
import android.content.res.Resources;

public class CustomerHomePresenter implements CustomerHomeContract.Presenter {

    private CustomerHomeContract.View view;

    @Override
    public void setView(CustomerHomeContract.View view) {
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
