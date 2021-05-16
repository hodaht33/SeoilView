package seoil.capstone.som.ui.main.manager.ledger;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

public interface ManagerLedgerContract {

    interface Interactor extends BaseContract.Interactor {

        void getStock(String shopId, OnFinishApiListener<StockData.GetRes> onFinishApiListener);

        void getSales(String shopId, String dateQuery, OnFinishApiListener<SalesData.GetRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setSales(int value);
        void setSaleError(String s);

        void setLayoutAdpater(ArrayList<String> listName, ArrayList<Integer> listAmount);

    }

    interface Presenter extends BaseContract.Presenter<ManagerLedgerContract.View> {
        
    }
}
