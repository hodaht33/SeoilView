package seoil.capstone.som.ui.main.customer.search;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopDTO;

// 검색 MVP 인터페이스
public interface CustomerSearchContract {

    interface Interactor extends BaseContract.Interactor{

        void getShop(String shopId, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);
        void getShopKeyword(String keyword, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);
        void getShopKeywordCategory(String category, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setLayoutAdapterShop(ArrayList<String> listShopName, ArrayList<String> listShopAddress);
    }

    interface Presenter extends BaseContract.Presenter<CustomerSearchContract.View> {

        void getShop(String shopName);
        void getShopKeyword(String keyword, int page);
        void getShopKeywordCategory(String category, int page);
    }
}
