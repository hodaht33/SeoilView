package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.model.StockData;

public interface Stock {

    @GET("stock/{shopId")
    Call<StockData.GetRes> getStock(@Path("shopId") String shopId);

    @POST("stock")
    Call<StockData.StatusRes> insertStock(@Body StockData.Req req);

    @PUT("stock")
    Call<StockData.StatusRes> updateStock(@Body StockData.Req req);

    @DELETE("stock/{shopId}")
    Call<StockData.StatusRes> deleteStock(@Path("shopId") String shopId);
}
