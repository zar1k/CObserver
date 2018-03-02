package com.gmail.generationdotz2.c_observer.model;

public class Alert {
    private long above;
    private long current;
    private long below;

    public long getAbove() {
        return above;
    }

    public void setAbove(long above) {
        this.above = above;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getBelow() {
        return below;
    }

    public void setBelow(long below) {
        this.below = below;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Alert alert = (Alert) obj;

        if (above != alert.above) return false;
        if (current != alert.current) return false;
        return below == alert.below;
    }

    @Override
    public int hashCode() {
        int result = (int) (above ^ (above >>> 32));
        result = 31 * result + (int) (current ^ (current >>> 32));
        result = 31 * result + (int) (below ^ (below >>> 32));
        return result;
    }
}
