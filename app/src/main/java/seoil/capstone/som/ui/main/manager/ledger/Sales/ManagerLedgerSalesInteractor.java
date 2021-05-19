package seoil.capstone.som.ui.main.manager.ledger.Sales;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;

public class ManagerLedgerSalesInteractor implements ManagerLedgerSalesContract.Interactor{

    @Override
    public void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getIncomeSales(shopId, dateQuery, onFinishApiListener);
    }

    @Override
    public void insertSalesWithDate(String shopId, String name, int amount, String dateQuery ,OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        //salesCode 사용 안함 (자동 생성)
        AppApiHelper.getInstance().insertSalesWithDate(new SalesData.Req(-1 ,dateQuery, shopId, name, amount), onFinishApiListener);
    }

    @Override
    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteSpendingSales(shopId, salesCode, salesDate, onFinishApiListener);
    }

    @Override
    public void updateSpendingSales(int salesCode, String salesDate, String shopId, String salesName, int salesAmount, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().updateSpendingSales(new SalesData.Req(salesCode, salesDate, shopId, salesName, salesAmount), onFinishApiListener);
    }

    @Override
    public void getCost(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSpendingSales(shopId, dateQuery, onFinishApiListener);
    }
}
