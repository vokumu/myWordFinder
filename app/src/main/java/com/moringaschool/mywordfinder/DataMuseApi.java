package com.moringaschool.mywordfinder;

import com.moringaschool.mywordfinder.models.Ryme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataMuseApi {
    @GET("words")
    Call <List<Ryme>> getRymes(
            @Query("rel_rhy") String itslef
    );
}
