package seoil.capstone.som.ui.main.manager.statistics;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerContract;

public class ManagerStatisticsInteractor implements ManagerStatisticsContract.Interactor {


    @Override
    public void getSalesDate(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSalesStatistics(shopId, startDate, endDate, onFinishApiListener);
    }
}
