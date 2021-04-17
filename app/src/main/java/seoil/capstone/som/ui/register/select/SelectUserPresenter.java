package seoil.capstone.som.ui.register.select;

import android.content.Context;
import android.content.res.Resources;

public class SelectUserPresenter implements SelectUserContract.Presenter {

    private SelectUserContract.View view;

    @Override
    public void setView(SelectUserContract.View view) {
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
