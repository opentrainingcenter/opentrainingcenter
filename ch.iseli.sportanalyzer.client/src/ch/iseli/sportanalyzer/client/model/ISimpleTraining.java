package ch.iseli.sportanalyzer.client.model;

import java.util.Date;

public interface ISimpleTraining {

    double getDistanzInMeter();

    double getDauerInSekunden();

    Date getDatum();

    String getZeit();

    int getAvgHeartRate();

    String getFormattedDate();

    String getLaengeInKilometer();

    String getMaxHeartBeat();

    String getPace();

    String getMaxSpeed();
}