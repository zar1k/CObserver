package com.gmail.generationdotz2.c_observer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.generationdotz2.c_observer.R;
import com.gmail.generationdotz2.c_observer.model.Cryptocurrency;
import com.gmail.generationdotz2.c_observer.model.Wallet;
import com.gmail.generationdotz2.c_observer.util.PrettyFormat;
import com.gmail.generationdotz2.c_observer.util.TimeFormat;

public class NewWalletActivity extends AppCompatActivity {
    public static final String CURRENT_COIN = "current_coin";
    // About coin/wallet
    private ImageView walletImg;
    private TextView walletName;
    private TextView walletSymbol;
    private TextView tradePriceUSD;
    private TextView tradePriceBTC;
    private TextView marketCapRank;
    private TextView marketCap;
    private TextView percentChange24h;
    // Wallet settings.
    private TextView tradeDate;
    private TextView tradePriceCoin;
    private EditText enterQuantityCoin;
    private TextView totalValue;
    // Alert.
    private EditText enterAboveCoin;
    private TextView currentCoin;
    private EditText enterBelowCoin;
    // Toolbar
    private Toolbar toolbar;
    private Wallet wallet;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_wallet_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Intent intent = new Intent(this, WalletActivity.class);
                startActivity(intent);
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wallet);

        walletImg = findViewById(R.id.wallet_image_view);
        walletName = findViewById(R.id.wallet_name);
        walletSymbol = findViewById(R.id.wallet_symbol);
        tradePriceUSD = findViewById(R.id.trade_price_usd);
        tradePriceBTC = findViewById(R.id.trade_price_btc);
        marketCapRank = findViewById(R.id.market_cap_rank);
        marketCap = findViewById(R.id.market_cap);
        percentChange24h = findViewById(R.id.percent_change_24h);

        tradeDate = findViewById(R.id.trade_date);
        tradePriceCoin = findViewById(R.id.trade_price_coin);
        enterQuantityCoin = findViewById(R.id.enter_quantity_coin);
        totalValue = findViewById(R.id.total_value);

        enterAboveCoin = findViewById(R.id.enter_above_coin);
        currentCoin = findViewById(R.id.current_coin);
        enterBelowCoin = findViewById(R.id.enter_below_coin);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        load();
    }

    private void load() {
        Cryptocurrency coin = getIntent().getParcelableExtra(CURRENT_COIN);
        display(coin);
    }

    private void display(Cryptocurrency coin) {
        StringBuilder builder = new StringBuilder();
        String titleWalletName = builder.append("New ").append(coin.getSymbol()).append(" Wallet").toString();
        getSupportActionBar().setTitle(titleWalletName);

        walletName.setText(coin.getName());
        walletSymbol.setText(coin.getSymbol());
        tradePriceUSD.setText(coin.getPriceUsd());
        tradePriceBTC.setText(coin.getPriceBtc());
        marketCapRank.setText(coin.getRank());
        marketCap.setText(coin.getMarketCapUsd());
        percentChange24h.setText(PrettyFormat.addPercentSign(coin.getPercentChange24h()));
        percentChange24h.setTextColor(PrettyFormat.changeColor(this, coin.getPercentChange24h()));

        TimeFormat timeFormat = new TimeFormat();

        tradeDate.setText(timeFormat.convertUTCToHumanReadableDate(coin.getLastUpdated()));
        tradePriceCoin.setText(coin.getPriceUsd());

        totalValue.setText(getTotalVal());

        enterAboveCoin.getText();
        currentCoin.setText(coin.getPriceUsd());
        enterBelowCoin.getText();
    }

    public String getTotalVal() {
        if (TextUtils.isEmpty(enterQuantityCoin.getText().toString())) {
            return "";
        }
        String res;
        float quantityCoinFloat = Float.valueOf(enterQuantityCoin.getText().toString());
        float priceCoin = Float.valueOf(tradePriceCoin.getText().toString());
        res = String.valueOf(quantityCoinFloat * priceCoin);
        return res;
    }
}
