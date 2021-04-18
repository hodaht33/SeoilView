package seoil.capstone.som.data.network.uri;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import seoil.capstone.som.data.network.model.LoginResponse;
import seoil.capstone.som.data.network.model.RegisterResponse;

public interface UserUri {

    // GET으로 추가로 데이터(URL에 ?키=값 형태로 데이터를 보내는 것을 말함)를 넣으려면 매개변수에 @Query(키 이름) 타입 변수명) 을 넣음
    // GET은 body가 없어 @Body사용 불가
    // 특정 유저의 정보 가져오기
    @GET("user/login/{id}&{pwd}")
    Call<LoginResponse.SomRestLoginApi> getLoginData(@Path("id") String id, @Path("pwd") String pwd);

    // 유저 추가
    @POST("user/info/customer")
    Call<RegisterResponse> insertCustomer(@Body String id, @Body String pwd, @Body String birthdate,
                                          @Body int gender, @Body String email, @Body String phoneNumber);

    @POST("user/info/manager")
    Call<RegisterResponse> insertManager(@Body String id, @Body String pwd, @Body String birthdate,
                                         @Body int gender, @Body String email, @Body String phoneNumber,
                                         @Body String corporateNumber, @Body String storeName, @Body String storeAddr,
                                         @Body String storeCategory);

    // 비밀번호 변경
    @PUT("user/info/{id}")
    void updateUser(@Path("id") String id, @Body String prevPwd, @Body  String pwd);

    // 회원 탈퇴
    @DELETE("user/info/{id}")
    void deleteUser(@Path("id") String id);
}
