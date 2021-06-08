package seoil.capstone.som.ui.main.manager.statistics;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StatisticsDTO;

public class ManagerStatisticsInteractor implements ManagerStatisticsContract.Interactor {
    
    //DB에 일별 매출 통계 조회
    @Override
    public void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener<StatisticsDTO.GetDayRes> onFinishApiListener) {

        AppApiHelper.getInstance().getDailySales(shopId, starDate, endDate, onFinishApiListener);
    }

    //DB에 성별 통계 조회
    @Override
    public void getGenderTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener) {

        AppApiHelper.getInstance().getGenderStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    //DB에 나이대 별 통계 조회
    @Override
    public void getAgeTotal(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener) {

        AppApiHelper.getInstance().getAgeGroupStatistics(shopId, startDate, endDate, onFinishApiListener);
    }
}