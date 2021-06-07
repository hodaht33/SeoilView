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

// 사용자 api 라우팅 인터페이스
public interface User {

    // 로그인 된 사용자 정보 요청
    @POST("login")
    Call<Login.LoginRes> getLoginData(@Body Login.LoginReq req);

    // 인증번호 문자 전송 요청
    @POST("phone-auth/send")
    Call<Auth.StatusRes> sendSms(@Body Auth.Req req);

    // 인증번호 확인 요청
    @POST("phone-auth/auth")
    Call<Auth.StatusRes> sendAuthCode(@Body Auth.Req req);

    // 아이디 찾기 요청
    @POST("phone-auth/auth/find/id")
    Call<UserData.FindIdRes> sendAuthForFindId(@Body Auth.Req req);

    // 사용자 핸드폰 번호 요청
    @GET("user/information/{userId}/phone")
    Call<UserData.GetUserInfoRes> getUserPhoneNumber(@Path("userId") String userId);

    // 아이디 중복 확인 요청
    @GET("duplicate/{id}")
    Call<Auth.StatusRes> checkIdDuplicate(@Path("id") String id);

    // 사업자 등록 번호 확인 요청
    @GET("check-corpor/{number}")
    Call<Auth.StatusRes> checkRegistrationNumber(@Path("number") String number);

    // 손님 추가 요청
    @POST("user/customer")
    Call<UserData.StatusRes> insertCustomer(@Body UserData.Customer request);

    // 점주 추가 요청
    @POST("user/manager")
    Call<UserData.StatusRes> insertManager(@Body UserData.Manager request);

    // 비밀번호 수정 요청
    @PUT("user/{userId}/password")
    Call<UserData.StatusRes> updatePassword(@Path("userId") String userId, @Body UserData.ChangePasswordReq req);

    // 사용자 삭제 요청
    @DELETE("user/{id}")
    Call<UserData.StatusRes> deleteUser(@Path("id") String id);
}
