package seoil.capstone.som.ui.main.customer.search;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopData;
import seoil.capstone.som.data.network.model.ShopData;

public interface CustomerSearchContract {

    interface Interactor extends BaseContract.Interactor{
        void getShop(String shopId, OnFinishApiListener<ShopData.GetRes> onFinishApiListener);

        void insertShop(String ShopId, String ShopName, String ShopAddress, OnFinishApiListener<ShopData.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void onRefresh();

        String getQueryString();

        void showLoadingSpinner(boolean show);

        void showSpinnerAtBottom(boolean show);

        void clearList();

        boolean isListEmpty();

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
