package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.BookmarkDTO;

// 즐겨찾기 api 라우팅 인터페이스
public interface Bookmark {

    // 가게 정보 요청
    @GET("bookmark/{id}/shop")
    Call<BookmarkDTO.ShopInfoRes> getShopInfo(@Path("id") String id);

    // 사용자 정보 요청
    @GET("bookmark/{shopId}/user")
    Call<BookmarkDTO.UserInfoRes> getUserInfo(@Path("shopId") String shopId);

    // 즐겨찾기 추가 요청
    @POST("bookmark")
    Call<BookmarkDTO.StatusRes> addBookmark(@Body BookmarkDTO.InsertReq createReq);

    // 즐겨찾기 삭제 요청
    @DELETE("bookmark/{userId}")
    Call<BookmarkDTO.StatusRes> deleteBookmark(@Path("userId") String userId, @Query("shopId") String shopId);
}
