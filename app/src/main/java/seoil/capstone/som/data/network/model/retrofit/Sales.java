package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.SalesData;

public interface Sales {

    // date에 null을 전달하면 최근 매출 발생부터 모두 가져옴
    @GET("sales/{shopId}")
    Call<SalesData.GetRes> getSalesData(@Path("shopId") String shopId, @Query("salesDate") String date);

    @GET("sales/{shopId}/statistics")
    Call<SalesData.GetRes> getSalesStatistics(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @POST("sales")
    Call<SalesData.StatusRes> insertSalesData(@Body SalesData.InsertReq req);
}
