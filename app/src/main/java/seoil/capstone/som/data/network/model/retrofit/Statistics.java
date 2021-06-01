package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.StatisticsData;

public interface Statistics {

    @GET("statistics/{shopId}/ageGroup")
    Call<StatisticsData.GetAgeGroupRes> getAgeGroupStatistics(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("statistics/{shopId}/gender")
    Call<StatisticsData.GetGenderRes> getGenderStatistics(@Path("shopId") String shopId, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("statistics/{shopId}/sales/day")
    Call<StatisticsData.GetDayRes> getDailySales(@Path("shopId") String shopId, @Query("startDate") String starDate, @Query("endDate") String endDate);

    @GET("statistics/{shopId}/sales/week")
    Call<StatisticsData.GetWeekRes> getWeeklySales(@Path("shopId") String shopId, @Query("month") String month, @Query("startDay") String startDay);

    @GET("statistics/{shopId}/sales/month")
    Call<StatisticsData.GetMonthRes> getMonthlySales(@Path("shopId") String shopId, @Query("year") String year);

    @POST("statistics")
    Call<StatisticsData.StatusRes> insertStatisticsData(@Body StatisticsData.InsertReq req);
}
