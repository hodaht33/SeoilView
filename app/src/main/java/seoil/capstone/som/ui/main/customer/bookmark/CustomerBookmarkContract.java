package seoil.capstone.som.ui.main.customer.bookmark;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkData;
import seoil.capstone.som.data.network.model.EventData;

public interface CustomerBookmarkContract {

    interface View extends BaseContract.View {

        void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory, ArrayList<String> shopId);

        void deleteBookmark(int position);

        void intentDetailEvent(int eventCode);

        void setAdapterEvent(ArrayList<String> marketName, ArrayList<String> eventName, ArrayList<String> eventDate, ArrayList<Integer> eventCode);

        void createAlert(int position);
    }

    interface Presenter extends BaseContract.Presenter<CustomerBookmarkContract.View> {

        void getBookmarkShopInfo(String userId);
    }

    interface Interactor extends BaseContract.Interactor {

        void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkData.ShopInfoRes> onFinishApiListener);

        void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener);

        void getOnGoingEvent(String userId, OnFinishApiListener<EventData.OngoingEventRes> onFinishApiListener);
    }
}
