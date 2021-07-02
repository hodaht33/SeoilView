package seoil.capstone.som.ui.main.manager.ledger.Sales;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;

// 가계부 매출 모델
public class ManagerLedgerSalesInteractor implements ManagerLedgerSalesContract.Interactor{

    //지출 조회
    @Override
    public void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getIncomeSales(shopId, dateQuery, onFinishApiListener);
    }

    //매출 삽입
    @Override
    public void insertSalesWithDate(String shopId, String name, int amount, String dateQuery ,OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        //salesCode 사용 안함 (자동 생성)
        AppApiHelper.getInstance().insertSalesWithDate(new SalesDTO.Req(-1 ,dateQuery, shopId, name, amount), onFinishApiListener);
    }

    //매출 삭제
    @Override
    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteSpendingSales(shopId, salesCode, salesDate, onFinishApiListener);
    }

    //매출 갱신
    @Override
    public void updateSpendingSales(int salesCode, String salesDate, String shopId, String salesName, int salesAmount, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().updateSpendingSales(new SalesDTO.Req(salesCode, salesDate, shopId, salesName, salesAmount), onFinishApiListener);
    }

    //매출 조회
    @Override
    public void getCost(String shopId, String dateQuery, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSpendingSales(shopId, dateQuery, onFinishApiListener);
    }
}
