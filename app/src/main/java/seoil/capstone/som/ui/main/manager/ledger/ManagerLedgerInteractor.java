package seoil.capstone.som.ui.main.manager.ledger;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

public class ManagerLedgerInteractor implements ManagerLedgerContract.Interactor{

    //DB에 재고 조회
    @Override
    public void getStock(String shopId, OnFinishApiListener<StockData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getStock(shopId, onFinishApiListener);
    }
    
    //DB에 재고 삽입
    @Override
    public void insertStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertStock(new StockData.Req(shopId, name, amount), onFinishApiListener);
    }
    
    //DB에 재고 갱신
    @Override
    public void updateStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().updateStock(new StockData.Req(shopId, name, amount), onFinishApiListener);
    }

    //DB에 재고 삭제
    @Override
    public void deleteStock(String shopId, String name, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteStock(shopId, name, onFinishApiListener);
    }

}
