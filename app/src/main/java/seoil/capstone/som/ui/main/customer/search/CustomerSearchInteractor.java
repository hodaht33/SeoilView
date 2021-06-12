package seoil.capstone.som.ui.main.customer.search;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.ShopDTO;

public class CustomerSearchInteractor implements  CustomerSearchContract.Interactor{

    //가게 정보
    @Override
    public void getShop(String shopName, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInformation(shopName, onFinishApiListener);
    }

    //가계검색
    @Override
    public void getShopKeyword(String keyword, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInfoWithKeyword(keyword, page, onFinishApiListener);
    }

    //카테고리검색
    @Override
    public void getShopKeywordCategory(String category, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInfoWithCategory(category, page, onFinishApiListener);
    }

}
