package seoil.capstone.som.data.network;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import seoil.capstone.som.R;
import seoil.capstone.som.data.network.api.BookmarkApi;
import seoil.capstone.som.data.network.api.EventApi;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.api.StatisticsApi;
import seoil.capstone.som.data.network.api.StockApi;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.BookmarkData;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.EventData;
import seoil.capstone.som.data.network.model.Login;
import seoil.capstone.som.data.network.model.PointData;
import seoil.capstone.som.data.network.model.UserData;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.ShopData;
import seoil.capstone.som.data.network.model.StatisticsData;
import seoil.capstone.som.data.network.model.StockData;

public class AppApiHelper {

    public static final String BASE_URL = "https://leebera.name/api/";

    private static AppApiHelper mAppApiHelper;
    private UserApi mUserApi;
    private PointApi mPointApi;
    private ShopApi mShopApi;
    private SalesApi mSalesApi;
    private BookmarkApi mBookmarkApi;
    private StockApi mStockApi;
    private StatisticsApi mStatisticsApi;
    private EventApi mEventApi;

    public AppApiHelper() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mUserApi = new UserApi(retrofit);
        mPointApi = new PointApi(retrofit);
        mShopApi = new ShopApi(retrofit);
        mSalesApi = new SalesApi(retrofit);
        mBookmarkApi = new BookmarkApi(retrofit);
        mStockApi = new StockApi(retrofit);
        mStatisticsApi = new StatisticsApi(retrofit);
        mEventApi = new EventApi(retrofit);
    }

    public static AppApiHelper getInstance() {

        if (mAppApiHelper == null) {

            mAppApiHelper = new AppApiHelper();
        }

        return mAppApiHelper;
    }

    public boolean check404Error(Response response, OnFinishApiListener onFinishApiListener) {

        if (response.code() == 404) {

            onFinishApiListener.onFailure(new Throwable("404 Error(server offline or not exist page)"));

            return true;
        }

        return false;
    }

    public void serverLogin(Login.LoginReq req, OnFinishApiListener<Login.LoginRes> onFinishApiListener) {

        mUserApi.login(req, onFinishApiListener);
    }

    public void kakaoLogin(Context context, OnFinishApiListener<Login.KakaoLoginRes> onFinishApiListener) {

        // 카카오 로그인 콜백 함수 정의
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {

                // 로그인 정상 수행
                if (oAuthToken != null) {

                    Log.d("API", "oAuth is available");

                    // 카카오 로그인 사용자의 uid받아 서버에 보내 회원인지 확인
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {

                            // 카카오 유저가 정상적으로 있을 경우
                            if (user != null) {

                                onFinishApiListener.onSuccess(new Login.KakaoLoginRes(String.valueOf(user.getId())));
                            }

                            return null;
                        }
                    });
                }

                // 로그인 실패
                if (throwable != null) {

                    // 예외 처리
                    Log.d("API", "throwable: " + throwable);
                }

                return null;
            }
        };
        // 카카오 어플 존재하는지 확인
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(context)) {

            // 존재하므로 어플로 로그인
            UserApiClient.getInstance().loginWithKakaoTalk(context, callback);
        } else {

            // 존재하지 않아 웹으로 로그인
            UserApiClient.getInstance().loginWithKakaoAccount(context, callback);
        }
    }

    public void naverLogin(Context context, Resources res, OnFinishApiListener<Login.NaverLoginRes> onFinishApiListener) {

        OAuthLogin oAuthLogin = OAuthLogin.getInstance();
        oAuthLogin.init(
                context
                ,res.getString(R.string.naver_client_id)
                ,res.getString(R.string.naver_client_secret)
                ,res.getString(R.string.naver_client_name)
        );

        @SuppressLint("HandlerLeak") OAuthLoginHandler oAuthLoginHandler = new OAuthLoginHandler() {
            @Override
            public void run(boolean success) {
                if (success) {

                    Thread getDataThread = new Thread() {
                        @Override
                        public void run() {

                            String accessToken = oAuthLogin.getAccessToken(context);
                            String naverLoginData = oAuthLogin.requestApi(context, accessToken, "https://openapi.naver.com/v1/nid/me");

                            try {

                                JSONObject jsonResult = new JSONObject(naverLoginData).getJSONObject("response");
                                String gender = jsonResult.getString("gender");

                                // 성별이 알 수 없음 일 때
                                if (gender.equals("U")) {

                                    gender = "M";
                                }

                                onFinishApiListener.onSuccess(new Login.NaverLoginRes(
                                        jsonResult.getString("id"),
                                        jsonResult.getString("birthyear") + jsonResult.getString("birthday").replace("-", ""),
                                        gender,
                                        jsonResult.getString("email"),
                                        jsonResult.getString("mobile").replace("-", "")
                                ));
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }
                    };

                    getDataThread.start();
                    try {

                        getDataThread.join();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                } else {

                    String errorCode = oAuthLogin.getLastErrorCode(context).getCode();
                    String errorDesc = oAuthLogin.getLastErrorDesc(context);
                }
            };
        };

        // OAuth로그인 핸들러 넘겨 수행
        oAuthLogin.startOauthLoginActivity((Activity) context, oAuthLoginHandler);
    }

    public void sendSms(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        mUserApi.sendSms(req, onFinishApiListener);
    }

    public void sendAuthCode(Auth.Req req, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        mUserApi.sendAuthCode(req, onFinishApiListener);
    }

    public void sendAuthForFindId(Auth.Req req, OnFinishApiListener<UserData.FindIdRes> onFinishApiListener) {

        mUserApi.sendAuthForFindId(req, onFinishApiListener);
    }

    public void getUserPhoneNumber(String userId, OnFinishApiListener<UserData.GetUserInfoRes> onFinishApiListener) {

        mUserApi.getUserPhoneNumber(userId, onFinishApiListener);
    }

    public void checkIdDuplicate(String id, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        mUserApi.checkIdDuplicate(id, onFinishApiListener);
    }

    public void checkRegistrationNumber(String number, OnFinishApiListener<Auth.StatusRes> onFinishApiListener) {

        mUserApi.checkRegistrationNumber(number, onFinishApiListener);
    }

    public void customerRegister(UserData.Customer registerRequest, OnFinishApiListener<UserData.StatusRes> onFinishApiListener) {

        mUserApi.insertCustomer(registerRequest, onFinishApiListener);
    }

    public void deleteUser(String id, OnFinishApiListener onFinishApiListener) {

        mUserApi.deleteUser(id, onFinishApiListener);
    }

    public void getCurrentPoint(String id, OnFinishApiListener<PointData.GetCurrentRes> onFinishApiListener) {

        mPointApi.getCurrentPoint(id, onFinishApiListener);
    }

    public void insertCurrentPoint(PointData.InsertCurrentReq req, OnFinishApiListener<PointData.StatusRes> onFinishApiListener) {

        mPointApi.insertCurrentPointTuple(req, onFinishApiListener);
    }

    public void updateCurrentPoint(PointData.UpdateCurrentReq req, OnFinishApiListener<PointData.StatusRes> onFinishApiListener) {

        mPointApi.updateCurrentPoint(req, onFinishApiListener);
    }

    public void getUsingPoint(String id, OnFinishApiListener<PointData.GetUsingRes> onFinishApiListener) {

        mPointApi.getUsingPointData(id, onFinishApiListener);
    }

    public void insertUsingPoint(PointData.InsertUsingReq req, OnFinishApiListener<PointData.StatusRes> onFinishApiListener) {

        mPointApi.insertUsingPointData(req, onFinishApiListener);
    }

    public void getSavePoint(String id, OnFinishApiListener<PointData.GetSaveRes> onFinishApiListener) {

        mPointApi.getSavePointData(id, onFinishApiListener);
    }

    public void insertSavePoint(PointData.InsertSaveReq req, OnFinishApiListener<PointData.StatusRes> onFinishApiListener) {

        mPointApi.insertSavePointData(req, onFinishApiListener);
    }

    public void getUsingAndSavePoint(String userId, OnFinishApiListener<PointData.GetUsingAndSaveRes> onFinishApiListener) {

        mPointApi.getUsingAndSavePointData(userId, onFinishApiListener);
    }

    public void managerRegister(UserData.Manager managerRequest, OnFinishApiListener<UserData.StatusRes> onFinishApiListener) {

        mUserApi.insertManager(managerRequest, onFinishApiListener);
    }

    public void updatePassword(String userId, UserData.ChangePasswordReq req, OnFinishApiListener<UserData.StatusRes> onFinishApiListener) {

        mUserApi.updatePassword(userId, req, onFinishApiListener);
    }

    public void getShopInformation(String shopId, OnFinishApiListener<ShopData.GetRes> onFinishApiListener) {

        mShopApi.getShopInformation(shopId, onFinishApiListener);
    }

    public void getShopInfoWithKeyword(String keyword, int page, OnFinishApiListener<ShopData.GetRes> onFinishApiListener) {

        mShopApi.getShopInfoWithKeyword(keyword, page, onFinishApiListener);
    }

    public void getShopInfoWithCategory(String category, int page, OnFinishApiListener<ShopData.GetRes> onFinishApiListener) {

        mShopApi.getShopInfoWithCategory(category, page, onFinishApiListener);
    }

    public void insertShopInfo(ShopData.InsertReq req, OnFinishApiListener<ShopData.StatusRes> onFinishApiListener) {

        mShopApi.insertShopInfo(req, onFinishApiListener);
    }

    public void updateShopInfo(ShopData.UpdateReq req, OnFinishApiListener<ShopData.StatusRes> onFinishApiListener) {

        mShopApi.updateShopInfo(req, onFinishApiListener);
    }

    public void deleteShopInfo(String shopId, String shopCode, OnFinishApiListener<ShopData.StatusRes> onFinishApiListener) {

        mShopApi.deleteShopInfo(shopId, shopCode, onFinishApiListener);
    }

    public void getIncomeSales(String shopId, String salesDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        mSalesApi.getIncomeSales(shopId, salesDate, onFinishApiListener);
    }

    public void getSpendingSales(String shopId, String salesDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        mSalesApi.getSpendingSales(shopId, salesDate, onFinishApiListener);
    }

    public void getStatisticsSales(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetStatisticsRes> onFinishApiListener) {

        mSalesApi.getSalesStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    public void updateSpendingSales(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        mSalesApi.updateSpendingSales(req, onFinishApiListener);
    }

    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        mSalesApi.deleteSpendingSales(shopId, salesCode, salesDate, onFinishApiListener);
    }

    public void insertSalesWithDate(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        mSalesApi.insertSalesWithDate(req, onFinishApiListener);
    }

    public void insertSales(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        mSalesApi.insertSales(req, onFinishApiListener);
    }

    public void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkData.ShopInfoRes> onFinishApiListener) {

        mBookmarkApi.getShopInfo(userId, onFinishApiListener);
    }

    public void getBookmarkUserInfo(String shopId, OnFinishApiListener<BookmarkData.UserInfoRes> onFinishApiListener) {

        mBookmarkApi.getUserInfo(shopId, onFinishApiListener);
    }

    public void getOngoingEvent(String userId, OnFinishApiListener onFinishApiListener) {

        mEventApi.getOngoingEvent(userId, onFinishApiListener);
    }

    public void addBookmark(BookmarkData.InsertReq createReq, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener) {

        mBookmarkApi.addBookmark(createReq, onFinishApiListener);
    }

    public void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkData.StatusRes> onFinishApiListener) {

        mBookmarkApi.deleteBookmark(userId, shopId, onFinishApiListener);
    }

    public void getStock(String shopId, OnFinishApiListener<StockData.GetRes> onFinishApiListener) {

        mStockApi.getStock(shopId, onFinishApiListener);
    }

    public void insertStock(StockData.Req req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        mStockApi.insertStock(req, onFinishApiListener);
    }

    public void updateStockAmount(StockData.Req req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        mStockApi.updateStockAmount(req, onFinishApiListener);
    }

    public void updateStock(StockData.UpdateAllReq req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        mStockApi.updateStock(req, onFinishApiListener);
    }

    public void deleteStock(String shopId, String stockName, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        mStockApi.deleteStock(shopId, stockName, onFinishApiListener);
    }

    public void getAgeGroupStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetAgeGroupRes> onFinishApiListener) {

        mStatisticsApi.getAgeGroupStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    public void getGenderStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsData.GetGenderRes> onFinishApiListener) {

        mStatisticsApi.getGenderStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    public void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getDailySales(shopId, starDate, endDate, onFinishApiListener);
    }

    public void getWeeklySales(String shopId, String month, String startDate, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getWeeklySales(shopId, month, startDate, onFinishApiListener);
    }

    public void getMonthlySales(String shopId, String year, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getMonthlySales(shopId, year, onFinishApiListener);
    }

    public void insertStatisticsData(StatisticsData.InsertReq req, OnFinishApiListener<StatisticsData.StatusRes> onFinishApiListener) {

        mStatisticsApi.insertStatisticsData(req, onFinishApiListener);
    }

    public void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener) {

        mEventApi.getEvent(shopId, onFinishApiListener);
    }

    public void getEventByCode(int eventCode, OnFinishApiListener<EventData.EventCodeRes> onFinishApiListener) {

        mEventApi.getEventByCode(eventCode, onFinishApiListener);
    }

    public void insertEvent(EventData.InsertReq req, OnFinishApiListener onFinishApiListener) {

        mEventApi.insertEvent(req, onFinishApiListener);
    }

    public void updateEvent(EventData.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        mEventApi.updateEvent(req, onFinishApiListener);
    }

    public void deleteEvent(int eventCode, OnFinishApiListener onFinishApiListener) {

        mEventApi.deleteEvent(eventCode, onFinishApiListener);
    }
}
