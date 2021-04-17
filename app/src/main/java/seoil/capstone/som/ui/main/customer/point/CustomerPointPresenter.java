package seoil.capstone.som.ui.main.customer.point;

import android.content.Context;
import android.content.res.Resources;

public class CustomerPointPresenter implements CustomerPointContract.Presenter {

    private CustomerPointContract.View view;

    @Override
    public void setView(CustomerPointContract.View view) {
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
