package seoil.capstone.som.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://leebera.name/";
    public static Retrofit retrofit = null;

    public static final String USER_BASE_URL = "https://leebera.name/user/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
