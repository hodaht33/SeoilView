package seoil.capstone.som.ui.main.customer.search;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.model.ShopDTO;
import seoil.capstone.som.data.network.model.retrofit.Shop;

import static android.widget.Toast.LENGTH_SHORT;

public class CustomerSearchPresenter implements CustomerSearchContract.Presenter {

    private CustomerSearchContract.View view;
    private CustomerSearchContract.Interactor mInteractor;


    @Override
    public void setView(CustomerSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerSearchInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    //ShopID검색
    public void getShop(String shopName){

        OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener = new OnFinishApiListener<ShopDTO.GetRes>() {

            ArrayList<String> ShopName = new ArrayList<>();
            ArrayList<String> ShopCategory = new ArrayList<>();

            @Override
            public void onSuccess(ShopDTO.GetRes getRes) {

                if(getRes.getStatus() == ShopApi.SUCCESS){ //검색 한 값이 shopID가 맞으면

                    Log.d("tag", "SHOPID Acsses");
                    List<ShopDTO.GetRes.Result> list = getRes.getResults();

                    for (ShopDTO.GetRes.Result result : list){

                        ShopName.add(result.getShopName());
                        ShopCategory.add(result.getShopCategory());

                    }
                    view.setLayoutAdapterShop(ShopName, ShopCategory);
                } else{

                    Log.d("tag", "ShopId NoneData");

                }
            }
            @Override
            public void onFailure(Throwable t) {

                Log.d("tag", "Fail");
            }
        };
        mInteractor.getShop(shopName, onFinishApiListener);
    }

    //가게이름 검색
    public void getShopKeyword(String keyword, int page){

        ArrayList<String> ShopName = new ArrayList<>();
        ArrayList<String> ShopCategory = new ArrayList<>();

        OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener = new OnFinishApiListener<ShopDTO.GetRes>() {
            @Override
            public void onSuccess(ShopDTO.GetRes getRes) {

                if(getRes.getStatus() == ShopApi.SUCCESS){ //검색 한 값이 shopID가 맞으면

                    Log.d("tag", "가게 접속성공");
                    List<ShopDTO.GetRes.Result> list = getRes.getResults();

                    for (ShopDTO.GetRes.Result result : list){

                        ShopName.add(result.getShopName());
                        ShopCategory.add(result.getShopCategory());

                    }
                    view.setLayoutAdapterShop(ShopName, ShopCategory);
                } else{

                    Log.d("tag", "nonedate");

                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("tag", "Fail");
            }
        };
        mInteractor.getShopKeyword(keyword, page, onFinishApiListener);
    }

    //카테고리 검색
    public void getShopKeywordCategory(String category, int page){

        ArrayList<String> ShopName = new ArrayList<>();
        ArrayList<String> ShopCategory = new ArrayList<>();

        OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener = new OnFinishApiListener<ShopDTO.GetRes>() {
            @Override
            public void onSuccess(ShopDTO.GetRes getRes) {

                if(getRes.getStatus() == ShopApi.SUCCESS){

                    Log.d("tag", "카테고리 접속성공");
                    List<ShopDTO.GetRes.Result> list = getRes.getResults();

                    for (ShopDTO.GetRes.Result result : list){

                        ShopName.add(result.getShopName());
                        ShopCategory.add(result.getShopCategory());

                    }
                    view.setLayoutAdapterShop(ShopName, ShopCategory);
                } else{

                    view.showDialog("검색 된 가계가 없습니다.");
                    Log.d("tag", "nonedate");

                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.d("tag", "Fail");
            }
        };

        mInteractor.getShopKeywordCategory(category, page, onFinishApiListener);

    }
}
