package com.emagrorrim.pm25.NetRequest;

import com.emagrorrim.pm25.Area.AreaInfo;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by apple on 15/12/20.
 */
public interface CityAreaRequest {
    @GET("/api/querys/aqi_details.json")
    Call<List<AreaInfo>> getPM25(@Query("token") String token, @Query("city") String city);
}
