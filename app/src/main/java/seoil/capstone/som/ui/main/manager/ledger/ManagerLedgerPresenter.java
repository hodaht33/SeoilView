package seoil.capstone.som.ui.main.manager.ledger;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.api.StockApi;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;
import seoil.capstone.som.util.Utility;

public class ManagerLedgerPresenter implements  ManagerLedgerContract.Presenter{

    private ManagerLedgerContract.View view;
    private ManagerLedgerContract.Interactor mInteractor;
    public final int TEXT_LENGTH_NONE = 0;
    public final int TEXT_LENGTH_OVER = 1;
    public final int TEXT_LENGTH_INVALID = 2;

    @Override
    public void setView(ManagerLedgerContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new ManagerLedgerInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
    }

    public String getDate(String date) {

        String result;

        if (date.equals("Sun")) {

            result = "일";
        } else if (date.equals("Mon")) {

            result = "월";
        } else if (date.equals("Tue")) {

            result = "화";
        } else if (date.equals("Wed")) {

            result = "수";
        } else if (date.equals("Thu")) {

            result = "목";
        } else if (date.equals("Fri")) {

            result = "금";
        } else if (date.equals("Sat")) {

            result = "토";
        } else {

            result = "Error";
        }

        return result;
    }


    public String getDateQuery(int year, int month, int day) {

        String dateQuery;
        if (day < 10) {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-0" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-0" + day;
            }

        } else {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-" + day;
            }
        }

        return dateQuery;
    }


    public int isTextSet(String str) {
        if (str == null || str.equals("")) {

            return TEXT_LENGTH_NONE;
        } else if (str.length() > 11 || str.length() <= 0) {

            return TEXT_LENGTH_OVER;
        }
        return TEXT_LENGTH_INVALID;
    }

    public void getStock(String shopId) {

        OnFinishApiListener<StockData.GetRes> onFinishApiListener = new OnFinishApiListener<StockData.GetRes>() {
            @Override
            public void onSuccess(StockData.GetRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<StockData.GetRes.Result> list = getRes.getResults();
                    
                    ArrayList<String> dataName = new ArrayList<>();
                    ArrayList<String> dataAmount = new ArrayList<>();


                    for (StockData.GetRes.Result result : list) {

                        dataName.add(result.getStockName());
                        dataAmount.add(result.getStockAmount() + "개");
                    }

                    view.setLayoutAdapterStock(dataName, dataAmount);
                } else if (getRes.getStatus() == SalesApi.ERROR_NONE_DATA){

                    view.setLayoutAdapterStock(null, null);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        mInteractor.getStock(shopId, onFinishApiListener);
    }


    public boolean isNumeric(String str) {

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) < '0' || str.charAt(i) > '9') {

                return false;
            }
        }
        return true;
    }



    public void insertStock(String shopId, String name, int amount) {

        OnFinishApiListener<StockData.StatusRes> onFinishApiListener = new OnFinishApiListener<StockData.StatusRes>() {

            @Override
            public void onSuccess(StockData.StatusRes statusRes) {

                if (statusRes.getStatus() == StockApi.SUCCESS) {

                    view.initStock();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.insertStock(shopId, name, amount, onFinishApiListener);
    }

    public void updateStock (String shopId, String name, int amount) {

        OnFinishApiListener<StockData.StatusRes> onFinishApiListener = new OnFinishApiListener<StockData.StatusRes>() {
            @Override
            public void onSuccess(StockData.StatusRes statusRes) {

                view.initStock();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.updateStock(shopId, name, amount, onFinishApiListener);
    }

    public void deleteStock(String shopId, String name) {

        OnFinishApiListener<StockData.StatusRes> onFinishApiListener = new OnFinishApiListener<StockData.StatusRes>() {
            @Override
            public void onSuccess(StockData.StatusRes statusRes) {

                view.initStock();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.deleteStock(shopId, name, onFinishApiListener);
    }


}