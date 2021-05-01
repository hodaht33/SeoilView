package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.CurrentPoint;
import seoil.capstone.som.data.network.model.UsingPoint;

public interface Point {

    //////////////
    // 잔여 포인트 //
    //////////////

    // 현재 잔여 포인트 조회
    @GET("point/current-point")
    Call<CurrentPoint.GetRes> getCurrentPoint(@Query("id") String id);

    // 유저 추가 시 유저의 포인트 튜플 생성
    @POST("point/current-point")
    Call<CurrentPoint.StatusRes> insertPointTuple(@Body CurrentPoint.InsertReq req);

    // 비밀번호 변경
    @PUT("point/current-point")
    Call<CurrentPoint.StatusRes> updatePoint(@Body CurrentPoint.PutReq req);

    // 회원 탈퇴
    @DELETE("point/current-point")
    Call<CurrentPoint.StatusRes> deletePointTuple(@Query("id") String id);

    //////////////////
    // 사용 포인트 내역 //
    //////////////////

    @GET("point/using-point")
    Call<UsingPoint.GetRes> getUsingPoint(@Query("id") String id);

    @POST("point/using-point")
    Call<UsingPoint.StatusRes> insertUsingPointTuple(@Body UsingPoint.InsertReq req);

    //////////////////
    // 적립 포인트 내역 //
    //////////////////


}
