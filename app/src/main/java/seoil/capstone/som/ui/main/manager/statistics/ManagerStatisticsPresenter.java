package seoil.capstone.som.ui.main.manager.statistics;

import android.content.Context;
import android.content.res.Resources;

public class ManagerStatisticsPresenter implements ManagerStatisticsContract.Presenter{

    private ManagerStatisticsContract.View view;

    @Override
    public void setView(ManagerStatisticsContract.View view) {
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
