package com.emagrorrim.pm25.NetRequest;

import com.emagrorrim.pm25.Area.AreaInfo;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by apple on 15/12/22.
 */
public class CityInforFetcher {
    private static CityInforFetcher fetcher;
    private final CityAreaRequest request;

    private static final String BASE_URL = "http://www.pm25.in";
    private static String TOKEN = "";

    public static void setTOKEN(String TOKEN) {
        CityInforFetcher.TOKEN = TOKEN;
    }

    private CityInforFetcher() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(20 * 1000, TimeUnit.MILLISECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        request = builder.build().create(CityAreaRequest.class);
    }

    public static CityInforFetcher sharedFetcher() {
        if (fetcher == null) {
            fetcher = new CityInforFetcher();
        }
        return fetcher;
    }

    public void requestPM25(String city, Callback<List<AreaInfo>> callback) {
        Call<List<AreaInfo>> call = request.getPM25(TOKEN, city);
        enqueue(call, callback);
    }

    private <T> void enqueue(Call<T> call, Callback<T> callback) {
        if (call != null && callback != null) {
            call.enqueue(callback);
        }
    }
}
