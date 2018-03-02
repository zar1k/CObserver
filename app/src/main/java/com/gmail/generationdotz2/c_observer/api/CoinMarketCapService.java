package com.gmail.generationdotz2.c_observer.api;

import com.gmail.generationdotz2.c_observer.model.Cryptocurrency;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinMarketCapService {
    @GET("/v1/ticker/")
    Call<Collection<Cryptocurrency>> getCryptocurrency();
}