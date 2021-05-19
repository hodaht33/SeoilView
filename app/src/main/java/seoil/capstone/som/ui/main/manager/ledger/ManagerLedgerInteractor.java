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
    public void insertStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertStock(new StockData.Req(shopId, name, amount), onFinishApiListener);
    }

    @Override
    public void updateStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().updateStock(new StockData.Req(shopId, name, amount), onFinishApiListener);
    }

    @Override
    public void deleteStock(String shopId, String name, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteStock(shopId, name, onFinishApiListener);
    }

}
