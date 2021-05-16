package seoil.capstone.som.ui.main.manager.statistics;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesData;

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

    public void getSalesDate(String shopId, String startDate, String endDate) {

        OnFinishApiListener<SalesData.GetRes> onFinishApiListener = new OnFinishApiListener<SalesData.GetRes>() {

            @Override
            public void onSuccess(SalesData.GetRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesData.GetRes.Result> list = getRes.getResults();

                    ArrayList<Integer> listAmounts = new ArrayList<>();
                    ArrayList<String> listDates = new ArrayList<>();
                    int c = 0;
                    for (SalesData.GetRes.Result result : list) {

                        listAmounts.add(c, result.getSalesAmount());
                        listDates.add(c, result.getSalesDate());
                        c++;
                    }
                    mView.sendSalesData(listDates, listAmounts);

                } else {


                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getSalesDate(shopId, startDate, endDate, onFinishApiListener);
    }
}
