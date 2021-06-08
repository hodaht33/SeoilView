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
import seoil.capstone.som.data.network.model.BookmarkDTO;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.EventDataDTO;
import seoil.capstone.som.data.network.model.LoginDTO;
import seoil.capstone.som.data.network.model.PointDTO;
import seoil.capstone.som.data.network.model.UserDTO;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.ShopDTO;
import seoil.capstone.som.data.network.model.StatisticsDTO;
import seoil.capstone.som.data.network.model.StockDTO;

// api 관리자
// 싱글턴 클래스
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

    // 공용 404에러 확인 메서드
    public boolean check404Error(Response response, OnFinishApiListener onFinishApiListener) {

        if (response.code() == 404) {

            onFinishApiListener.onFailure(new Throwable("404 Error(server offline or not exist page)"));

            return true;
        }

        return false;
    }

    // 서버 로그인 요청
    public void serverLogin(LoginDTO.LoginReq req, OnFinishApiListener<LoginDTO.LoginRes> onFinishApiListener) {

        mUserApi.login(req, onFinishApiListener);
    }

    // 카카오 간편 로그인 요청
    public void kakaoLogin(Context context, OnFinishApiListener<LoginDTO.KakaoLoginRes> onFinishApiListener) {

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

                                onFinishApiListener.onSuccess(new LoginDTO.KakaoLoginRes(String.valueOf(user.getId())));
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

    // 네이버 간편 로그인 요청
    public void naverLogin(Context context, Resources res, OnFinishApiListener<LoginDTO.NaverLoginRes> onFinishApiListener) {

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

                                onFinishApiListener.onSuccess(new LoginDTO.NaverLoginRes(
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

    // 인증번호 문자 전송 요청
    public void sendSms(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        mUserApi.sendSms(req, onFinishApiListener);
    }

    // 인증번호 확인 요청
    public void sendAuthCode(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        mUserApi.sendAuthCode(req, onFinishApiListener);
    }

    // 아이디 찾기를 위한 인증번호 확인 요청(아이디 찾기)
    public void sendAuthForFindId(AuthDTO.Req req, OnFinishApiListener<UserDTO.FindIdRes> onFinishApiListener) {

        mUserApi.sendAuthForFindId(req, onFinishApiListener);
    }

    // 사용자 핸드폰 번호 요청
    public void getUserPhoneNumber(String userId, OnFinishApiListener<UserDTO.GetUserInfoRes> onFinishApiListener) {

        mUserApi.getUserPhoneNumber(userId, onFinishApiListener);
    }

    // 아이디 중복 확인 요청
    public void checkIdDuplicate(String id, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        mUserApi.checkIdDuplicate(id, onFinishApiListener);
    }

    // 사업자 등록 번호 확인 요청
    public void checkRegistrationNumber(String number, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        mUserApi.checkRegistrationNumber(number, onFinishApiListener);
    }

    // 손님 회원가입 요청
    public void customerRegister(UserDTO.Customer registerRequest, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        mUserApi.insertCustomer(registerRequest, onFinishApiListener);
    }

    // 점주 회원가입 요청
    public void managerRegister(UserDTO.Manager managerRequest, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        mUserApi.insertManager(managerRequest, onFinishApiListener);
    }

    // 사용자 삭제 요청
    public void deleteUser(String id, OnFinishApiListener onFinishApiListener) {

        mUserApi.deleteUser(id, onFinishApiListener);
    }

    // 잔여 포인트 확인 요청
    public void getCurrentPoint(String id, OnFinishApiListener<PointDTO.GetCurrentRes> onFinishApiListener) {

        mPointApi.getCurrentPoint(id, onFinishApiListener);
    }

    // 잔여 포인트 튜플 추가 요청
    public void insertCurrentPoint(PointDTO.InsertCurrentReq req, OnFinishApiListener<PointDTO.StatusRes> onFinishApiListener) {

        mPointApi.insertCurrentPointTuple(req, onFinishApiListener);
    }

    // 잔여 포인트 수정 요청
    public void updateCurrentPoint(PointDTO.UpdateCurrentReq req, OnFinishApiListener<PointDTO.StatusRes> onFinishApiListener) {

        mPointApi.updateCurrentPoint(req, onFinishApiListener);
    }

    // 사용 내역 요청
    public void getUsingPoint(String id, OnFinishApiListener<PointDTO.GetUsingRes> onFinishApiListener) {

        mPointApi.getUsingPointData(id, onFinishApiListener);
    }

    // 사용 내역 추가 요청
    public void insertUsingPoint(PointDTO.InsertUsingReq req, OnFinishApiListener<PointDTO.StatusRes> onFinishApiListener) {

        mPointApi.insertUsingPointData(req, onFinishApiListener);
    }

    // 적립 내역 요청
    public void getSavePoint(String id, OnFinishApiListener<PointDTO.GetSaveRes> onFinishApiListener) {

        mPointApi.getSavePointData(id, onFinishApiListener);
    }

    // 적립 내역 추가 요청
    public void insertSavePoint(PointDTO.InsertSaveReq req, OnFinishApiListener<PointDTO.StatusRes> onFinishApiListener) {

        mPointApi.insertSavePointData(req, onFinishApiListener);
    }

    // 사용 내역, 적립 내역 요청
    public void getUsingAndSavePoint(String userId, OnFinishApiListener<PointDTO.GetUsingAndSaveRes> onFinishApiListener) {

        mPointApi.getUsingAndSavePointData(userId, onFinishApiListener);
    }

    // 비밀번호 수정 요청
    public void updatePassword(String userId, UserDTO.ChangePasswordReq req, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        mUserApi.updatePassword(userId, req, onFinishApiListener);
    }

    // 매장 정보 요청
    public void getShopInformation(String shopId, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        mShopApi.getShopInformation(shopId, onFinishApiListener);
    }

    // 키워드로 검색된 매장 정보 요청
    public void getShopInfoWithKeyword(String keyword, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        mShopApi.getShopInfoWithKeyword(keyword, page, onFinishApiListener);
    }

    // 카테고리로 검색된 매장 정보 요청
    public void getShopInfoWithCategory(String category, int page, OnFinishApiListener<ShopDTO.GetRes> onFinishApiListener) {

        mShopApi.getShopInfoWithCategory(category, page, onFinishApiListener);
    }

    // 매장 정보 추가 요청
    public void insertShopInfo(ShopDTO.InsertReq req, OnFinishApiListener<ShopDTO.StatusRes> onFinishApiListener) {

        mShopApi.insertShopInfo(req, onFinishApiListener);
    }

    // 매장 정보 수정 요청
    public void updateShopInfo(ShopDTO.UpdateReq req, OnFinishApiListener<ShopDTO.StatusRes> onFinishApiListener) {

        mShopApi.updateShopInfo(req, onFinishApiListener);
    }

    // 매장 정보 삭제 요청
    public void deleteShopInfo(String shopId, String shopCode, OnFinishApiListener<ShopDTO.StatusRes> onFinishApiListener) {

        mShopApi.deleteShopInfo(shopId, shopCode, onFinishApiListener);
    }

    // 수입 정보 요청
    public void getIncomeSales(String shopId, String salesDate, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        mSalesApi.getIncomeSales(shopId, salesDate, onFinishApiListener);
    }

    // 지출 정보 요청
    public void getSpendingSales(String shopId, String salesDate, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        mSalesApi.getSpendingSales(shopId, salesDate, onFinishApiListener);
    }

    // 매출 통계 요청
    public void getStatisticsSales(String shopId, String startDate, String endDate, OnFinishApiListener<SalesDTO.GetStatisticsRes> onFinishApiListener) {

        mSalesApi.getSalesStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    // 지출 정보 수정 요청
    public void updateSpendingSales(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        mSalesApi.updateSpendingSales(req, onFinishApiListener);
    }

    // 지출 정보 삭제 요청
    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        mSalesApi.deleteSpendingSales(shopId, salesCode, salesDate, onFinishApiListener);
    }

    // 날짜와 함께 매출 정보 추가 요청
    public void insertSalesWithDate(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        mSalesApi.insertSalesWithDate(req, onFinishApiListener);
    }

    // 매출 정보 추가 요청
    public void insertSales(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        mSalesApi.insertSales(req, onFinishApiListener);
    }

    // 즐겨찾기에서의 매장 정보 요청
    public void getBookmarkShopInfo(String userId, OnFinishApiListener<BookmarkDTO.ShopInfoRes> onFinishApiListener) {

        mBookmarkApi.getShopInfo(userId, onFinishApiListener);
    }

    // 즐겨찾기에서의 손님 정보 요청
    public void getBookmarkUserInfo(String shopId, OnFinishApiListener<BookmarkDTO.UserInfoRes> onFinishApiListener) {

        mBookmarkApi.getUserInfo(shopId, onFinishApiListener);
    }

    // 현재 진행중인 이벤트 정보 요청
    public void getOngoingEvent(String userId, OnFinishApiListener onFinishApiListener) {

        mEventApi.getOngoingEvent(userId, onFinishApiListener);
    }

    // 즐겨찾기 추가 요청
    public void addBookmark(BookmarkDTO.InsertReq createReq, OnFinishApiListener<BookmarkDTO.StatusRes> onFinishApiListener) {

        mBookmarkApi.addBookmark(createReq, onFinishApiListener);
    }

    // 즐겨찾기 삭제 요청
    public void deleteBookmark(String userId, String shopId, OnFinishApiListener<BookmarkDTO.StatusRes> onFinishApiListener) {

        mBookmarkApi.deleteBookmark(userId, shopId, onFinishApiListener);
    }

    // 재고 정보 요청
    public void getStock(String shopId, OnFinishApiListener<StockDTO.GetRes> onFinishApiListener) {

        mStockApi.getStock(shopId, onFinishApiListener);
    }

    // 재고 추가 요청
    public void insertStock(StockDTO.Req req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        mStockApi.insertStock(req, onFinishApiListener);
    }

    // 재고 수량 수정 요청
    public void updateStockAmount(StockDTO.Req req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        mStockApi.updateStockAmount(req, onFinishApiListener);
    }

    // 재고 수정 요청
    public void updateStock(StockDTO.UpdateAllReq req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        mStockApi.updateStock(req, onFinishApiListener);
    }

    // 재고 삭제 요청
    public void deleteStock(String shopId, String stockName, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        mStockApi.deleteStock(shopId, stockName, onFinishApiListener);
    }

    // 나이대 통계 요청
    public void getAgeGroupStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetAgeGroupRes> onFinishApiListener) {

        mStatisticsApi.getAgeGroupStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    // 성별 통계 요청
    public void getGenderStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<StatisticsDTO.GetGenderRes> onFinishApiListener) {

        mStatisticsApi.getGenderStatistics(shopId, startDate, endDate, onFinishApiListener);
    }

    // 일별 매출 통계 요청
    public void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getDailySales(shopId, starDate, endDate, onFinishApiListener);
    }

    // 주별 매출 통계 요청
    public void getWeeklySales(String shopId, String month, String startDate, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getWeeklySales(shopId, month, startDate, onFinishApiListener);
    }

    // 월별 매출 통계 요청
    public void getMonthlySales(String shopId, String year, OnFinishApiListener onFinishApiListener) {

        mStatisticsApi.getMonthlySales(shopId, year, onFinishApiListener);
    }

    // 통계(방문 정보) 추가 요청
    public void insertStatisticsData(StatisticsDTO.InsertReq req, OnFinishApiListener<StatisticsDTO.StatusRes> onFinishApiListener) {

        mStatisticsApi.insertStatisticsData(req, onFinishApiListener);
    }

    // 이벤트 정보 요청
    public void getEvent(String shopId, OnFinishApiListener<EventDataDTO.GetRes> onFinishApiListener) {

        mEventApi.getEvent(shopId, onFinishApiListener);
    }

    // 이벤트 코드로 검색된 이벤트 정보 요청
    public void getEventByCode(int eventCode, OnFinishApiListener<EventDataDTO.EventCodeRes> onFinishApiListener) {

        mEventApi.getEventByCode(eventCode, onFinishApiListener);
    }

    // 이벤트 추가 요청
    public void insertEvent(EventDataDTO.InsertReq req, OnFinishApiListener onFinishApiListener) {

        mEventApi.insertEvent(req, onFinishApiListener);
    }

    // 이벤트 수정 요청
    public void updateEvent(EventDataDTO.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        mEventApi.updateEvent(req, onFinishApiListener);
    }

    // 이벤트 삭제 요청
    public void deleteEvent(int eventCode, OnFinishApiListener onFinishApiListener) {

        mEventApi.deleteEvent(eventCode, onFinishApiListener);
    }
}
