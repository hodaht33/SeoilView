package seoil.capstone.som.ui.main.customer.search;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.model.ShopDTO;
import seoil.capstone.som.data.network.model.retrofit.Shop;

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

    }

    @Override
    public void releaseInteractor() {

    }


    public void getShop(String shopId){

        OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener = new OnFinishApiListener<ShopDTO.GetRes>() {
            @Override
            public void onSuccess(ShopDTO.GetRes getRes) {
                if(getRes.getStatus() == ShopApi.SUCCESS){

                    List<ShopDTO.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> ShopName = new ArrayList<>();
                    ArrayList<String> ShopAddress = new ArrayList<>();
                    for (ShopDTO.GetRes.Result result : list){

                        ShopName.add(result.getShopName());
                        ShopName.add(result.getShopAddress());

                    }
                    view.setLayoutAdapterShop(ShopName, ShopAddress);
                } else{

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.getShop(shopId, onFinishApiListener);
    }

    public void insertShop(String ShopId, String ShopName, String ShopAddrees){

        OnFinishApiListener<ShopDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<ShopDTO.StatusRes>() {
            @Override
            public void onSuccess(ShopDTO.StatusRes statusRes) {

                view.initShop();
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("setShop",t.getMessage());

            }
        };
        mInteractor.insertShop(ShopId, ShopName, ShopAddrees, onFinishApiListener);

    }
}
