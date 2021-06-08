package seoil.capstone.som.ui.main.customer.point;

import java.util.List;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointDTO;

public interface CustomerPointContract {

    interface View extends BaseContract.View {

        void setCurrentPoint(List<CustomerPointAdapter.Item> currentPoint);

        void setUsePoint(CustomerPointAdapter.Item usePoint, List<String> useDate);

        void setSavePoint(CustomerPointAdapter.Item savePoint, List<String> saveDate);

    }

    interface Presenter extends BaseContract.Presenter<CustomerPointContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getCurrentPoint(String id, OnFinishApiListener<PointDTO.GetCurrentRes> onFinishApiListener);

        void getSavePoint(String id, OnFinishApiListener<PointDTO.GetSaveRes> onFinishApiListener);

        void getUsingPoint(String id, OnFinishApiListener<PointDTO.GetUsingRes> onFinishApiListener);
    }
}
