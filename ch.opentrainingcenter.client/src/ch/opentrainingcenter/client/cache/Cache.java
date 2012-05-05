package ch.opentrainingcenter.client.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ch.opentrainingcenter.client.model.ISimpleTraining;
import ch.opentrainingcenter.client.model.RunType;
import ch.opentrainingcenter.tcx.ActivityT;
import ch.opentrainingcenter.transfer.IAthlete;
import ch.opentrainingcenter.transfer.IImported;

public interface Cache {

    /**
     * Methode um importierte Runs im cache abzulegen.
     * 
     * @param activities
     *            eine Liste von Aktivitäten.
     */
    void add(final ActivityT activity);

    /**
     * Methode um importierte Runs im cache abzulegen.
     * 
     * @param activities
     *            eine Liste von Aktivitäten.
     */
    void addAll(final List<ActivityT> activities);

    /**
     * @return eine nach Datum sortierte Liste von {@link IImported}
     */
    Collection<IImported> getAllImportedRecords();

    void setSelectedRun(final IImported selected);

    void setSelectedProfile(final IAthlete athlete);

    /**
     * Ist noch keiner selektiert, wird der neuste, also derjenige mit dem
     * jüngsten datum, zurückgegeben. Dieser wird dann auch als selektierten
     * Lauf gesetzt.
     * 
     * @return den selektierten record oder den neusten record.
     */
    IImported getSelected();

    /**
     * @return eine Übersicht auf das selektierte Training.
     */
    ISimpleTraining getSelectedOverview();

    void remove(final List<Date> deletedIds);

    void changeType(final List<IImported> changedRecords, final RunType type);

    void update();

    void addListener(final IRecordListener listener);

    void removeListener(final IRecordListener listener);

    List<ISimpleTraining> getAllSimpleTrainings();

    void setSelection(final Object[] selectedItems);

    List<?> getSelection();

    IAthlete getSelectedProfile();

    void setCacheLoaded(final boolean loaded);

    boolean isCacheLoaded();

    void addAllImported(final List<IImported> records);

    boolean contains(final Date activityId);

    ActivityT get(final Date activityId);
}