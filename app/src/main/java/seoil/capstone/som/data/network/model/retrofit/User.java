package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.Register;

public interface User {

    // 특정 유저의 정보 가져오기
    @POST("login")
    Call<Login.LoginRes> getLoginData(@Body Login.LoginReq req);

    @POST("phone-auth/send")
    Call<Auth.StatusRes> sendSms(@Body Auth.Req req);

    @POST("phone-auth/auth")
    Call<Auth.StatusRes> sendAuthCode(@Body Auth.Req req);

    // 아이디 중복 확인
    @GET("duplicate/{id}")
    Call<Auth.StatusRes> checkIdDuplicate(@Path("id") String id);

    @GET("check-corpor/{number}")
    Call<Auth.StatusRes> checkRegistrationNumber(@Path("number") String number);

    // 유저 추가
    @POST("user/customer")
    Call<Register.RegisterRes> insertCustomer(@Body Register.Customer request);

    @POST("user/manager")
    Call<Register.RegisterRes> insertManager(@Body Register.Manager request);

    // 비밀번호 변경
    @PUT("user/{id}/password")
    void updateUser(@Path("id") String id);
    // Body로 변경할 비밀번호 받음(이전 비밀번호는 애초에 비밀번호 변경 들어가기 전에 검사를 하게하여 여기서 받지 않음)

    // 회원 탈퇴
    @DELETE("user/{id}")
    void deleteUser(@Path("id") String id);
}
