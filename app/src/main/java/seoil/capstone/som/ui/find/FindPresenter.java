package seoil.capstone.som.ui.find;

import android.content.Context;
import android.content.res.Resources;

public class FindPresenter implements FindContract.Preseneter {

    private FindContract.View view;

    @Override
    public void setView(FindContract.View view) {
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
