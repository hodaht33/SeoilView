package seoil.capstone.som.ui.main.manager.ledger;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StockDTO;

// 가계부 모델
public class ManagerLedgerInteractor implements ManagerLedgerContract.Interactor{

    //DB에 재고 조회
    @Override
    public void getStock(String shopId, OnFinishApiListener<StockDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getStock(shopId, onFinishApiListener);
    }
    
    //DB에 재고 삽입
    @Override
    public void insertStock(Integer stockCode,String shopId, String name, int amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertStock(new StockDTO.Req(stockCode, shopId, name, amount), onFinishApiListener);
    }
    
    //DB에 재고 갱신
    @Override
    public void updateStock(Integer stockCode, String shopId, String name, String newName, Integer amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().updateStock(new StockDTO.UpdateAllReq(stockCode, shopId, name, newName, amount), onFinishApiListener);
    }

    //DB에 재고 삭제
    @Override
    public void deleteStock(String shopId, Integer stockCode, String name, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteStock(shopId, stockCode ,name, onFinishApiListener);
    }

}
