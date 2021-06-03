package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.BookmarkData;

public interface Bookmark {

    @GET("bookmark/{id}/shop")
    Call<BookmarkData.ShopInfoRes> getShopInfo(@Path("id") String id);

    @GET("bookmark/{shopId}/user")
    Call<BookmarkData.UserInfoRes> getUserInfo(@Path("shopId") String shopId);

    @GET("bookmark/{userId}/ongoing-event")
    Call<BookmarkData.OngoingEventRes> getOngoingEvent(@Path("userId") String userId);

    @POST("bookmark")
    Call<BookmarkData.StatusRes> addBookmark(@Body BookmarkData.InsertReq createReq);

    @DELETE("bookmark/{userId}")
    Call<BookmarkData.StatusRes> deleteBookmark(@Path("userId") String userId, @Query("shopId") String shopId);
}
