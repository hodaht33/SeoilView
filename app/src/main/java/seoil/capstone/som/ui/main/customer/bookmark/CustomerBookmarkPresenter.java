package seoil.capstone.som.ui.main.customer.bookmark;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.BookmarkApi;
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.model.BookmarkInfo;
import seoil.capstone.som.data.network.model.ShopData;

public class CustomerBookmarkPresenter implements CustomerBookmarkContract.Presenter{

    private CustomerBookmarkContract.View mView;
    private CustomerBookmarkInteractor mInteractor;

    @Override
    public void setView(CustomerBookmarkContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerBookmarkInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    //Model에 사용자Id, 조회된 매장 정보 처리 방식을 전달
    @Override
    public void getBookmarkShopInfo(String userId) {

        //조회된 매장 즐겨찾기 정보를 view에 전달
        OnFinishApiListener<BookmarkInfo.ShopInfoRes> onFinishApiListener = new OnFinishApiListener<BookmarkInfo.ShopInfoRes>() {
            @Override
            public void onSuccess(BookmarkInfo.ShopInfoRes shopInfoRes) {

                ArrayList<String> shopName = new ArrayList<>();
                ArrayList<String> shopCategory = new ArrayList<>();

                if (shopInfoRes.getStatus() == BookmarkApi.SUCCESS) {

                    List<BookmarkInfo.ShopInfoRes.Result> list = shopInfoRes.getResults();

                    if (list == null) {

                        shopName.add("즐겨찾기한 매장이 없습니다.");
                        shopCategory.add("");
                    } else {

                        for (BookmarkInfo.ShopInfoRes.Result result : list) {

                            shopName.add(result.getShopName());
                            shopCategory.add(result.getShopCategoory());
                        }
                    }
                    mView.setAdapterShopInfo(shopName, shopCategory);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getBookmarkShopInfo(userId, onFinishApiListener);
    }

}
