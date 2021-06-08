package seoil.capstone.som.ui.main.customer.bookmark;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.BookmarkApi;
import seoil.capstone.som.data.network.model.BookmarkDTO;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 손님 즐겨찾기 프레젠터
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
        OnFinishApiListener<BookmarkDTO.ShopInfoRes> onFinishApiListener = new OnFinishApiListener<BookmarkDTO.ShopInfoRes>() {
            @Override
            public void onSuccess(BookmarkDTO.ShopInfoRes shopInfoRes) {

                ArrayList<String> shopName = new ArrayList<>();
                ArrayList<String> shopCategory = new ArrayList<>();
                ArrayList<String> shopId = new ArrayList<>();

                if (shopInfoRes.getStatus() == BookmarkApi.SUCCESS) {

                    List<BookmarkDTO.ShopInfoRes.Result> list = shopInfoRes.getResults();

                    if (list != null) {

                        for (BookmarkDTO.ShopInfoRes.Result result : list) {

                            shopName.add(result.getShopName());
                            shopCategory.add(result.getShopCategoory());
                            shopId.add(result.getShopId());
                        }

                        mView.setAdapterShopInfo(shopName, shopCategory, shopId);
                        return;
                    }

                }

                mView.setAdapterShopInfo(null, null, null);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getBookmarkShopInfo(userId, onFinishApiListener);
    }

    //즐겨찾기 삭제
    public void deleteBookmark(String userId, String shopId, int position) {

        OnFinishApiListener<BookmarkDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<BookmarkDTO.StatusRes>() {
            @Override
            public void onSuccess(BookmarkDTO.StatusRes statusRes) {

                if (statusRes.getStatus() == BookmarkApi.SUCCESS) {

                    mView.deleteBookmark(position);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("deleteBookmark", t.getMessage());
            }
        };
        mInteractor.deleteBookmark(userId, shopId, onFinishApiListener);
    }

    //이벤트 상세 정보 액티비티로 이동
    public void intentDetailEventCustomer(int eventCode) {

        mView.intentDetailEvent(eventCode);
    }

    //즐겨찾기된 매장의 진행중 이벤트 조회
    public void getOnGoingEvent(String userId) {

        OnFinishApiListener<EventDataDTO.OngoingEventRes> onFinishApiListener = new OnFinishApiListener<EventDataDTO.OngoingEventRes>() {
            @Override
            public void onSuccess(EventDataDTO.OngoingEventRes ongoingEventRes) {

                Log.d("ongoingEvent", String.valueOf(ongoingEventRes.getStatus()));

                if (ongoingEventRes.getStatus() == BookmarkApi.SUCCESS) {

                    ArrayList<Integer> eventCode = new ArrayList<>();
                    ArrayList<String> marketName = new ArrayList<>();
                    ArrayList<String> marketEventName = new ArrayList<>();
                    ArrayList<String> marketEventDate = new ArrayList<>();

                    List<EventDataDTO.OngoingEventRes.Result> list = ongoingEventRes.getResults();

                    if (list != null) {

                        for (EventDataDTO.OngoingEventRes.Result result : list) {

                            eventCode.add(result.getEventCode());
                            marketName.add(result.getShopName());
                            marketEventName.add(result.getEventName());
                            final String temp = result.getStartDate() + "~" + result.getEndDate();
                            marketEventDate.add(temp);
                        }
                    }

                    mView.setAdapterEvent(marketName, marketEventName, marketEventDate, eventCode);
                } else {

                    mView.setAdapterEvent(null, null, null, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("ongoingEvent", t.getMessage());
            }
        };

        mInteractor.getOnGoingEvent(userId, onFinishApiListener);
    }
}
