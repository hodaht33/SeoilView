package seoil.capstone.som.ui.main.manager.ledger;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StockDTO;

public interface ManagerLedgerContract {

    interface Interactor extends BaseContract.Interactor {

        void getStock(String shopId, OnFinishApiListener<StockDTO.GetRes> onFinishApiListener);


        void insertStock(String shopId, String name, int amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);

        void updateStock(String shopId, String name, int amount, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);

        void deleteStock(String shopId, String name, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener);

    }

    interface View extends BaseContract.View {

        void setLayoutAdapterStock(ArrayList<String> listName, ArrayList<String> listAmount);

        void initStock();
    }

    interface Presenter extends BaseContract.Presenter<ManagerLedgerContract.View> {

    }
}