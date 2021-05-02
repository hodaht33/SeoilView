package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.SalesInfo;

public interface Sales {

    @GET("sales")
    Call<SalesInfo.GetRes> getSalesInfo(@Query("id") String id);

    @POST("sales")
    Call<SalesInfo.StatusRes> insertSalesInfo(@Body SalesInfo.InsertReq req);
}
