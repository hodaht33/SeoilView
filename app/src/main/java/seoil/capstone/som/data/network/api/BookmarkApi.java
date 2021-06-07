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

// 즐겨찾기 api
public class BookmarkApi {

    // 즐겨찾기 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Bookmark mBookmarkData;

    public BookmarkApi(Retrofit retrofit) {

        mBookmarkData = retrofit.create(Bookmark.class);
    }

    // 점주 아이디 요청
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

    // 사용자 아이디 요청
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

    // 즐겨찾기 추가 요청
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

    // 즐겨찾기 삭제 요청
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
