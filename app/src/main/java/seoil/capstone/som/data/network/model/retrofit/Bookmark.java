package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.BookmarkInfo;

public interface Bookmark {

    @GET("bookmark/{id}/shop")
    Call<BookmarkInfo.ShopInfoRes> getShopInfo(@Path("id") String id);

    @GET("bookmark/{shopId}/user")
    Call<BookmarkInfo.UserInfoRes> getUserInfo(@Path("shopId") String shopId);

    @POST("bookmark")
    Call<BookmarkInfo.StatusRes> addBookmark(@Body BookmarkInfo.InsertReq createReq);

    @DELETE("bookmark/{userId}")
    Call<BookmarkInfo.StatusRes> deleteBookmark(@Path("userId") String userId, @Query("shopCode") String shopCode, @Query("shopId") String shopId);
}
