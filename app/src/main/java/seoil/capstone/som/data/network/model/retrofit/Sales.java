package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.SalesInfo;

public interface Sales {

    // date에 null을 전달하면 최근 매출 발생부터 모두 가져옴
    @GET("sales/{shopId}")
    Call<SalesInfo.GetRes> getSalesInfo(@Path("shopId") String shopId, @Query("salesDate") String date);

    @POST("sales")
    Call<SalesInfo.StatusRes> insertSalesInfo(@Body SalesInfo.InsertReq req);
}
