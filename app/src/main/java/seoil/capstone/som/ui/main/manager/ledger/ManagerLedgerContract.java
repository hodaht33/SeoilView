package seoil.capstone.som.ui.main.manager.ledger;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

public interface ManagerLedgerContract {

    interface Interactor extends BaseContract.Interactor {

        void getStock(String shopId, OnFinishApiListener<StockData.GetRes> onFinishApiListener);

        void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener);

        void getCost(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener);

        void insertSalesWithDate(String shopId, String name, int amount, String dateQuery ,OnFinishApiListener<SalesData.StatusRes> onFinishApiListener);

        void setStock(String shopId, String name, int amount, OnFinishApiListener<StockData.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setLayoutAdapter(ArrayList<String> listName, ArrayList<String> listAmount);

        void initSales();

        void initStock();
    }

    interface Presenter extends BaseContract.Presenter<ManagerLedgerContract.View> {

    }
}