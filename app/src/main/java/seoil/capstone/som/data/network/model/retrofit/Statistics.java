package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.StatisticsData;

// 통계 api 라우팅 인터페이스
public interface Statistics {

    // 나이대 통계 요청
    @GET("statistics/{shopId}/ageGroup")
    Call<StatisticsData.GetAgeGroupRes> getAgeGroupStatistics(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    // 성별 통계 요청
    @GET("statistics/{shopId}/gender")
    Call<StatisticsData.GetGenderRes> getGenderStatistics(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    // 일별 매출 통계 요청
    @GET("statistics/{shopId}/sales/day")
    Call<StatisticsData.GetDayRes> getDailySales(@Path("shopId") String shopId, @Query("startDate") String starDate, @Query("endDate") String endDate);

    // 주별 매출 통계 요청
    @GET("statistics/{shopId}/sales/week")
    Call<StatisticsData.GetWeekRes> getWeeklySales(@Path("shopId") String shopId, @Query("month") String month, @Query("startDay") String startDay);

    // 월별 매출 통계 요청
    @GET("statistics/{shopId}/sales/month")
    Call<StatisticsData.GetMonthRes> getMonthlySales(@Path("shopId") String shopId, @Query("year") String year);

    // 통계(방문 정보) 추가 요청
    @POST("statistics")
    Call<StatisticsData.StatusRes> insertStatisticsData(@Body StatisticsData.InsertReq req);
}
