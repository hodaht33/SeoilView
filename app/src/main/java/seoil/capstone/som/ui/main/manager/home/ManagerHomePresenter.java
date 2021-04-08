package seoil.capstone.som.ui.main.manager.home;

import android.content.Context;
import android.content.res.Resources;

import seoil.capstone.som.ui.main.MainContract;

public class ManagerHomePresenter implements ManagerHomeContract.Presenter {

    private ManagerHomeContract.View view;
    private Context context;
    private Resources res;

    @Override
    public void setView(ManagerHomeContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void releaseContext() {
        this.context = null;
    }

    @Override
    public void setResources(Resources res) {
        this.res = res;
    }

    @Override
    public void releaseResources() {
        this.res = null;
    }
}
