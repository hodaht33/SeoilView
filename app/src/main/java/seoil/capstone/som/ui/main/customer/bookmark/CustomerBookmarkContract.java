package seoil.capstone.som.ui.main.customer.bookmark;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkInfo;

public interface CustomerBookmarkContract {

    interface View extends BaseContract.View {

        void setAdapterShopInfo(ArrayList<String> shopName, ArrayList<String> shopCategory);
    }

    interface Presenter extends BaseContract.Presenter<CustomerBookmarkContract.View> {

        void getBookmarkShopInfo(String userId);
    }

    interface Interactor extends BaseContract.Interactor {

        void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkInfo.ShopInfoRes> onFinishApiListener);

    }
}
