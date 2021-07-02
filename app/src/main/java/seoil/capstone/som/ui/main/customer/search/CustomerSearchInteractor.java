package seoil.capstone.som.ui.main.customer.search;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.ShopDTO;

public class CustomerSearchInteractor implements  CustomerSearchContract.Interactor{

    //DB에서 가계 검색
    @Override
    public void getShop(String shopName, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInformation(shopName, onFinishApiListener);
    }

    //DB에서 가게명으로 검색
    @Override
    public void getShopKeyword(String keyword, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInfoWithKeyword(keyword, page, onFinishApiListener);
    }

    //DB에서 카레고리로 검색
    @Override
    public void getShopKeywordCategory(String category, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getShopInfoWithCategory(category, page, onFinishApiListener);
    }

}
