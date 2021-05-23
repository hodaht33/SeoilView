package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.ShopData;

public interface Shop {

    @GET("shop/{shopId}/information")
    Call<ShopData.GetRes> getShopInformation(@Path("shopId") String shopId);

    @GET("shop/name")
    Call<ShopData.GetRes> getShopInfoWithKeyword(@Query("keyword") String keyword, @Query("page") int page);

    @GET("shop/category")
    Call<ShopData.GetRes> getShopInfoWithCategory(@Query("category") String category, @Query("page") int page);

    @POST("shop")
    Call<ShopData.StatusRes> insertShopInfo(@Body ShopData.InsertReq req);

    @PUT("shop")
    Call<ShopData.StatusRes> updateShopInfo(@Body ShopData.UpdateReq req);

    @DELETE("shop/{shopId}")
    Call<ShopData.StatusRes> deleteShopInfo(@Path("shopId") String shopId, @Query("shopCode") String shopCode);
}
