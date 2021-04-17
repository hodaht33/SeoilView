package seoil.capstone.som.ui.register;

import android.content.Context;
import android.content.res.Resources;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;

    @Override
    public void setView(RegisterContract.View view) {
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
