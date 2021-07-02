package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.MenuDTO;
import seoil.capstone.som.data.network.model.retrofit.Menu;

public class MenuApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;

    private Menu mMenuData;

    public MenuApi(Retrofit retrofit) {

        mMenuData = retrofit.create(Menu.class);
    }

    // 재고 정보 요청
    public void getMenu(String shopId , OnFinishApiListener<MenuDTO.Res> onFinishApiListener) {

        Call<MenuDTO.Res> call = mMenuData.getMenuInfo(shopId);
        call.enqueue(new Callback<MenuDTO.Res>() {
            @Override
            public void onResponse(Call<MenuDTO.Res> call, Response<MenuDTO.Res> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.Res> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertMenu(String shopId, MenuDTO.Req req, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.insertMenu(shopId, req);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateMenu(String shopId, String menuName, String menuNewName,
                           int menuPrice, String menuIngredients, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.updateMenu(shopId, menuName, menuNewName, menuPrice, menuIngredients);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateMenuName(String shopId, String menuName, String menuNewName, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.updateMenuName(shopId, menuName, menuNewName);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateMenuPrice(String shopId, String menuName, int menuPrice, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.updateMenuPrice(shopId, menuName, menuPrice);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateMenuIngredients(String shopId, String menuName, String menuIngredients, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.updateMenuIngredients(shopId, menuName, menuIngredients);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteAllMenu(String shopId, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.deleteAllMenu(shopId);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteMenu(String shopId, String menuName, OnFinishApiListener onFinishApiListener) {

        Call<MenuDTO.StatusRes> call = mMenuData.deleteMenu(shopId, menuName);
        call.enqueue(new Callback<MenuDTO.StatusRes>() {
            @Override
            public void onResponse(Call<MenuDTO.StatusRes> call, Response<MenuDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MenuDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
