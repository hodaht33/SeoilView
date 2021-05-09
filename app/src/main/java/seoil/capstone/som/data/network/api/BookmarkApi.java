package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.BookmarkInfo;
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

    public void getShopInfo(String userId, OnFinishApiListener<BookmarkInfo.ShopInfoRes> onFinishApiListener) {

        Call<BookmarkInfo.ShopInfoRes> call = mBookmarkData.getShopInfo(userId);
        call.enqueue(new Callback<BookmarkInfo.ShopInfoRes>() {
            @Override
            public void onResponse(Call<BookmarkInfo.ShopInfoRes> call, Response<BookmarkInfo.ShopInfoRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkInfo.ShopInfoRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getUserInfo(String shopId, OnFinishApiListener<BookmarkInfo.UserInfoRes> onFinishApiListener) {

        Call<BookmarkInfo.UserInfoRes> call = mBookmarkData.getUserInfo(shopId);
        call.enqueue(new Callback<BookmarkInfo.UserInfoRes>() {
            @Override
            public void onResponse(Call<BookmarkInfo.UserInfoRes> call, Response<BookmarkInfo.UserInfoRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkInfo.UserInfoRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void addBookmark(BookmarkInfo.InsertReq createReq, OnFinishApiListener<BookmarkInfo.StatusRes> onFinishApiListener) {

        Call<BookmarkInfo.StatusRes> call = mBookmarkData.addBookmark(createReq);
        call.enqueue(new Callback<BookmarkInfo.StatusRes>() {
            @Override
            public void onResponse(Call<BookmarkInfo.StatusRes> call, Response<BookmarkInfo.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkInfo.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteBookmark(String userId, String shopCode, String shopId, OnFinishApiListener<BookmarkInfo.StatusRes> onFinishApiListener) {

        Call<BookmarkInfo.StatusRes> call = mBookmarkData.deleteBookmark(userId, shopCode, shopId);
        call.enqueue(new Callback<BookmarkInfo.StatusRes>() {
            @Override
            public void onResponse(Call<BookmarkInfo.StatusRes> call, Response<BookmarkInfo.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BookmarkInfo.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
