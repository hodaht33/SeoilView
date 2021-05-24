package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.UserData;

public interface User {

    // 특정 유저의 정보 가져오기
    @POST("login")
    Call<Login.LoginRes> getLoginData(@Body Login.LoginReq req);

    @POST("phone-auth/send")
    Call<Auth.StatusRes> sendSms(@Body Auth.Req req);

    @POST("phone-auth/auth")
    Call<Auth.StatusRes> sendAuthCode(@Body Auth.Req req);

    @POST("phone-auth/auth/find/id")
    Call<UserData.FindIdRes> sendAuthForFindId(@Body Auth.Req req);

    @GET("user/information/{userId}/phone")
    Call<UserData.GetUserInfoRes> getUserPhoneNumber(@Path("userId") String userId);

    // 아이디 중복 확인
    @GET("duplicate/{id}")
    Call<Auth.StatusRes> checkIdDuplicate(@Path("id") String id);

    @GET("check-corpor/{number}")
    Call<Auth.StatusRes> checkRegistrationNumber(@Path("number") String number);

    // 유저 추가
    @POST("user/customer")
    Call<UserData.StatusRes> insertCustomer(@Body UserData.Customer request);

    @POST("user/manager")
    Call<UserData.StatusRes> insertManager(@Body UserData.Manager request);

    @PUT("user/{userId}/password")
    Call<UserData.StatusRes> updatePassword(@Path("userId") String userId, @Body UserData.ChangePasswordReq req);

    // 회원 탈퇴
    @DELETE("user/{id}")
    void deleteUser(@Path("id") String id);
}
