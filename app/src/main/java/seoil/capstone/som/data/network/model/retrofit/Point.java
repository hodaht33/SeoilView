package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.PointData;

public interface Point {

    //////////////
    // 잔여 포인트 //
    //////////////

    // 현재 잔여 포인트 검색
    @GET("point/current-point")
    Call<PointData.GetCurrentRes> getCurrentPoint(@Query("id") String id);

    // 유저 추가 시 유저의 포인트 튜플 생성
    @POST("point/current-point")
    Call<PointData.StatusRes> insertPointTuple(@Body PointData.InsertCurrentReq req);

    // 잔여 포인트 수정
    @PUT("point/current-point")
    Call<PointData.StatusRes> updatePoint(@Body PointData.UpdateCurrentReq req);

    // 포인트 삭제(회원탈퇴)
    @DELETE("point/current-point")
    Call<PointData.StatusRes> deletePointTuple(@Query("id") String id);

    //////////////////
    // 사용 포인트 내역 //
    //////////////////

    // 포인트 사용 내역 검색
    @GET("point/using-point")
    Call<PointData.GetUsingRes> getUsingPoint(@Query("id") String id);

    // 포인트 사용 내역 추가
    @POST("point/using-point")
    Call<PointData.StatusRes> insertUsingPointTuple(@Body PointData.InsertUsingReq req);

    //////////////////
    // 적립 포인트 내역 //
    //////////////////

    // 포인트 적립 내역 검색
    @GET("point/save-point")
    Call<PointData.GetSaveRes> getSavePoint(@Query("id") String id);

    // 포인트 적립 내역 추가
    @POST("point/save-point")
    Call<PointData.StatusRes> insertSavePointTuple(@Body PointData.InsertSaveReq req);

    ///////////////////////
    // 사용 내역 + 적립 내역 //
    ///////////////////////

    // 포인트 사용 내역과 적립 내역 검색
    @GET("point/{userId}/using-and-save-point")
    Call<PointData.GetUsingAndSaveRes> getUsingAndSavePoint(@Path("userId") String userId);

}
