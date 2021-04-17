package seoil.capstone.som.ui.main.manager.home;

import android.content.Context;
import android.content.res.Resources;

public class ManagerHomePresenter implements ManagerHomeContract.Presenter {

    private ManagerHomeContract.View view;

    @Override
    public void setView(ManagerHomeContract.View view) {
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
