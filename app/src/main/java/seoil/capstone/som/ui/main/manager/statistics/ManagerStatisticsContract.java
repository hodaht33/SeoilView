package seoil.capstone.som.ui.main.manager.statistics;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

public interface ManagerStatisticsContract {

    interface View extends BaseContract.View {

        void sendSalesData(ArrayList<String> listDates, ArrayList<Integer> listAmounts);
    }

    interface Presenter extends BaseContract.Presenter<ManagerStatisticsContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getSalesDate(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener);
    }
}
