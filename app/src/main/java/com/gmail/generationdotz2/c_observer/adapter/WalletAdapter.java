package com.gmail.generationdotz2.c_observer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.generationdotz2.c_observer.R;
import com.gmail.generationdotz2.c_observer.model.Wallet;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    private WalletClickListener walletClickListener;
    private List<Wallet> wallets;
    private Context context;

    public WalletAdapter(WalletClickListener walletClickListener, List<Wallet> wallets, Context context) {
        this.walletClickListener = walletClickListener;
        this.wallets = wallets;
        this.context = context;
    }

    @Override
    public WalletViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_item_view, parent, false);
        return new WalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WalletViewHolder holder, int position) {
        final Wallet wallet = wallets.get(position);
        holder.bind(wallet);
    }

    public void setItems(Collection<Wallet> walletCollection) {
        wallets.addAll(walletCollection);
        notifyDataSetChanged();
    }

    public void clearItems() {
        wallets.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return wallets.size();
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileImg;
        private TextView nameWallet;
        private TextView coinSymbol;
        private TextView priceUsd;
        private TextView priceBtc;


        public WalletViewHolder(View itemView) {
            super(itemView);
            profileImg = itemView.findViewById(R.id.wallet_profile_img_view);
            nameWallet = itemView.findViewById(R.id.wallet_name_item_view);
            coinSymbol = itemView.findViewById(R.id.coin_symbol_item_view);
            priceUsd = itemView.findViewById(R.id.portfolio_value_price_usd);
            priceBtc = itemView.findViewById(R.id.portfolio_value_price_btc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    walletClickListener.onWalletClick(wallets.get(getLayoutPosition()));
                }
            });
        }

        public void bind(Wallet wallet) {
            Picasso.with(itemView.getContext()).load("http://i.imgur.com/DvpvklR.png").into(profileImg);
            profileImg.setVisibility("http://i.imgur.com/DvpvklR.png" != null ? View.VISIBLE : View.GONE);

            nameWallet.setText(wallet.getCoin().getName());
            coinSymbol.setText(wallet.getCoin().getSymbol());
            priceUsd.setText(wallet.getCoin().getPriceUsd());
            priceBtc.setText(wallet.getCoin().getPriceBtc());
        }
    }

    public interface WalletClickListener {
        void onWalletClick(Wallet wallet);
    }
}
