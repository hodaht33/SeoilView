package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.SalesDTO;

// 매출 api 라우팅 인터페이스
public interface Sales {

    // 매장 수입 검색
    @GET("sales/{shopId}/income")
    Call<SalesDTO.GetRes> getIncomeSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    // 매장 지출 검색
    @GET("sales/{shopId}/spending")
    Call<SalesDTO.GetRes> getSpendingSales(@Path("shopId") String shopId, @Query("salesDate") String salesDate);

    // 매장 매출 통계 검색
    @GET("sales/{shopId}/statistics")
    Call<SalesDTO.GetStatisticsRes> getStatisticsSales(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    // 매장 매출 추가
    @POST("sales")
    Call<SalesDTO.StatusRes> insertSalesWithDate(@Body SalesDTO.Req res);

    // 매장 수입 추가
    @POST("sales/income")
    Call<SalesDTO.StatusRes> insertSales(@Body SalesDTO.Req req);

    // 매장 지출 수정
    @PUT("sales/spending")
    Call<SalesDTO.StatusRes> updateSpendingSales(@Body SalesDTO.Req req);

    // 매장 지출 삭제
    @DELETE("sales/{shopId}/spending")
    Call<SalesDTO.StatusRes> deleteSpendingSales(@Path("shopId") String shopId, @Query("salesCode") int salesCode, @Query("salesDate") String salesDate);
}
