package seoil.capstone.som.ui.main.manager.statistics;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.StatisticsDTO;

public class ManagerStatisticsPresenter implements ManagerStatisticsContract.Presenter{

    private ManagerStatisticsContract.View mView;
    private ManagerStatisticsInteractor mInteractor;

    @Override
    public void setView(ManagerStatisticsContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new ManagerStatisticsInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
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

    public void getSalesStatistics(String shopId, String startDate, String endDate) {

        OnFinishApiListener<SalesDTO.GetStatisticsRes> onFinishApiListener = new OnFinishApiListener<SalesDTO.GetStatisticsRes>() {

            @Override
            public void onSuccess(SalesDTO.GetStatisticsRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<SalesDTO.GetStatisticsRes.Result> list = getRes.getResults();

                    ArrayList<Integer> listAmounts = new ArrayList<>();
                    ArrayList<String> listDates = new ArrayList<>();
                    int c = 0;

                    for (SalesDTO.GetStatisticsRes.Result result : list) {

                        listAmounts.add(c, result.getSalesAmount());
                        listDates.add(c, result.getSalesDate());
                        c++;
                    }

                    mView.sendSalesData(listDates, listAmounts);
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getSalesStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    public void getGenderTotal(String shopId, String startDate, String endDate) {

        OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener = new OnFinishApiListener<StatisticsDTO.GetGenderRes>() {
            @Override
            public void onSuccess(StatisticsDTO.GetGenderRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<StatisticsDTO.GetGenderRes.Result> list = getRes.getResults();

                    ArrayList<String> genderList = new ArrayList<>();
                    ArrayList<Integer> countList = new ArrayList<>();

                    int c = 0;
                    for (StatisticsDTO.GetGenderRes.Result result : list) {

                        Log.d("gender", result.getGender());
                        if (list.size() == 2) {

                            genderList.add(c, result.getGender());
                            countList.add(c, result.getCount());
                        } else if (list.size() == 1) {

                            if (result.getGender().equals("M")) {

                                genderList.add(c, result.getGender());
                                countList.add(c, result.getCount());
                                c++;
                                genderList.add(c, "W");
                                countList.add(c, 0);
                            } else {
                                genderList.add(c, "M");
                                countList.add(c, 0);
                                c++;
                                genderList.add(c, result.getGender());
                                countList.add(c, result.getCount());
                            }
                        }
                        if (list.size() == 0) {
                            genderList.add(c, "M");
                            countList.add(c, 0);
                            c++;
                            genderList.add(c, "W");
                            countList.add(c, 0);
                        }
                    }
                    mView.sendGenderTotal(genderList, countList);

                } else if (getRes.getStatus() == SalesApi.ERROR_NONE_DATA){

                    ArrayList<String> genderList = new ArrayList<>();
                    ArrayList<Integer> countList = new ArrayList<>();

                    int c = 0;
                    genderList.add(c, "M");
                    countList.add(c, 0);
                    c++;
                    genderList.add(c, "W");
                    countList.add(c, 0);

                    mView.sendGenderTotal(genderList, countList);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };
        mInteractor.getGenderTotal(shopId, startDate, endDate, onFinishApiListener);
    }

    public void getAgeTotal(String shopId, String startDate, String endDate) {

        OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener = new OnFinishApiListener<StatisticsDTO.GetAgeGroupRes>() {
            @Override
            public void onSuccess(StatisticsDTO.GetAgeGroupRes getAgeGroupRes) {

                if (getAgeGroupRes.getStatus() == SalesApi.SUCCESS) {

                    List<StatisticsDTO.GetAgeGroupRes.Result> list = getAgeGroupRes.getResults();

                    ArrayList<String> ageList = new ArrayList<>();
                    ArrayList<Integer> amountList = new ArrayList<>();
                    int c = 0;

                    for (StatisticsDTO.GetAgeGroupRes.Result result : list) {

                        if (c < result.getAgeGroup() - 1) {

                            ageList.add(c, ((c + 1) * 10) + "대");
                            amountList.add(c, 0);
                        } else {

                            ageList.add(c, result.getAgeGroup() * 10 + "대");
                            amountList.add(c, result.getCount());
                        }

                        c++;
                    }

                    mView.sendAgeTotal(ageList, amountList);
                } else {

                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getAgeTotal(shopId, startDate, endDate, onFinishApiListener);
    }
}
