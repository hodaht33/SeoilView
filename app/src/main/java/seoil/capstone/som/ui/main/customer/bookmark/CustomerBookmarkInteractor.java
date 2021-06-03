package seoil.capstone.som.ui.main.customer.bookmark;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkData;
import seoil.capstone.som.data.network.model.EventData;

public class CustomerBookmarkInteractor implements CustomerBookmarkContract.Interactor{
    
    //DB에 매장 즐겨찾기 정보 조회
    @Override
    public void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkData.ShopInfoRes> onFinishApiListener) {

        AppApiHelper.getInstance().getBookmarkShopInfo(userId, onFinishApiListener);
    }

    //DB에 즐겨찾기 정보 삭제
    @Override
    public void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().deleteBookmark(userId, shopId, onFinishApiListener);
    }
    
    //DB에 진행중 이벤트 조회
    @Override
    public void getOnGoingEvent(String userId, OnFinishApiListener<EventData.OngoingEventRes> onFinishApiListener) {

        AppApiHelper.getInstance().getOngoingEvent(userId, onFinishApiListener);
    }

}
