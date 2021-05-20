package seoil.capstone.som.ui.main.manager.ledger.Sales;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerContract;
import seoil.capstone.som.ui.main.manager.ledger.ManagerLedgerInteractor;

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

    public void getSales(String shopId, String dateQuery) {

        OnFinishApiListener<SalesData.GetRes> onFinishApiListener = new OnFinishApiListener<SalesData.GetRes>() {

            @Override
            public void onSuccess(SalesData.GetRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesData.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> dataDate = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    ArrayList<Integer> autoInc = new ArrayList<>();

                    for (SalesData.GetRes.Result result : list) {

                        dataDate.add(result.getSalesDate());
                        dataAmount.add(result.getSalesAmount());
                        autoInc.add(result.getSalesCode());
                    }
                    view.setLayoutAdapterSales(dataDate, dataAmount, autoInc);
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

    public void getCost(String shopId, String dateQuery) {

        OnFinishApiListener<SalesData.GetRes> onFinishApiListener = new OnFinishApiListener<SalesData.GetRes>() {

            @Override
            public void onSuccess(SalesData.GetRes getRes) {
                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesData.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> dataName = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    ArrayList<Integer> autoInc = new ArrayList<>();

                    for (SalesData.GetRes.Result result : list) {

                        dataName.add(result.getSalesName());
                        dataAmount.add(Math.abs(result.getSalesAmount()));
                        Log.d("salescode",String.valueOf(result.getSalesCode()));
                        autoInc.add(result.getSalesCode());
                    }
                    view.setLayoutAdapterSales(dataName, dataAmount, autoInc);
                } else {
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.getCost(shopId, dateQuery, onFinishApiListener);
    }

    public void insertSalesWithDate(String shopId, String name, int amount, String dateQuery) {

        OnFinishApiListener<SalesData.StatusRes> onFinishApiListener =  new OnFinishApiListener<SalesData.StatusRes>() {

            @Override
            public void onSuccess(SalesData.StatusRes statusRes) {


                view.initCost();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("setSales", t.getMessage());
            }
        };

        mInteractor.insertSalesWithDate(shopId, name, amount, dateQuery, onFinishApiListener);
    }

    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, Boolean isCost) {

        OnFinishApiListener<SalesData.StatusRes> onFinishApiListener = new OnFinishApiListener<SalesData.StatusRes>() {
            @Override
            public void onSuccess(SalesData.StatusRes statusRes) {

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

    public void updateSpendingSales(int salesCode, String salesDate, String shopId, String salesName, int salesAmount, Boolean isCost) {

        OnFinishApiListener<SalesData.StatusRes> onFinishApiListener = new OnFinishApiListener<SalesData.StatusRes>() {
            @Override
            public void onSuccess(SalesData.StatusRes statusRes) {

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

    public int isTextSet(String str) {
        if (str == null || str.equals("")) {

            return TEXT_LENGTH_NONE;
        } else if (str.length() > 11 || str.length() <= 0) {

            return TEXT_LENGTH_OVER;
        }
        return TEXT_LENGTH_INVALID;
    }

    public boolean isNumeric(String str) {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

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
