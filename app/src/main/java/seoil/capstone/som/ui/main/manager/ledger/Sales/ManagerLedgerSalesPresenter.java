package seoil.capstone.som.ui.main.manager.ledger.Sales;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesDTO;

public class ManagerLedgerSalesPresenter implements ManagerLedgerSalesContract.Presenter{

    private ManagerLedgerSalesInteractor mInteractor;
    ManagerLedgerSalesContract.View view;

    public final int TEXT_LENGTH_NONE = 0;
    public final int TEXT_LENGTH_OVER = 1;
    public final int TEXT_LENGTH_INVALID = 2;

    @Override
    public void setView(ManagerLedgerSalesContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new ManagerLedgerSalesInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    //view에서 shopId, dateQuery를 받아 데이터 처리 방식을 Model에 전달
    public void getSales(String shopId, String dateQuery) {

        //DB 조회된 매출 정보를 view에 전달
        OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener = new OnFinishApiListener<SalesDTO.GetRes>() {

            @Override
            public void onSuccess(SalesDTO.GetRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    ArrayList<String> dataDate = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    ArrayList<Integer> autoInc = new ArrayList<>();

                    List<SalesDTO.GetRes.Result> list = getRes.getResults();

                    for (SalesDTO.GetRes.Result result : list) {

                        dataDate.add(result.getSalesDate());
                        dataAmount.add(result.getSalesAmount());
                        autoInc.add(result.getSalesCode());
                    }
                    view.setLayoutAdapterSales(dataDate, dataAmount, autoInc);
                } else if (getRes.getStatus() == SalesApi.ERROR_NONE_DATA){

                    view.setLayoutAdapterSales(null, null, null);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("managerLedger", t.toString());
            }
        };

        mInteractor.getSales(shopId, dateQuery, onFinishApiListener);
    }

    //view에서 shopId, dateQuery를 받아 데이터 처리방식을 Model에 전달
    public void getCost(String shopId, String dateQuery) {

        OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener = new OnFinishApiListener<SalesDTO.GetRes>() {

            //DB 조회된 지출 정보를 view에 전달
            @Override
            public void onSuccess(SalesDTO.GetRes getRes) {
                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesDTO.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> dataName = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    ArrayList<Integer> autoInc = new ArrayList<>();

                    for (SalesDTO.GetRes.Result result : list) {

                        dataName.add(result.getSalesName());
                        dataAmount.add(Math.abs(result.getSalesAmount()));
                        autoInc.add(result.getSalesCode());
                    }
                    view.setLayoutAdapterSales(dataName, dataAmount, autoInc);
                } else if (getRes.getStatus() == SalesApi.ERROR_NONE_DATA){

                    view.setLayoutAdapterSales(null, null, null);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.getCost(shopId, dateQuery, onFinishApiListener);
    }

    //지출 정보를 추가 후 지출 조회
    public void insertSalesWithDate(String shopId, String name, int amount, String dateQuery) {

        OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener =  new OnFinishApiListener<SalesDTO.StatusRes>() {

            @Override
            public void onSuccess(SalesDTO.StatusRes statusRes) {

                view.initCost();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("setSales", t.getMessage());
            }
        };

        mInteractor.insertSalesWithDate(shopId, name, amount, dateQuery, onFinishApiListener);
    }

    //지출 정보 삭제후 지출 조회
    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, Boolean isCost) {

        OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<SalesDTO.StatusRes>() {
            @Override
            public void onSuccess(SalesDTO.StatusRes statusRes) {

                if (isCost) {

                    view.initCost();
                } else {

                    view.initSales();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.deleteSpendingSales(shopId, salesCode, salesDate, onFinishApiListener);
    }

    //지출 정보 갱신
    public void updateSpendingSales(int salesCode, String salesDate, String shopId, String salesName, int salesAmount, Boolean isCost) {

        OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<SalesDTO.StatusRes>() {
            @Override
            public void onSuccess(SalesDTO.StatusRes statusRes) {

                if (isCost) {

                    view.initCost();
                } else {

                    view.initSales();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.updateSpendingSales(salesCode, salesDate, shopId, salesName, salesAmount, onFinishApiListener);
    }

    //추가 수정 시 EditText의 데이터 확인
    public int isTextSet(String str) {
        if (str == null || str.equals("")) {

            return TEXT_LENGTH_NONE;
        } else if (str.length() > 11 || str.length() <= 0) {

            return TEXT_LENGTH_OVER;
        }
        return TEXT_LENGTH_INVALID;
    }

    //숫자로만 이루어졌는지 확인
    public boolean isNumeric(String str) {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) < '0' || str.charAt(i) > '9') {

                return false;
            }
        }
        return true;
    }

    //1,000,000 형식으로 값 변경
    public String getDetailedSale (int value){

        if (value == 0) {

            return "0원";
        }

        StringBuffer temp = new StringBuffer(String.valueOf(value));

        for(int i = temp.length() - 3; i > 0; i -= 3) {

            temp.insert(i,",");
        }
        temp.append("원");

        return temp.toString();
    }
}
