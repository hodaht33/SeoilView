package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.ShopInfo;

public interface Shop {

    @GET("shop")
    Call<ShopInfo.GetRes> getShopInfo(@Query("id") String id);

    @POST("shop")
    Call<ShopInfo.StatusRes> insertShopInfo(@Body ShopInfo.InsertReq req);
}
