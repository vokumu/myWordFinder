package com.moringaschool.mywordfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataMuseApi {
    @GET("posts")
    Call <List<Ryme>> getRymes();
}
