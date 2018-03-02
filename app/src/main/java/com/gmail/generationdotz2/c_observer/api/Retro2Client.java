package com.gmail.generationdotz2.c_observer.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro2Client {
    private static final String BASE_URL = "https://api.coinmarketcap.com";

    private static Retrofit getInstance() {
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CoinMarketCapService getCoinMarketCapService() {
        return getInstance().create(CoinMarketCapService.class);
    }
}