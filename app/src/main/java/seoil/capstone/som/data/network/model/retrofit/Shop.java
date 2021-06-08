package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.ShopDTO;

// 매장 api 라우팅 인터페이스
public interface Shop {

    // 매장 정보 요청
    @GET("shop/{shopId}/information")
    Call<ShopDTO.GetRes> getShopInformation(@Path("shopId") String shopId);

    // 키워드로 검색되는 매장 정보 요청
    @GET("shop/name")
    Call<ShopDTO.GetRes> getShopInfoWithKeyword(@Query("keyword") String keyword, @Query("page") int page);

    // 카테고리로 검색되는 매장 정보 요청
    @GET("shop/category")
    Call<ShopDTO.GetRes> getShopInfoWithCategory(@Query("category") String category, @Query("page") int page);

    // 매장 정보 추가 요청
    @POST("shop")
    Call<ShopDTO.StatusRes> insertShopInfo(@Body ShopDTO.InsertReq req);

    // 매장 정보 수정 요청
    @PUT("shop")
    Call<ShopDTO.StatusRes> updateShopInfo(@Body ShopDTO.UpdateReq req);

    // 매장 정보 삭제 요청
    @DELETE("shop/{shopId}")
    Call<ShopDTO.StatusRes> deleteShopInfo(@Path("shopId") String shopId, @Query("shopCode") String shopCode);
}
