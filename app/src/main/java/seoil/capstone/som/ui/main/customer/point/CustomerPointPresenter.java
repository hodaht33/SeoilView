package seoil.capstone.som.ui.main.customer.point;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.model.PointDTO;

// 포인트 프레젠터
public class CustomerPointPresenter implements CustomerPointContract.Presenter {

    private CustomerPointContract.View view;
    private CustomerPointInteractor mInteractor;

    //뷰 설정
    @Override
    public void setView(CustomerPointContract.View view) {
        this.view = view;
    }

    //뷰 해제
    @Override
    public void releaseView() {
        this.view = null;
    }

    //DB 접근 Model 생성
    @Override
    public void createInteractor() {
        mInteractor = new CustomerPointInteractor();
    }

    //Model 해제
    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    
    //Fragment에서 받아온 사용자 아이디와 조회된 잔여 포인트를 처리할 방식을 Model에 전달
    public synchronized void getCurrentPoint(String id) {

        //DB에 조회 성공 했을 때 잔여 사용 포인트 정보, 사용 날짜를 view에 전달
        OnFinishApiListener<PointDTO.GetCurrentRes> onFinishApiListener = new OnFinishApiListener<PointDTO.GetCurrentRes>() {
            @Override
            public void onSuccess(PointDTO.GetCurrentRes getCurrentRes) {

                if (getCurrentRes.getStatus() == PointApi.SUCCESS) {

                    List<CustomerPointAdapter.Item> list = new ArrayList<>();
                    list.add(new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER,"잔여 포인트"));
                    Integer amount = getCurrentRes.getPoint();

                    if (amount == null) {

                        list.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(0)));
                    } else {

                        list.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(getCurrentRes.getPoint())));
                    }

                    view.setCurrentPoint(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getCurrentPoint(id, onFinishApiListener);
    }

    //Fragment에서 받아온 사용자 아이디와 조회된 적립 포인트를 처리할 방식을 Model에 전달
    public synchronized void getSavePoint(String id) {

        //DB에 조회 성공 했을 때 적립 포인트 정보, 적립 날짜를 view에 전달
        OnFinishApiListener<PointDTO.GetSaveRes> onFinishApiListener = new OnFinishApiListener<PointDTO.GetSaveRes>() {
            @Override
            public void onSuccess(PointDTO.GetSaveRes getSaveRes) {

                if (getSaveRes.getStatus() == PointApi.SUCCESS) {

                    CustomerPointAdapter.Item savePoint = new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER, "적립 포인트");

                    List<PointDTO.GetSaveRes.Result> list = getSaveRes.getResults();

                    savePoint.invisibleChildren = new ArrayList<>();

                    List<String> saveDate = new ArrayList<>();
                    saveDate.add("");

                    if (list == null) {

                        savePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(0)));
                        saveDate.add("");
                    } else {


                        for (PointDTO.GetSaveRes.Result result : list) {

                            saveDate.add(result.getSavePointDate().substring(0, 10));
                            savePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(result.getSavePointAmount())));
                        }
                    }
                    
                    view.setSavePoint(savePoint, saveDate);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getSavePoint(id, onFinishApiListener);
    }

    //Fragment에서 받아온 사용자 아이디와 조회된 사용 포인트를 처리할 방식을 Model에 전달
    public synchronized void getUsePoint(String id) {

        OnFinishApiListener<PointDTO.GetUsingRes> onFinishApiListener = new OnFinishApiListener<PointDTO.GetUsingRes>() {
            @Override
            public void onSuccess(PointDTO.GetUsingRes getUsingRes) {

                if (getUsingRes.getStatus() == PointApi.SUCCESS) {

                    CustomerPointAdapter.Item usePoint = new CustomerPointAdapter.Item(CustomerPointAdapter.HEADER, "사용 포인트");

                    List<PointDTO.GetUsingRes.Result> list = getUsingRes.getResults();

                    usePoint.invisibleChildren = new ArrayList<>();

                    List<String> useDate = new ArrayList<>();
                    useDate.add("");

                    if (list == null) {

                        usePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(0)));
                        useDate.add("");
                    } else {

                        for (PointDTO.GetUsingRes.Result result : list) {

                            useDate.add(result.getUsingPointDate().substring(0, 10));
                            usePoint.invisibleChildren.add(new CustomerPointAdapter.Item(CustomerPointAdapter.CHILD, String.valueOf(result.getUsingPointAmount())));
                        }
                    }



                    view.setUsePoint(usePoint, useDate);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getUsingPoint(id, onFinishApiListener);
    }
}
