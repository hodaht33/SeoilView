package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.ShopData;

// 매장 api 라우팅 인터페이스
public interface Shop {

    // 매장 정보 요청
    @GET("shop/{shopId}/information")
    Call<ShopData.GetRes> getShopInformation(@Path("shopId") String shopId);

    // 키워드로 검색되는 매장 정보 요청
    @GET("shop/name")
    Call<ShopData.GetRes> getShopInfoWithKeyword(@Query("keyword") String keyword, @Query("page") int page);

    // 카테고리로 검색되는 매장 정보 요청
    @GET("shop/category")
    Call<ShopData.GetRes> getShopInfoWithCategory(@Query("category") String category, @Query("page") int page);

    // 매장 정보 추가 요청
    @POST("shop")
    Call<ShopData.StatusRes> insertShopInfo(@Body ShopData.InsertReq req);

    // 매장 정보 수정 요청
    @PUT("shop")
    Call<ShopData.StatusRes> updateShopInfo(@Body ShopData.UpdateReq req);

    // 매장 정보 삭제 요청
    @DELETE("shop/{shopId}")
    Call<ShopData.StatusRes> deleteShopInfo(@Path("shopId") String shopId, @Query("shopCode") String shopCode);
}
