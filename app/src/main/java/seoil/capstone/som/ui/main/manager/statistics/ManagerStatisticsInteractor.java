package seoil.capstone.som.ui.main.manager.statistics;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StatisticsData;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerContract;

public class ManagerStatisticsInteractor implements ManagerStatisticsContract.Interactor {


    @Override
    public void getSalesStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetStatisticsRes> onFinishApiListener) {

        AppApiHelper.getInstance().getStatisticsSales(shopId, startDate, endDate, onFinishApiListener);
    }

    @Override
    public void getGenderTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetGenderRes> onFinishApiListener) {

        AppApiHelper.getInstance().getGenderStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    @Override
    public void getAgeTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetAgeGroupRes> onFinishApiListener) {

        AppApiHelper.getInstance().getAgeGroupStatistics(shopId, startDate, endDate, onFinishApiListener);
    }
}
