package seoil.capstone.som.data.network.uri;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.CurrentPointRequest;
import seoil.capstone.som.data.network.model.CurrentPointResponse;

public interface CurrentPointUri {

    // 현재 잔여 포인트 조회
    @GET("point/current-point")
    Call<CurrentPointResponse.GetRes> getCurrentPoint(@Query("id") String id);

    // 유저 추가 시 유저의 포인트 튜플 생성
    @POST("point/current-point")
    Call<CurrentPointResponse.StatusRes> createPointTuple(@Body CurrentPointRequest.PostReq req);

    // 비밀번호 변경
    @PUT("point/current-point")
    Call<CurrentPointResponse.StatusRes> updateUser(@Body CurrentPointRequest.PutReq req);

    // 회원 탈퇴
    @DELETE("point/current-point")
    Call<CurrentPointResponse.StatusRes> deletePointTuple(@Query("id") String id);
}
