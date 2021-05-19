package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.SalesData;

public interface Sales {

    @GET("sales/{shopId}/income")
    Call<SalesData.GetRes> getIncomeSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    @GET("sales/{shopId}/spending")
    Call<SalesData.GetRes> getSpendingSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    @GET("sales/{shopId}/statistics")
    Call<SalesData.GetStatisticsRes> getStatisticsSales(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @POST("sales")
    Call<SalesData.StatusRes> insertSalesWithDate(@Body SalesData.Req res);

    @POST("sales/income")
    Call<SalesData.StatusRes> insertSales(@Body SalesData.Req req);

    @PUT("sales/spending")
    Call<SalesData.StatusRes> updateSpendingSales(@Body SalesData.Req req);

    @DELETE("sales/{shpoId}/spending")
    Call<SalesData.StatusRes> deleteSpendingSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);
}
