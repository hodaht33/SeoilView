package seoil.capstone.som.ui.main.manager.statistics;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StatisticsData;
import seoil.capstone.som.data.network.model.StockData;

public interface ManagerStatisticsContract {

    interface View extends BaseContract.View {

        void sendSalesData(ArrayList<String> listDates, ArrayList<Integer> listAmounts);

        void sendGenderTotal(ArrayList<String> genderList, ArrayList<Integer> amountList);

        void sendAgeTotal(ArrayList<String> ageList, ArrayList<Integer> amountList);
    }

    interface Presenter extends BaseContract.Presenter<ManagerStatisticsContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getStatisticsSales(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetStatisticsRes> onFinishApiListener);

        void getGenderTotal (String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetGenderRes> onFinishApiListener);

        void getAgeTotal (String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetAgeGroupRes> onFinishApiListener);
    }
}
