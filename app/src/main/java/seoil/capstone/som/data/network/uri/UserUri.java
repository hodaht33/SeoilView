package seoil.capstone.som.data.network.uri;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.IdDuplicateResponse;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterRequest;
import seoil.capstone.som.data.network.model.RegisterResponse;

public interface UserUri {

    // GET으로 추가로 데이터(URL에 ?키=값 형태로 데이터를 보내는 것을 말함)를 넣으려면 매개변수에 @Query(키 이름) 타입 변수명) 을 넣음
    // GET은 body가 없어 @Body사용 불가
    // 특정 유저의 정보 가져오기
    @GET("user/info")
    Call<LoginResponse.SomRestLoginApi> getLoginData(@Query("id") String id, @Query("pwd") String pwd);

    // 아이디 중복 확인
    @GET("user/info/id")
    Call<IdDuplicateResponse> checkIdDuplicate(@Query("id") String id);

    // 유저 추가
    @POST("user/info/customer")
    Call<RegisterResponse> insertCustomer(@Body RegisterRequest.Customer request);

    @POST("user/info/manager")
    Call<RegisterResponse> insertManager(@Body RegisterRequest.Manager request);

    // 비밀번호 변경
    @PUT("user/info/{id}")
    void updateUser(@Path("id") String id, @Body String prevPwd, @Body  String pwd);

    // 회원 탈퇴
    @DELETE("user/info/{id}")
    void deleteUser(@Path("id") String id);
}
