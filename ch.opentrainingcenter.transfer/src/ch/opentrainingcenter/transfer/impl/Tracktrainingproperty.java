package ch.opentrainingcenter.transfer.impl;

import ch.opentrainingcenter.transfer.IStreckenPunkt;
import ch.opentrainingcenter.transfer.ITrackPointProperty;

// Generated 04.04.2013 20:38:06 by Hibernate Tools 3.4.0.CR1

/**
 * Tracktrainingproperty generated by hbm2java
 */
public class Tracktrainingproperty implements java.io.Serializable, ITrackPointProperty {

    private static final long serialVersionUID = 1L;
    private int id;
    private double distance;
    private int heartbeat;
    private int altitude;
    private long zeit;

    private IStreckenPunkt streckenPunkt;
    private int lap;

    public Tracktrainingproperty() {
    }

    public Tracktrainingproperty(final double distance, final int heartbeat, final int altitude, final long zeit, final int lap,
            final IStreckenPunkt streckenPunkt) {
        this.distance = distance;
        this.heartbeat = heartbeat;
        this.altitude = altitude;
        this.zeit = zeit;
        this.lap = lap;
        this.streckenPunkt = streckenPunkt;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(final double distance) {
        this.distance = distance;
    }

    @Override
    public int getHeartBeat() {
        return this.heartbeat;
    }

    @Override
    public void setHeartBeat(final int heartbeat) {
        this.heartbeat = heartbeat;
    }

    @Override
    public int getAltitude() {
        return this.altitude;
    }

    @Override
    public void setAltitude(final int altitude) {
        this.altitude = altitude;
    }

    @Override
    public long getZeit() {
        return this.zeit;
    }

    @Override
    public void setZeit(final long zeit) {
        this.zeit = zeit;
    }

    @Override
    public IStreckenPunkt getStreckenPunkt() {
        return streckenPunkt;
    }

    @Override
    public void setStreckenPunkt(final IStreckenPunkt streckenPunkt) {
        this.streckenPunkt = streckenPunkt;
    }

    @Override
    public int getLap() {
        return lap;
    }

    @Override
    public void setLap(final int lap) {
        this.lap = lap;
    }

    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return "Tracktrainingproperty [distance=" + distance + ", heartbeat=" + heartbeat + ", altitude=" + altitude + ", streckenPunkt=" + streckenPunkt
                + ", lap=" + lap + "]";
    }

}
