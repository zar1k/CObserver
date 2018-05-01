package com.gmail.generationdotz2.c_observer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.generationdotz2.c_observer.R;
import com.gmail.generationdotz2.c_observer.model.Cryptocurrency;
import com.gmail.generationdotz2.c_observer.util.PrettyFormat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Унаследовали наш адаптер от RecyclerView.Adapter
// Здесь же указали наш собственный ViewHolder, который предоставит нам доступ к View-компонентам.
public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> implements Filterable {
    private OnCoinClickListener onCoinClickListener;    // Наблюдателя за кликом по элементу coin.
    private List<Cryptocurrency> coinList;              // Список криптовалют.
    private List<Cryptocurrency> coinListFiltered;
    private Context context;

    public CoinAdapter(Context context, List<Cryptocurrency> coinList, OnCoinClickListener onCoinClickListener) {
        this.context = context;
        this.coinList = coinList;
        this.coinListFiltered = coinList;
        this.onCoinClickListener = onCoinClickListener;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_item_view, parent, false);
        return new CoinViewHolder(view);
    }

    // Этот метод отвечает за связь java объекта и View.
    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        final Cryptocurrency coin = coinListFiltered.get(position);
        holder.bind(coin);
    }

    public void setItems(Collection<Cryptocurrency> coins) {
        coinList.addAll(coins);
        notifyDataSetChanged(); // Для перерисовки єлементов после изменения списка.
    }

    public void clearItems() {
        coinList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return coinListFiltered.size();
    }

    // Предоставляет прямую ссылку на каждый View-компонент
    // Используется для кэширования View-компонентов и последующего быстрого доступа к ним.
    public class CoinViewHolder extends RecyclerView.ViewHolder {
        // Ваш ViewHolder должен содержать переменные для всех
        // View-компонентов, которым вы хотите задавать какие-либо свойства
        // в процессе работы пользователя со списком.
        private ImageView coinProfileImgView;
        private TextView coinNameItemView;
        private TextView coinSymbolItemView;
        private TextView coinPriceUsdItemView;
        private TextView coinPercentChange24hItemView;

        public CoinViewHolder(View itemView) {
            super(itemView);
            coinProfileImgView = itemView.findViewById(R.id.coin_profile_img_view);
            coinNameItemView = itemView.findViewById(R.id.coin_name_item_view);
            coinSymbolItemView = itemView.findViewById(R.id.coin_symbol_item_view);
            coinPriceUsdItemView = itemView.findViewById(R.id.coin_price_usd_item_view);
            coinPercentChange24hItemView = itemView.findViewById(R.id.coin_percent_change_24h_item_view);

            // Уведомление наблюдателя о событии в CoinAdapter.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCoinClickListener.onCoinClick(coinListFiltered.get(getLayoutPosition()));
                }
            });
        }

        public void bind(Cryptocurrency coin) {
            Picasso.with(itemView.getContext()).load("http://i.imgur.com/DvpvklR.png").into(coinProfileImgView);
            coinProfileImgView.setVisibility("http://i.imgur.com/DvpvklR.png" != null ? View.VISIBLE : View.GONE);

            coinNameItemView.setText(coin.getName());
            coinSymbolItemView.setText(coin.getSymbol());
            coinPriceUsdItemView.setText(coin.getPriceUsd());
            coinPercentChange24hItemView.setText(PrettyFormat.addPercentSign(coin.getPercentChange24h()));
            coinPercentChange24hItemView.setTextColor(PrettyFormat.changeColor(context, coin.getPercentChange24h()));
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    coinListFiltered = coinList;
                } else {
                    List<Cryptocurrency> filteredList = new ArrayList<>();
                    for (Cryptocurrency coin : coinList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name.
                        if (coin.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(coin);
                        }
                    }
                    coinListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = coinListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                coinListFiltered = (ArrayList<Cryptocurrency>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnCoinClickListener {
        void onCoinClick(Cryptocurrency coin);
    }
}