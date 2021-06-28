package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.LoginDTO;
import seoil.capstone.som.data.network.model.UserDTO;

// 사용자 api 라우팅 인터페이스
public interface User {

    // 로그인 된 사용자 정보 요청
    @POST("login")
    Call<LoginDTO.LoginRes> getLoginData(@Body LoginDTO.LoginReq req);

    // POS기 로그인
    // getLoginData와는 다르게 pos기는 점주만 로그인 가능하므로 code가 정해져있어 받아오지 않음
    @POST("login/pos")
    Call<LoginDTO.LoginRes> getPosLoginData(@Body LoginDTO.LoginReq req);

    // 인증번호 문자 전송 요청
    @POST("phone-auth/send")
    Call<AuthDTO.StatusRes> sendSms(@Body AuthDTO.Req req);

    // 인증번호 확인 요청
    @POST("phone-auth/auth")
    Call<AuthDTO.StatusRes> sendAuthCode(@Body AuthDTO.Req req);

    // 아이디 찾기 요청
    @POST("phone-auth/auth/find/id")
    Call<UserDTO.FindIdRes> sendAuthForFindId(@Body AuthDTO.Req req);

    // 사용자 핸드폰 번호 요청
    @GET("user/information/{userId}/phone")
    Call<UserDTO.GetUserInfoRes> getUserPhoneNumber(@Path("userId") String userId);

    // 아이디 중복 확인 요청
    @GET("duplicate/{id}")
    Call<AuthDTO.StatusRes> checkIdDuplicate(@Path("id") String id);

    // 사업자 등록 번호 확인 요청
    @GET("check-corpor/{number}")
    Call<AuthDTO.StatusRes> checkRegistrationNumber(@Path("number") String number);

    // 손님 추가 요청
    @POST("user/customer")
    Call<UserDTO.StatusRes> insertCustomer(@Body UserDTO.Customer request);

    // 점주 추가 요청
    @POST("user/manager")
    Call<UserDTO.StatusRes> insertManager(@Body UserDTO.Manager request);

    // 비밀번호 수정 요청
    @PUT("user/{userId}/password")
    Call<UserDTO.StatusRes> updatePassword(@Path("userId") String userId, @Body UserDTO.ChangePasswordReq req);

    // 사용자 삭제 요청
    @DELETE("user/{id}")
    Call<UserDTO.StatusRes> deleteUser(@Path("id") String id);
}
