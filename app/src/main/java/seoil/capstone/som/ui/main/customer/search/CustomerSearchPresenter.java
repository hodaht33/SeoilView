package seoil.capstone.som.ui.main.customer.search;

import android.content.Context;
import android.content.res.Resources;

public class CustomerSearchPresenter implements CustomerSearchContract.Presenter {

    private CustomerSearchContract.View view;

    @Override
    public void setView(CustomerSearchContract.View view) {
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
