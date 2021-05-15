package seoil.capstone.som.ui.main.manager.statistics;

import android.content.Context;
import android.content.res.Resources;

import seoil.capstone.som.data.network.OnFinishApiListener;

public class ManagerStatisticsPresenter implements ManagerStatisticsContract.Presenter{

    private ManagerStatisticsContract.View mView;
    private ManagerStatisticsInteractor mInteractor;

    @Override
    public void setView(ManagerStatisticsContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new ManagerStatisticsInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
    }

    public String getDateQuery(int year, int month, int day) {

        String dateQuery;
        if (day < 10) {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-0" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-0" + day;
            }

        } else {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-" + day;
            }
        }

        return dateQuery;
    }

    public void getSalesDate(String startDate, String endDate) {

        OnFinishApiListener onFinishApiListener = new OnFinishApiListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getSalesDate(startDate, endDate, onFinishApiListener);
    }
}
