package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkData;
import seoil.capstone.som.data.network.model.retrofit.Bookmark;

public class BookmarkApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Bookmark mBookmarkData;

    public BookmarkApi(Retrofit retrofit) {

        mBookmarkData = retrofit.create(Bookmark.class);
    }

    public void getShopInfo(String userId, OnFinishApiListener<BookmarkData.ShopInfoRes> onFinishApiListener) {

        Call<BookmarkData.ShopInfoRes> call = mBookmarkData.getShopInfo(userId);
        call.enqueue(new Callback<BookmarkData.ShopInfoRes>() {
            @Override
            public void onResponse(Call<BookmarkData.ShopInfoRes> call, Response<BookmarkData.ShopInfoRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkData.ShopInfoRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getUserInfo(String shopId, OnFinishApiListener<BookmarkData.UserInfoRes> onFinishApiListener) {

        Call<BookmarkData.UserInfoRes> call = mBookmarkData.getUserInfo(shopId);
        call.enqueue(new Callback<BookmarkData.UserInfoRes>() {
            @Override
            public void onResponse(Call<BookmarkData.UserInfoRes> call, Response<BookmarkData.UserInfoRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkData.UserInfoRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getOngoingEvent(String userId, OnFinishApiListener onFinishApiListener) {

        Call<BookmarkData.OngoingEventRes> call = mBookmarkData.getOngoingEvent(userId);
        call.enqueue(new Callback<BookmarkData.OngoingEventRes>() {
            @Override
            public void onResponse(Call<BookmarkData.OngoingEventRes> call, Response<BookmarkData.OngoingEventRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkData.OngoingEventRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void addBookmark(BookmarkData.InsertReq createReq, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener) {

        Call<BookmarkData.StatusRes> call = mBookmarkData.addBookmark(createReq);
        call.enqueue(new Callback<BookmarkData.StatusRes>() {
            @Override
            public void onResponse(Call<BookmarkData.StatusRes> call, Response<BookmarkData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener) {

        Call<BookmarkData.StatusRes> call = mBookmarkData.deleteBookmark(userId, shopId);
        call.enqueue(new Callback<BookmarkData.StatusRes>() {
            @Override
            public void onResponse(Call<BookmarkData.StatusRes> call, Response<BookmarkData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
