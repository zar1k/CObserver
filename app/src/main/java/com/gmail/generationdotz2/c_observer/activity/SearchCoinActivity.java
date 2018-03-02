package com.gmail.generationdotz2.c_observer.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.generationdotz2.c_observer.R;
import com.gmail.generationdotz2.c_observer.adapter.CoinAdapter;
import com.gmail.generationdotz2.c_observer.api.CoinMarketCapService;
import com.gmail.generationdotz2.c_observer.api.Retro2Client;
import com.gmail.generationdotz2.c_observer.model.Cryptocurrency;
import com.gmail.generationdotz2.c_observer.util.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCoinActivity extends AppCompatActivity {
    private RecyclerView coinRecyclerView;
    private CoinAdapter coinAdapter;
    private List<Cryptocurrency> coinList;
    // Toolbar
    private Toolbar toolbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_coin);
        initRecyclerView();
        load();
        // Для поддержки версий ниже Android 5.0 (API 21).
        toolbar = findViewById(R.id.toolbar);
        // Кнопка "Назад" в тулбаре.
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.search_coin_activity_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_coin_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                coinAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when query submitted
                coinAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
        }
        super.onBackPressed();
    }

    private void initRecyclerView() {
        coinRecyclerView = findViewById(R.id.coin_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        coinRecyclerView.setLayoutManager(layoutManager);   // Отвечает за форму отображения элементо.
        coinRecyclerView.setItemAnimator(new DefaultItemAnimator());
        coinRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));

        CoinAdapter.OnCoinClickListener onCoinClickListener = new CoinAdapter.OnCoinClickListener() {
            @Override
            public void onCoinClick(Cryptocurrency coin) {
                Intent intent = new Intent(SearchCoinActivity.this, NewWalletActivity.class);
                intent.putExtra(NewWalletActivity.CURRENT_COIN, coin);
                startActivity(intent);
            }
        };
        coinList = new ArrayList<>();
        coinAdapter = new CoinAdapter(this, coinList, onCoinClickListener);
        coinRecyclerView.setAdapter(coinAdapter);
    }


    private void load() {
//        Collection<Cryptocurrency> coins = getCoinMock();
//        coinAdapter.setItems(coins);
        CoinMarketCapService coinMarketCapService = Retro2Client.getCoinMarketCapService();
        Call<Collection<Cryptocurrency>> cryptocurrencys = coinMarketCapService.getCryptocurrency();
        cryptocurrencys.enqueue(new Callback<Collection<Cryptocurrency>>() {
            @Override
            public void onResponse(Call<Collection<Cryptocurrency>> call, Response<Collection<Cryptocurrency>> response) {
                Collection<Cryptocurrency> coins = response.body();
                // adding contacts to contacts list
                coinAdapter.clearItems();
                coinAdapter.setItems(coins);

                // refreshing recycler view
                coinAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Collection<Cryptocurrency>> call, Throwable t) {
                Toast.makeText(SearchCoinActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Collection<Cryptocurrency> getCoinMock() {
        return Arrays.asList(
                new Cryptocurrency(
                        "bitcoin",
                        "Bitcoin",
                        "BTC",
                        "1",
                        "10234.3",
                        "1.0",
                        "8490130000.0",
                        "172771993407",
                        "16881662.0",
                        "16881662.0",
                        "21000000.0",
                        "1.13",
                        "-2.02",
                        "2.28",
                        "1519381768"
                ),
                new Cryptocurrency(
                        "ethereum",
                        "Ethereum",
                        "ETH",
                        "2",
                        "863.092",
                        "0.0856871",
                        "2420140000.0",
                        "84403053792.0",
                        "97791491.0",
                        "97791491.0",
                        null,
                        "0.57",
                        "3.21",
                        "-7.29",
                        "1519381752"
                ),
                new Cryptocurrency(
                        "ripple",
                        "Ripple",
                        "XRP",
                        "3",
                        "0.99849",
                        "0.00009913",
                        "1097030000.0",
                        "39035769041.0",
                        "39094802192.0",
                        "99992622540.0",
                        "100000000000",
                        "0.42",
                        "1.34",
                        "-11.44",
                        "1519381741"
                )
        );
    }
}
