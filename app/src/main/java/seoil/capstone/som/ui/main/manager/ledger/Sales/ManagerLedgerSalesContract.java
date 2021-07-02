package seoil.capstone.som.ui.main.manager.ledger.Sales;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;

// 가계부 매출 MVP 인터페이스
public interface ManagerLedgerSalesContract {

    interface Interactor extends BaseContract.Interactor {

        void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener);
        void getCost(String shopId, String dateQuery, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener);
        void insertSalesWithDate(String shopId, String name, int amount, String dateQuery ,OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener);
        void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener);
        void updateSpendingSales(int salesCode, String salesDate, String shopId, String salesName, int salesAmount, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setLayoutAdapterSales(ArrayList<String> listName, ArrayList<Integer> listAmount, ArrayList<Integer> autoInc);
        void initCost();
        void initSales();
    }

    interface Presenter extends BaseContract.Presenter<ManagerLedgerSalesContract.View> {

    }
}
