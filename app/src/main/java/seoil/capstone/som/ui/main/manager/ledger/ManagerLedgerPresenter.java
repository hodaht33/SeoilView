package seoil.capstone.som.ui.main.manager.ledger;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.StockData;

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

    public int isTextSet(String str) {
        if (str == null) {

            return TEXT_LENGTH_NONE;
        } else if (str.length() > 11) {

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
                    view.setLayoutAdapter(dataName, dataAmount);
                } else {
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        mInteractor.getStock(shopId, onFinishApiListener);
    }

    public void getSales(String shopId, String dateQuery) {

        OnFinishApiListener<SalesData.GetRes> onFinishApiListener = new OnFinishApiListener<SalesData.GetRes>() {

            @Override
            public void onSuccess(SalesData.GetRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesData.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> dataDate = new ArrayList<>();
                    ArrayList<String> dataAmount = new ArrayList<>();

                    for (SalesData.GetRes.Result result : list) {

                        dataDate.add(result.getSalesDate());
                        dataAmount.add(getDetailedSale(result.getSalesAmount()));
                    }
                    view.setLayoutAdapter(dataDate, dataAmount);
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

                    ArrayList<String> dataDate = new ArrayList<>();
                    ArrayList<String> dataAmount = new ArrayList<>();

                    for (SalesData.GetRes.Result result : list) {

                        dataDate.add(result.getSalesDate());
                        dataAmount.add(getDetailedSale(Math.abs(result.getSalesAmount())));
                    }
                    view.setLayoutAdapter(dataDate, dataAmount);
                } else {
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.getCost(shopId, dateQuery, onFinishApiListener);
    }

    public boolean isNumeric(String str) {

        if (str == null) {

            return false;
        }
        try {

            double d= Double.parseDouble(str);
        } catch (NumberFormatException nfe) {

            return false;
        }

        return true;
    }

    public void insertSalesWithDate(String shopId, String name, int amount, String dateQuery) {

        OnFinishApiListener<SalesData.StatusRes> onFinishApiListener =  new OnFinishApiListener<SalesData.StatusRes>() {

            @Override
            public void onSuccess(SalesData.StatusRes statusRes) {

                view.initSales();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("setSales", t.getMessage());
            }
        };

        mInteractor.insertSalesWithDate(shopId, name, amount, dateQuery, onFinishApiListener);
    }

    public void setStock(String shopId, String name, int amount) {

        OnFinishApiListener<StockData.StatusRes> onFinishApiListener = new OnFinishApiListener<StockData.StatusRes>() {

            @Override
            public void onSuccess(StockData.StatusRes statusRes) {


                view.initStock();
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("setStock", t.getMessage());
            }
        };
        mInteractor.setStock(shopId, name, amount, onFinishApiListener);
    }
}