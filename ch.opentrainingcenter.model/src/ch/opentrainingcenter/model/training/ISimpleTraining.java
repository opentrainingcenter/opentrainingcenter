package ch.opentrainingcenter.model.training;

import java.util.Date;

import ch.opentrainingcenter.model.RunType;

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

    RunType getType();

    void setType(RunType type);

    String getNote();

    void setNote(String note);

    Wetter getWetter();

    void setWetter(Wetter type);
}