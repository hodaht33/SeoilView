package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.StockData;

public interface Stock {

    // 재고 검색 GET 요청
    @GET("stock/{shopId}")
    Call<StockData.GetRes> getStock(@Path("shopId") String shopId);

    // 재고 추가 POST 요청
    @POST("stock")
    Call<StockData.StatusRes> insertStock(@Body StockData.Req req);

    // 재고 이름, 수량 수정 PUT 요청
    @PUT("stock")
    Call<StockData.StatusRes> updateStock(@Body StockData.UpdateAllReq req);

    // 재고 수량 수정 PUT 요청
    @PUT("stock/amount")
    Call<StockData.StatusRes> updateStockAmount(@Body StockData.Req req);

    // 재고 특정 매장의 특정 재고 삭제 DELETE 요청
    @DELETE("stock/{shopId}/{stockName}")
    Call<StockData.StatusRes> deleteStock(@Path("shopId") String shopId, @Path("stockName") String stockName);
}
