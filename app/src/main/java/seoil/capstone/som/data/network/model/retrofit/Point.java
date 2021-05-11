package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.PointData;

public interface Point {

    //////////////
    // 잔여 포인트 //
    //////////////

    // 현재 잔여 포인트 조회
    @GET("point/current-point")
    Call<PointData.GetCurrentRes> getCurrentPoint(@Query("id") String id);

    // 유저 추가 시 유저의 포인트 튜플 생성
    @POST("point/current-point")
    Call<PointData.StatusRes> insertPointTuple(@Body PointData.InsertCurrentReq req);

    // 잔여 포인트 변경
    @PUT("point/current-point")
    Call<PointData.StatusRes> updatePoint(@Body PointData.UpdateCurrentReq req);

    // 회원 탈퇴
    @DELETE("point/current-point")
    Call<PointData.StatusRes> deletePointTuple(@Query("id") String id);

    //////////////////
    // 사용 포인트 내역 //
    //////////////////

    @GET("point/using-point")
    Call<PointData.GetUsingRes> getUsingPoint(@Query("id") String id);

    @POST("point/using-point")
    Call<PointData.StatusRes> insertUsingPointTuple(@Body PointData.InsertUsingReq req);

    //////////////////
    // 적립 포인트 내역 //
    //////////////////

    @GET("point/save-point")
    Call<PointData.GetSaveRes> getSavePoint(@Query("id") String id);
    @POST("point/save-point")
    Call<PointData.StatusRes> insertSavePointTuple(@Body PointData.InsertSaveReq req);
}
