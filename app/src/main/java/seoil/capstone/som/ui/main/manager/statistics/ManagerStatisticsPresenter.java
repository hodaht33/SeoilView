package seoil.capstone.som.ui.main.manager.statistics;

import android.util.Log;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;

import java.util.ArrayList;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.api.StatisticsApi;
import seoil.capstone.som.data.network.model.StatisticsDTO;

// 통계 프레젠터
public class ManagerStatisticsPresenter implements ManagerStatisticsContract.Presenter{

    private ManagerStatisticsContract.View mView;               //점주 통계 프레그먼트의 뷰
    private ManagerStatisticsInteractor mInteractor;            //점주 통계 프레그먼트의 모델

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

    //날짜 조회 쿼리에 맞는 문자열 생성
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

    //일별 매출 통계 조회
    public void getDailySales(String shopId, String starDate, String endDate) {

        OnFinishApiListener<StatisticsDTO.GetDayRes> onFinishApiListener = new OnFinishApiListener<StatisticsDTO.GetDayRes>() {
            @Override
            public void onSuccess(StatisticsDTO.GetDayRes getDayRes) {

                if (getDayRes.getStatus() == StatisticsApi.SUCCESS) {

                    List<StatisticsDTO.GetDayRes.Result> list = getDayRes.getResults();

                    ArrayList<ManagerStatisticsAdapter.Item> dailySalesList = new ArrayList<>();

                    ArrayList<String> date = new ArrayList<>();
                    ArrayList<Integer> sales = new ArrayList<>();
                    ArrayList<Integer> cost = new ArrayList<>();

                    for (StatisticsDTO.GetDayRes.Result result : list) {

                        if (date.contains(result.getSalesDate())) {         //지출 혹은 매출이 이미 추가 되었을 때

                            int index = date.indexOf(result.getSalesDate());

                            if (result.getSalesAmount() < 0) {              //값이 음수이면 지출에 추가

                                cost.set(index, result.getSalesAmount());
                            } else {                                        //값이 양수이면 매출에 추가

                                sales.set(index, result.getSalesAmount());
                            }
                        } else {                                            //값이 추가 되지 않았을 때

                            date.add(result.getSalesDate());
                            int index = date.indexOf(result.getSalesDate());

                            if (result.getSalesAmount() < 0) {              //값이 음수이면 지출에 추가

                                cost.add(index, result.getSalesAmount());
                                sales.add(index, null);
                            } else {                                        //값이 양수이면 매출에 추가

                                sales.add(index, result.getSalesAmount());
                                cost.add(index, null);
                            }
                        }
                    }

                    for (int i = 0; i < date.size(); i++) {

                        dailySalesList.add(new ManagerStatisticsAdapter.Item(date.get(i), sales.get(i), cost.get(i)));
                    }

                    mView.setAdapterDaily(dailySalesList);
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getDailySales(shopId, starDate, endDate, onFinishApiListener);
    }
    
    //성별 통계 조회
    public void getGenderTotal(String shopId, String startDate, String endDate) {

        OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener = new OnFinishApiListener<StatisticsDTO.GetGenderRes>() {
            @Override
            public void onSuccess(StatisticsDTO.GetGenderRes getRes) {

                if (getRes.getStatus() == SalesApi.SUCCESS) {

                    List<StatisticsDTO.GetGenderRes.Result> list = getRes.getResults();

                    ArrayList<DataEntry> genderTotalList = new ArrayList<>();

                    for (StatisticsDTO.GetGenderRes.Result result : list) {

                        Log.d("gender", result.getGender());
                        if (list.size() == 2) {

                            genderTotalList.add(new ValueDataEntry(result.getGender(), result.getCount()));
                        } else if (list.size() == 1) {

                            if (result.getGender().equals("M")) {

                                genderTotalList.add(new ValueDataEntry(result.getGender(), result.getCount()));
                                genderTotalList.add(new ValueDataEntry("W", 0));
                            } else {
                                genderTotalList.add(new ValueDataEntry("M", 0));
                                genderTotalList.add(new ValueDataEntry(result.getGender(), result.getCount()));
                            }
                        }
                        if (list.size() == 0) {
                            genderTotalList.add(new ValueDataEntry("M", 0));
                            genderTotalList.add(new ValueDataEntry("W", 0));
                        }

                        mView.setGenderChart(genderTotalList);
                    }

                } else {

                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getGenderTotal(shopId, startDate, endDate, onFinishApiListener);
    }

    //나이대 별 통계 조회
    public void getAgeTotal(String shopId, String startDate, String endDate) {

        OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener = new OnFinishApiListener<StatisticsDTO.GetAgeGroupRes>() {
            @Override
            public void onSuccess(StatisticsDTO.GetAgeGroupRes getAgeGroupRes) {

                if (getAgeGroupRes.getStatus() == StatisticsApi.SUCCESS) {

                    List<StatisticsDTO.GetAgeGroupRes.Result> list = getAgeGroupRes.getResults();

                    ArrayList<DataEntry> genderTotalList = new ArrayList<>();

                    int c = 0;

                    for (StatisticsDTO.GetAgeGroupRes.Result result : list) {

                        if (c < result.getAgeGroup() - 1) {

                            for (int i = c; i < result.getAgeGroup() - 2; i++) {

                                genderTotalList.add(new ValueDataEntry((c + 1) * 10 + "대", 0));
                            }
                            genderTotalList.add(new ValueDataEntry(result.getAgeGroup() * 10 + "대", result.getCount()));
                        } else {

                            genderTotalList.add(new ValueDataEntry(result.getAgeGroup() * 10 + "대", result.getCount()));
                        }

                        c++;
                    }

                    mView.setAgeChart(genderTotalList);
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
