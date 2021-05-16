package seoil.capstone.som.ui.main.manager.ledger;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

public class ManagerLedgerInteractor implements ManagerLedgerContract.Interactor{


    @Override
    public void getStock(String shopId, OnFinishApiListener<StockData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getStock(shopId, onFinishApiListener);
    }

    @Override
    public void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSalesData(shopId, dateQuery, onFinishApiListener);
    }
}
