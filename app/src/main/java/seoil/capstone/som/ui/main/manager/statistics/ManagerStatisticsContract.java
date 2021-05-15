package seoil.capstone.som.ui.main.manager.statistics;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesInfo;
import seoil.capstone.som.data.network.model.StockData;

public interface ManagerStatisticsContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<ManagerStatisticsContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getSalesDate(String startDate, String EndDate, OnFinishApiListener onFinishApiListener);
    }
}
