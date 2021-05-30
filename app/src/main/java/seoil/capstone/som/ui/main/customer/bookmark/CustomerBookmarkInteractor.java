package seoil.capstone.som.ui.main.customer.bookmark;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkInfo;
import seoil.capstone.som.data.network.model.ShopData;

public class CustomerBookmarkInteractor implements CustomerBookmarkContract.Interactor{
    
    //DB에 매장 즐겨찾기 정보 조회
    @Override
    public void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkInfo.ShopInfoRes> onFinishApiListener) {
        AppApiHelper.getInstance().getBookmarkShopInfo(userId, onFinishApiListener);
    }

}
