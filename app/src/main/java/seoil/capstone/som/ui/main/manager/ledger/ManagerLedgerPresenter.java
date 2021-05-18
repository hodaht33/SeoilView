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

    public void getStock(String shopId) {

        OnFinishApiListener<StockData.GetRes> onFinishApiListener = new OnFinishApiListener<StockData.GetRes>() {
            @Override
            public void onSuccess(StockData.GetRes getRes) {
                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<StockData.GetRes.Result> list = getRes.getResults();

                    ArrayList<String> dataName = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    for (StockData.GetRes.Result result : list) {

                        dataName.add(result.getStockName());
                        dataAmount.add(result.getStockAmount());
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
                    ArrayList<Integer> dataAmount = new ArrayList<>();

                    for (SalesData.GetRes.Result result : list) {

                        dataDate.add(result.getSalesDate());
                        dataAmount.add(result.getSalesAmount());
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
}
