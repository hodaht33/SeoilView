package seoil.capstone.som.ui.main.customer.bookmark;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkDTO;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 즐겨찾기 MVP 인터페이스
public interface CustomerBookmarkContract {

    interface Interactor extends BaseContract.Interactor {

        void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkDTO.ShopInfoRes> onFinishApiListener);
        void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkDTO.StatusRes> onFinishApiListener);
        void getOnGoingEvent(String userId, OnFinishApiListener<EventDataDTO.OngoingEventRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory, ArrayList<String> shopId);
        void deleteBookmark(int position);
        void intentDetailEvent(int eventCode);
        void setAdapterEvent(ArrayList<String> marketName, ArrayList<String> eventName, ArrayList<String> eventDate, ArrayList<Integer> eventCode);
    }

    interface Presenter extends BaseContract.Presenter<CustomerBookmarkContract.View> {

        void getBookmarkShopInfo(String userId);
    }
}
