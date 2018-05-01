package com.gmail.generationdotz2.c_observer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Alert implements Parcelable {
    private float above = 0.0f;
    private float current = 0.0f;
    private float below = 0.0f;

    public Alert() {
    }

    public Alert(Parcel in) {
        above = in.readFloat();
        current = in.readFloat();
        below = in.readFloat();
    }

    public float getAbove() {
        return above;
    }

    public void setAbove(float above) {
        this.above = above;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getBelow() {
        return below;
    }

    public void setBelow(float below) {
        this.below = below;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;

        Alert alert = (Alert) o;

        if (Float.compare(alert.above, above) != 0) return false;
        if (Float.compare(alert.current, current) != 0) return false;
        return Float.compare(alert.below, below) == 0;
    }

    @Override
    public int hashCode() {
        int result = (above != +0.0f ? Float.floatToIntBits(above) : 0);
        result = 31 * result + (current != +0.0f ? Float.floatToIntBits(current) : 0);
        result = 31 * result + (below != +0.0f ? Float.floatToIntBits(below) : 0);
        return result;
    }

    public static final Creator<Alert> CREATOR = new Creator<Alert>() {
        @Override
        public Alert createFromParcel(Parcel in) {
            return new Alert(in);
        }

        @Override
        public Alert[] newArray(int size) {
            return new Alert[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(above);
        dest.writeFloat(current);
        dest.writeFloat(below);
    }
}