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

        AppApiHelper.getInstance().getIncomeSales(shopId, dateQuery, onFinishApiListener);
    }

    @Override
    public void getCost(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSpendingSales(shopId, dateQuery, onFinishApiListener);
    }

    @Override
    public void setSales(String shopId, String name, int amount, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertSalesData(new SalesData.InsertReq(shopId, name, amount), onFinishApiListener);
    }

    @Override
    public void setStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertStock(new StockData.Req(shopId, name, amount), onFinishApiListener);
    }
}
