package com.gmail.generationdotz2.c_observer.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Cryptocurrency implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("price_usd")
    @Expose
    private String priceUsd;
    @SerializedName("price_btc")
    @Expose
    private String priceBtc;
    @SerializedName("24h_volume_usd")
    @Expose
    private String _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    @Expose
    private String marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    private String availableSupply;
    @SerializedName("total_supply")
    @Expose
    private String totalSupply;
    @SerializedName("max_supply")
    @Expose
    private Object maxSupply;
    @SerializedName("percent_change_1h")
    @Expose
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private String percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private String percentChange7d;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;

    public Cryptocurrency(String id, String name, String symbol, String rank, String priceUsd,
                          String priceBtc, String _24hVolumeUsd, String marketCapUsd,
                          String availableSupply, String totalSupply, Object maxSupply,
                          String percentChange1h, String percentChange24h, String percentChange7d,
                          String lastUpdated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
        this._24hVolumeUsd = _24hVolumeUsd;
        this.marketCapUsd = marketCapUsd;
        this.availableSupply = availableSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(String priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public void set24hVolumeUsd(String _24hVolumeUsd) {
        this._24hVolumeUsd = _24hVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(String marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        this.availableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Object getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Object maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cryptocurrency that = (Cryptocurrency) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!symbol.equals(that.symbol)) return false;
        if (!rank.equals(that.rank)) return false;
        if (!priceUsd.equals(that.priceUsd)) return false;
        if (!priceBtc.equals(that.priceBtc)) return false;
        if (!_24hVolumeUsd.equals(that._24hVolumeUsd)) return false;
        if (!marketCapUsd.equals(that.marketCapUsd)) return false;
        if (!availableSupply.equals(that.availableSupply)) return false;
        if (!totalSupply.equals(that.totalSupply)) return false;
        if (maxSupply != null ? !maxSupply.equals(that.maxSupply) : that.maxSupply != null)
            return false;
        if (!percentChange1h.equals(that.percentChange1h)) return false;
        if (!percentChange24h.equals(that.percentChange24h)) return false;
        if (!percentChange7d.equals(that.percentChange7d)) return false;
        return lastUpdated.equals(that.lastUpdated);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + symbol.hashCode();
        result = 31 * result + rank.hashCode();
        result = 31 * result + priceUsd.hashCode();
        result = 31 * result + priceBtc.hashCode();
        result = 31 * result + _24hVolumeUsd.hashCode();
        result = 31 * result + marketCapUsd.hashCode();
        result = 31 * result + availableSupply.hashCode();
        result = 31 * result + totalSupply.hashCode();
        result = 31 * result + (maxSupply != null ? maxSupply.hashCode() : 0);
        result = 31 * result + percentChange1h.hashCode();
        result = 31 * result + percentChange24h.hashCode();
        result = 31 * result + percentChange7d.hashCode();
        result = 31 * result + lastUpdated.hashCode();
        return result;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.symbol);
        dest.writeString(this.rank);
        dest.writeString(this.priceUsd);
        dest.writeString(this.priceBtc);
        dest.writeString(this._24hVolumeUsd);
        dest.writeString(this.marketCapUsd);
        dest.writeString(this.availableSupply);
        dest.writeString(this.totalSupply);
        dest.writeString((String) this.maxSupply);
        dest.writeString(this.percentChange1h);
        dest.writeString(this.percentChange24h);
        dest.writeString(this.percentChange7d);
        dest.writeString(this.lastUpdated);
    }

    protected Cryptocurrency(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.symbol = in.readString();
        this.rank = in.readString();
        this.priceUsd = in.readString();
        this.priceBtc = in.readString();
        this._24hVolumeUsd = in.readString();
        this.marketCapUsd = in.readString();
        this.availableSupply = in.readString();
        this.totalSupply = in.readString();
        this.maxSupply = in.readString();
        this.percentChange1h = in.readString();
        this.percentChange24h = in.readString();
        this.percentChange7d = in.readString();
        this.lastUpdated = in.readString();
    }

    public static final Parcelable.Creator<Cryptocurrency> CREATOR = new Parcelable.Creator<Cryptocurrency>() {
        @Override
        public Cryptocurrency createFromParcel(Parcel source) {
            return new Cryptocurrency(source);
        }

        @Override
        public Cryptocurrency[] newArray(int size) {
            return new Cryptocurrency[size];
        }
    };
}
