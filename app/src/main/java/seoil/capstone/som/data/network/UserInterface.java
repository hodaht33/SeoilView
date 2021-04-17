package seoil.capstone.som.data.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterResponse;

public interface UserInterface {

    // GET으로 추가로 데이터(URL에 ?키=값 형태로 데이터를 보내는 것을 말함)를 넣으려면 매개변수에 @Query(키 이름) 타입 변수명) 을 넣음
    // 특정 유저의 정보 가져오기
    @GET("user/login/{id}&{pwd}")
    Call<LoginResponse> getUser(@Path("id") String id, @Path("pwd") String pwd);

    // 유저 추가
    @POST("user/info")
    Call<RegisterResponse> insertUser(@Body String id, @Body String birthdate, @Body int gender, @Body String email, @Body String phoneNumber);

    //
    @PUT("user/info/{id}")
    void updateUser(@Path("id") String id, @Body String prevPwd, @Body  String pwd);

    @DELETE("user/info/{id}")
    void deleteUser(@Path("id") String id);
}
