package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.StockDTO;

// 재고 api 라우팅 인터페이스
public interface Stock {

    // 재고 정보 요청
    @GET("stock/{shopId}")
    Call<StockDTO.GetRes> getStock(@Path("shopId") String shopId);

    // 재고 추가 요청
    @POST("stock")
    Call<StockDTO.StatusRes> insertStock(@Body StockDTO.Req req);

    // 재고 이름, 수량, 가격 수정 요청
    @PUT("stock")
    Call<StockDTO.StatusRes> updateStock(@Body StockDTO.UpdateAllReq req);

    // 재고 수량 수정 요청
    @PUT("stock/amount")
    Call<StockDTO.StatusRes> updateStockAmount(@Body StockDTO.Req req);

    // 재고 가격 수정 요청
    @PUT("stock/price")
    Call<StockDTO.StatusRes> updateStockPrice(@Body StockDTO.Req req);

    // 재고 특정 매장의 특정 재고 삭제 요청
    @DELETE("stock/{shopId}")
    Call<StockDTO.StatusRes> deleteStock(@Path("shopId") String shopId, @Query("stockCode") int stockCode, @Query("stockName") String stockName);
}
