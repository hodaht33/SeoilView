package seoil.capstone.som.ui.main.manager.statistics;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.StatisticsDTO;

public class ManagerStatisticsInteractor implements ManagerStatisticsContract.Interactor {


    @Override
    public void getSalesStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<SalesDTO.GetStatisticsRes> onFinishApiListener) {

        AppApiHelper.getInstance().getStatisticsSales(shopId, startDate, endDate, onFinishApiListener);
    }

    @Override
    public void getGenderTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener) {

        AppApiHelper.getInstance().getGenderStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    @Override
    public void getAgeTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener) {

        AppApiHelper.getInstance().getAgeGroupStatistics(shopId, startDate, endDate, onFinishApiListener);
    }
}