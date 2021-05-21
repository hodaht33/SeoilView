package seoil.capstone.som.ui.main.customer.point;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.model.PointData;
import seoil.capstone.som.data.network.model.Register;
import seoil.capstone.som.data.network.model.SalesData;

public class CustomerPointPresenter implements CustomerPointContract.Presenter {

    private CustomerPointContract.View view;
    private CustomerPointInteractor mInteractor;

    @Override
    public void setView(CustomerPointContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerPointInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    public synchronized void getCurrentPoint(String id) {

        OnFinishApiListener<PointData.GetCurrentRes> onFinishApiListener = new OnFinishApiListener<PointData.GetCurrentRes>() {
            @Override
            public void onSuccess(PointData.GetCurrentRes getCurrentRes) {

                if (getCurrentRes.getStatus() == PointApi.SUCCESS) {

                    List<CustomerPointAdapter.Item> list = new ArrayList<>();
                    list.add(new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER,"잔여 포인트"));
                    list.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(getCurrentRes.getPoint())));

                    view.setCurrentPoint(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getCurrentPoint(id, onFinishApiListener);
    }

    public synchronized void getSavePoint(String id) {

        OnFinishApiListener<PointData.GetSaveRes> onFinishApiListener = new OnFinishApiListener<PointData.GetSaveRes>() {
            @Override
            public void onSuccess(PointData.GetSaveRes getSaveRes) {

                if (getSaveRes.getStatus() == PointApi.SUCCESS) {

                    CustomerPointAdapter.Item savePoint = new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER, "적립 포인트");

                    List<PointData.GetSaveRes.Result> list = getSaveRes.getResults();

                    savePoint.invisibleChildren = new ArrayList<>();
                    for (PointData.GetSaveRes.Result result : list) {

                        savePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(result.getSavePointAmount())));
                    }

                    view.setSavePoint(savePoint);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getSavePoint(id, onFinishApiListener);
    }

    public synchronized void getUsePoint(String id) {

        OnFinishApiListener<PointData.GetUsingRes> onFinishApiListener = new OnFinishApiListener<PointData.GetUsingRes>() {
            @Override
            public void onSuccess(PointData.GetUsingRes getUsingRes) {

                if (getUsingRes.getStatus() == PointApi.SUCCESS) {

                    CustomerPointAdapter.Item usePoint = new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER, "사용 포인트");

                    List<PointData.GetUsingRes.Result> list = getUsingRes.getResults();

                    usePoint.invisibleChildren = new ArrayList<>();
                    for (PointData.GetUsingRes.Result result : list) {

                        usePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(result.getUsingPointAmount())));
                    }
                    view.setUsePoint(usePoint);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getUsingPoint(id, onFinishApiListener);
    }
}
