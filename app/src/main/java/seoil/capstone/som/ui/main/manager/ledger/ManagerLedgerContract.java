package seoil.capstone.som.ui.main.manager.ledger;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StockDTO;

// 점주 가계부 MVP 인터페이스
public interface ManagerLedgerContract {

    interface Interactor extends BaseContract.Interactor {

        void getStock(String shopId, OnFinishApiListener<StockDTO.GetRes> onFinishApiListener);
        void insertStock(Integer stockCode,String shopId, String name, int amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);
        void updateStock(Integer stockCode, String shopId, String name, String newName, Integer amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);
        void deleteStock(String shopId, Integer stockCode, String name, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);

    }

    interface View extends BaseContract.View {

        void setLayoutAdapterStock(ArrayList<String> listName, ArrayList<Integer> listCode, ArrayList<Integer> listAmount);
        void initStock();
    }

    interface Presenter extends BaseContract.Presenter<ManagerLedgerContract.View> {

    }
}