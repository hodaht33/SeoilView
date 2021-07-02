package seoil.capstone.som.ui.main.manager.statistics;

import com.anychart.chart.common.dataentry.DataEntry;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StatisticsDTO;

// 점주 통계 MVP 인터페이스
public interface ManagerStatisticsContract {
    
    interface View extends BaseContract.View {

        void setGenderChart(ArrayList<DataEntry> genderData);
        void setAgeChart(ArrayList<DataEntry> ageData);
        void setAdapterDaily(ArrayList<ManagerStatisticsAdapter.Item> salesData);
    }
    
    interface Presenter extends BaseContract.Presenter<ManagerStatisticsContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener<StatisticsDTO.GetDayRes> onFinishApiListener);
        void getGenderTotal (String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener);
        void getAgeTotal (String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener);
    }
}