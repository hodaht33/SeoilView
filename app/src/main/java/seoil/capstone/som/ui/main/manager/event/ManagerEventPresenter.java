package seoil.capstone.som.ui.main.manager.event;

import android.content.Context;
import android.content.res.Resources;

public class ManagerEventPresenter implements ManagerEventContract.Presenter {

    private ManagerEventContract.View view;

    @Override
    public void setView(ManagerEventContract.View view) {
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
