package ch.opentrainingcenter.transfer.impl;

// default package
// Generated 21.10.2011 12:19:41 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import ch.opentrainingcenter.transfer.IAthlete;
import ch.opentrainingcenter.transfer.IImported;
import ch.opentrainingcenter.transfer.ITraining;
import ch.opentrainingcenter.transfer.ITrainingType;
import ch.opentrainingcenter.transfer.IWeather;

/**
 * Imported generated by hbm2java
 */
public class Imported implements java.io.Serializable, IImported {

    private static final long serialVersionUID = 1L;
    private int id;
    private IWeather weather;
    private IAthlete athlete;
    private Date importedDate;
    private String comments;
    private String note;
    private Date activityId;
    private ITraining overview;
    private ITrainingType type;

    public Imported() {
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public IWeather getWeather() {
        return this.weather;
    }

    @Override
    public void setWeather(final IWeather weather) {
        this.weather = weather;
    }

    @Override
    public IAthlete getAthlete() {
        return this.athlete;
    }

    @Override
    public void setAthlete(final IAthlete athlete) {
        this.athlete = athlete;
    }

    @Override
    public Date getImportedDate() {
        return this.importedDate;
    }

    @Override
    public void setImportedDate(final Date importedDate) {
        this.importedDate = importedDate;
    }

    @Override
    public String getComments() {
        return this.comments;
    }

    @Override
    public void setComments(final String comments) {
        this.comments = comments;
    }

    @Override
    public void setActivityId(final Date time) {
        this.activityId = time;
    }

    @Override
    public Date getActivityId() {
        return activityId;
    }

    @Override
    public void setTraining(final ITraining overview) {
        this.overview = overview;
    }

    @Override
    public ITraining getTraining() {
        return overview;
    }

    @Override
    public ITrainingType getTrainingType() {
        return type;
    }

    @Override
    public void setTrainingType(final ITrainingType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Imported [id=" + id + ", weather=" + weather + ", athlete=" + athlete + ", importedDate=" + importedDate + ", comments=" + comments //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                + ", activityId=" + activityId + ", overview=" + overview + " note=" + note + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
