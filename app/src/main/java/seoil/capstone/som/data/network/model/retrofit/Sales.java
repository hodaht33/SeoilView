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

// 매출 api 라우팅 인터페이스
public interface Sales {

    // 매장 수입 검색
    @GET("sales/{shopId}/income")
    Call<SalesData.GetRes> getIncomeSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    // 매장 지출 검색
    @GET("sales/{shopId}/spending")
    Call<SalesData.GetRes> getSpendingSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    // 매장 매출 통계 검색
    @GET("sales/{shopId}/statistics")
    Call<SalesData.GetStatisticsRes> getStatisticsSales(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    // 매장 매출 추가
    @POST("sales")
    Call<SalesData.StatusRes> insertSalesWithDate(@Body SalesData.Req res);

    // 매장 수입 추가
    @POST("sales/income")
    Call<SalesData.StatusRes> insertSales(@Body SalesData.Req req);

    // 매장 지출 수정
    @PUT("sales/spending")
    Call<SalesData.StatusRes> updateSpendingSales(@Body SalesData.Req req);

    // 매장 지출 삭제
    @DELETE("sales/{shopId}/spending")
    Call<SalesData.StatusRes> deleteSpendingSales(@Path("shopId") String shopId, @Query("salesCode") int salesCode, @Query("salesDate") String salesDate);
}
