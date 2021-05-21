package seoil.capstone.som.ui.main.customer.point;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointData;

public interface CustomerPointContract {

    interface View extends BaseContract.View {

        void setCurrentPoint(List<CustomerPointAdapter.Item> currentPoint);

        void setUsePoint(CustomerPointAdapter.Item usePoint);

        void setSavePoint(CustomerPointAdapter.Item savePoint);

    }

    interface Presenter extends BaseContract.Presenter<CustomerPointContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getCurrentPoint(String id, OnFinishApiListener<PointData.GetCurrentRes> onFinishApiListener);

        void getSavePoint(String id, OnFinishApiListener<PointData.GetSaveRes> onFinishApiListener);

        void getUsingPoint(String id, OnFinishApiListener<PointData.GetUsingRes> onFinishApiListener);
    }
}
