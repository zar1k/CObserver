package com.gmail.generationdotz2.c_observer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Wallet implements Parcelable {
    private Cryptocurrency coin;
    private Alert alert;
    private float totalValue = 0.0f;

    public Wallet() {
    }

    public Wallet(Cryptocurrency coin, Alert alert, float totalValue) {
        this.coin = coin;
        this.alert = alert;
        this.totalValue = totalValue;
    }

    protected Wallet(Parcel in) {
        coin = in.readParcelable(Cryptocurrency.class.getClassLoader());
        alert = in.readParcelable(Alert.class.getClassLoader());
        totalValue = in.readFloat();
    }

    public Cryptocurrency getCoin() {
        return coin;
    }

    public void setCoin(Cryptocurrency coin) {
        this.coin = coin;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Wallet wallet = (Wallet) obj;

        if (Float.compare(wallet.totalValue, totalValue) != 0) return false;
        if (!coin.equals(wallet.coin)) return false;
        return alert.equals(wallet.alert);
    }

    @Override
    public int hashCode() {
        int result = coin.hashCode();
        result = 31 * result + alert.hashCode();
        result = 31 * result + (totalValue != +0.0f ? Float.floatToIntBits(totalValue) : 0);
        return result;
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel in) {
            return new Wallet(in);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(coin, flags);
        dest.writeParcelable(alert, flags);
        dest.writeFloat(totalValue);
    }
}