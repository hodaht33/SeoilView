package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.MenuDTO;

public interface Menu {

    @GET("menu/{shopId}")
    Call<MenuDTO.Res> getMenuInfo(@Path("shopId") String shopId);

    @POST("menu/{shopId}")
    Call<MenuDTO.StatusRes> insertMenu(@Path("shopId") String shopId, @Body MenuDTO.Req req);

    @PUT("menu/{shopId}/{menuName}")
    Call<MenuDTO.StatusRes> updateMenu(@Path("shopId") String shopId, @Path("menuName") String menuName,
                                       @Query("menuNewName") String menuNewName, @Query("menuPrice") int menuPrice,
                                       @Query("menuIngredients") String menuIngredients);

    @PUT("menu/{shopId}/{menuName}/name")
    Call<MenuDTO.StatusRes> updateMenuName(@Path("shopId") String shopId, @Path("menuName") String menuName,
                                           @Query("menuNewName") String menuNewName);

    @PUT("menu/{shopId}/{menuName}/price")
    Call<MenuDTO.StatusRes> updateMenuPrice(@Path("shopId") String shopId, @Path("menuName") String menuName,
                                            @Query("menuPrice") int menuPrice);

    @PUT("menu/{shopId}/{menuName}/ingredients")
    Call<MenuDTO.StatusRes> updateMenuIngredients(@Path("shopId") String shopId, @Path("menuName") String menuName,
                                                  @Query("menuIngredients") String menuIngredients);

    @DELETE("menu/{shopId}")
    Call<MenuDTO.StatusRes> deleteAllMenu(@Path("shopId") String shopId);

    @DELETE("menu/{shopId}/{menuName}")
    Call<MenuDTO.StatusRes> deleteMenu(@Path("shopId") String shopId, @Path("menuName") String menuName);
}
