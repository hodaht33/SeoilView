package seoil.capstone.som.ui.main.customer.search;

import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopDTO;
import seoil.capstone.som.data.network.model.StockDTO;
import seoil.capstone.som.ui.main.customer.point.CustomerPointAdapter;

public interface CustomerSearchContract {

    interface Interactor extends BaseContract.Interactor{

        //DB에 가계조회
        void getShop(String shopId, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);

        //DB에서 keyword 조회

        void getShopKeyword(String keyword, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);


        //DB에서 Category 조회
        void getShopKeywordCategory(String category, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        String getQueryString();

        boolean isListEmpty();

        void initShopKeyword();

        void initShopKeywordCategory();

        void initShop();

        void showProgress();

        void hideProgress();

        void setLayoutAdapterShop(ArrayList<String> listShopName, ArrayList<String> listShopAddress);
    }

    interface  Presenter extends BaseContract.Presenter<CustomerSearchContract.View> {

        void setView(View view);

        void releaseView();

        void createInteractor();

        void releaseInteractor();

    }
}
