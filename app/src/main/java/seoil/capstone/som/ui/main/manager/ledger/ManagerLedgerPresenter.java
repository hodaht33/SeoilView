package seoil.capstone.som.ui.main.manager.ledger;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.api.StockApi;
import seoil.capstone.som.data.network.model.StockDTO;

// 가계부 프레젠터
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

    //영어로된 요일을 입력받아 한글로 반환
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

    //int 형식의 날짜를 2021-05-26 의 형식으로 변환
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
    
    //EditText의 Text가 있는지 확인
    public int isTextSet(String str) {
        if (str == null || str.equals("")) {

            return TEXT_LENGTH_NONE;
        } else if (str.length() > 11 || str.length() <= 0) {

            return TEXT_LENGTH_OVER;
        }
        return TEXT_LENGTH_INVALID;
    }

    // 재고 조회
    public void getStock(String shopId) {

        OnFinishApiListener<StockDTO.GetRes> onFinishApiListener = new OnFinishApiListener<StockDTO.GetRes>() {
            @Override
            public void onSuccess(StockDTO.GetRes getRes) {

                if (getRes.getStatus() == StockApi.SUCCESS) {

                    List<StockDTO.GetRes.Result> list = getRes.getResults();
                    
                    ArrayList<String> dataName = new ArrayList<>();
                    ArrayList<Integer> dataAmount = new ArrayList<>();
                    ArrayList<Integer> dataCode = new ArrayList<>();


                    for (StockDTO.GetRes.Result result : list) {

                        dataCode.add(result.getStockCode());
                        dataName.add(result.getStockName());
                        dataAmount.add(result.getStockAmount());
                    }

                    view.setLayoutAdapterStock(dataName, dataCode, dataAmount);
                } else if (getRes.getStatus() == SalesApi.ERROR_NONE_DATA){

                    view.setLayoutAdapterStock(null, null, null);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        };

        mInteractor.getStock(shopId, onFinishApiListener);
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

    //view에서 shopId, name, amount를 받아와 Model에 전달 후 재고 조회
    public void insertStock(Integer stockCode, String shopId, String name, int amount) {

        OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<StockDTO.StatusRes>() {

            @Override
            public void onSuccess(StockDTO.StatusRes statusRes) {

                if (statusRes.getStatus() == StockApi.SUCCESS) {

                    view.initStock();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.insertStock(stockCode, shopId, name, amount, onFinishApiListener);
    }

    //view에서 shopId, name, amount를 받아와 Model에 전달 후 재고 조회
    public void updateStock (Integer stockCode, String shopId, String name, String newName, int amount) {

        OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<StockDTO.StatusRes>() {
            @Override
            public void onSuccess(StockDTO.StatusRes statusRes) {

                view.initStock();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.updateStock(stockCode, shopId, name, newName, amount, onFinishApiListener);
    }

    //view에서 shopId, name, amount를 받아와 Model에 전달 후 재고 조회
    public void deleteStock(String shopId, Integer stockCode, String name) {

        OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener = new OnFinishApiListener<StockDTO.StatusRes>() {
            @Override
            public void onSuccess(StockDTO.StatusRes statusRes) {

                view.initStock();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.deleteStock(shopId, stockCode, name, onFinishApiListener);
    }


}