package com.gmail.generationdotz2.c_observer.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.generationdotz2.c_observer.R;
import com.gmail.generationdotz2.c_observer.adapter.WalletAdapter;
import com.gmail.generationdotz2.c_observer.model.Alert;
import com.gmail.generationdotz2.c_observer.model.Cryptocurrency;
import com.gmail.generationdotz2.c_observer.model.Wallet;
import com.gmail.generationdotz2.c_observer.util.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class WalletActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String CURRENT_WALLET = WalletActivity.class.getName();
    private WalletAdapter adapter;
    private RecyclerView recyclerView;
    private HashMap<String, Wallet> walletMap;
    // SwipeRefreshLayout
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        walletMap = new LinkedHashMap<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.wallet_recycler_view);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));

        WalletAdapter.WalletClickListener walletClickListener = new WalletAdapter.WalletClickListener() {
            @Override
            public void onWalletClick(Wallet wallet) {

            }
        };
//        wallets = new ArrayList<>();
//        adapter = new WalletAdapter(walletClickListener, wallets, this);
//        recyclerView.setAdapter(adapter);

        // show loader and fetch coins
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                load();
            }
        });
    }

    private void load() {
        swipeRefreshLayout.setRefreshing(true);
//        Wallet wallet = getIntent().getParcelableExtra(CURRENT_WALLET);
//
//        wallets.add(wallet);
//        adapter.setItems(wallets);

        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        // swipe refresh is performed, fetch the coins again
        load();
    }
}