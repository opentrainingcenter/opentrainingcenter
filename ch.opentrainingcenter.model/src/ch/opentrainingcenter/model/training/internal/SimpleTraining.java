package ch.opentrainingcenter.model.training.internal;

import java.util.Date;

import ch.opentrainingcenter.core.helper.DistanceHelper;
import ch.opentrainingcenter.core.helper.RunType;
import ch.opentrainingcenter.core.helper.TimeHelper;
import ch.opentrainingcenter.model.strecke.StreckeModel;
import ch.opentrainingcenter.model.training.ISimpleTraining;
import ch.opentrainingcenter.model.training.Wetter;

public class SimpleTraining implements ISimpleTraining {
    private final double distanzInMeter;
    private final double dauerInSekunden;
    private final Date datum;
    private final int avgHeartRate;
    private final int maxHeartRate;
    private final String readableZeit;
    private final String laengeInKilometer;
    private final String pace;
    private final String speed;
    private String note;
    private RunType type;
    private Wetter wetter;
    private StreckeModel strecke;
    private int upMeter;
    private int downMeter;

    public SimpleTraining(final double distanzInMeter, final double dauerInSekunden, final Date datum, final int avgHeartRate, final int maxHeartRate,
            final double speed, final RunType type, final String note) {
        this.distanzInMeter = distanzInMeter;
        this.avgHeartRate = avgHeartRate;
        this.maxHeartRate = maxHeartRate;
        this.type = type;
        this.note = note;
        laengeInKilometer = DistanceHelper.roundDistanceFromMeterToKm(distanzInMeter);
        this.dauerInSekunden = dauerInSekunden;
        readableZeit = TimeHelper.convertSecondsToHumanReadableZeit(dauerInSekunden);
        this.datum = datum;
        pace = DistanceHelper.calculatePace(distanzInMeter, dauerInSekunden);
        this.speed = DistanceHelper.calculatePace(speed);
        wetter = Wetter.UNBEKANNT;
    }

    @Override
    public double getDistanzInMeter() {
        return distanzInMeter;
    }

    @Override
    public double getDauerInSekunden() {
        return dauerInSekunden;
    }

    @Override
    public Date getDatum() {
        return datum;
    }

    @Override
    public int getAvgHeartRate() {
        return avgHeartRate;
    }

    @Override
    public String getZeit() {
        return readableZeit;
    }

    @Override
    public String getFormattedDate() {
        return TimeHelper.convertDateToString(datum, false);
    }

    @Override
    public String getLaengeInKilometer() {
        return laengeInKilometer;
    }

    @Override
    public String getMaxHeartBeat() {
        if (maxHeartRate > 0) {
            return String.valueOf(maxHeartRate);
        } else {
            return "-"; //$NON-NLS-1$
        }
    }

    @Override
    public String getPace() {
        return pace;
    }

    @Override
    public String getMaxSpeed() {
        return speed;
    }

    @Override
    public RunType getType() {
        return type;
    }

    @Override
    public void setType(final RunType type) {
        this.type = type;
    }

    @Override
    public String getNote() {
        return note;
    }

    @Override
    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public Wetter getWetter() {
        return wetter;
    }

    @Override
    public void setWetter(final Wetter wetter) {
        this.wetter = wetter;
    }

    @Override
    public StreckeModel getStrecke() {
        return strecke;
    }

    @Override
    public void setStrecke(final StreckeModel strecke) {
        this.strecke = strecke;
    }

    @Override
    public int getUpMeter() {
        return upMeter;
    }

    @Override
    public void setUpMeter(final int upMeter) {
        this.upMeter = upMeter;
    }

    @Override
    public int getDownMeter() {
        return downMeter;
    }

    @Override
    public void setDownMeter(final int downMeter) {
        this.downMeter = downMeter;
    }

    @SuppressWarnings("nls")
    @Override
    public String toString() {
        return "SimpleTraining [distanzInMeter=" + distanzInMeter + ", dauerInSekunden=" + dauerInSekunden + ", datum=" + datum + ", avgHeartRate="
                + avgHeartRate + ", maxHeartRate=" + maxHeartRate + ", readableZeit=" + readableZeit + ", laengeInKilometer=" + laengeInKilometer + ", pace="
                + pace + ", speed=" + speed + ", note=" + note + ", type=" + type + ", wetter=" + wetter + ", strecke=" + strecke + ", upMeter=" + upMeter
                + ", downMeter=" + downMeter + "]";
    }

}
