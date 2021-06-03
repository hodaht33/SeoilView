package seoil.capstone.som.data.network.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.model.EventData;

public interface Event {

    @GET("event/{shopId}")
    Call<EventData.GetRes> getEvent(@Path("shopId") String shopId);

    @GET("event/{eventCode}/event-data")
    Call<EventData.EventCodeRes> getEventByCode(@Path("eventCode") int eventCode);

    @GET("event/{userId}/ongoing-event")
    Call<EventData.OngoingEventRes> getOngoingEvent(@Path("userId") String userId);

    @POST("event")
    Call<EventData.StatusRes> insertEvent(@Body EventData.InsertReq req);

    @PUT("event")
    Call<EventData.StatusRes> updateEvent(@Body EventData.UpdateReq req);

    @DELETE("event")
    Call<EventData.StatusRes> deleteEvent(@Query("eventCode") int eventCode);
}
