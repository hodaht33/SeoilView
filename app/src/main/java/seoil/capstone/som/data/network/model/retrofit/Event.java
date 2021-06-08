package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 이벤트 api 라우팅 인터페이스
public interface Event {

    // 이벤트 정보 요청
    @GET("event/{shopId}")
    Call<EventDataDTO.GetRes> getEvent(@Path("shopId") String shopId);

    // 이벤트 코드로 이벤트 정보 요청
    @GET("event/{eventCode}/event-data")
    Call<EventDataDTO.EventCodeRes> getEventByCode(@Path("eventCode") int eventCode);

    // 현재 진행중인 이벤트 정보 요청
    @GET("event/{userId}/ongoing-event")
    Call<EventDataDTO.OngoingEventRes> getOngoingEvent(@Path("userId") String userId);

    // 이벤트 추가 요청
    @POST("event")
    Call<EventDataDTO.StatusRes> insertEvent(@Body EventDataDTO.InsertReq req);

    // 이벤트 수정 요청
    @PUT("event")
    Call<EventDataDTO.StatusRes> updateEvent(@Body EventDataDTO.UpdateReq req);

    // 이벤트 삭제 요청
    @DELETE("event")
    Call<EventDataDTO.StatusRes> deleteEvent(@Query("eventCode") int eventCode);
}
