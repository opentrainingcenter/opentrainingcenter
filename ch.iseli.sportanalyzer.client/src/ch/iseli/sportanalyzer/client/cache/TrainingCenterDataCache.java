package ch.iseli.sportanalyzer.client.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.ListenerList;

import ch.iseli.sportanalyzer.client.model.SimpleTraining;
import ch.iseli.sportanalyzer.client.model.TrainingOverview;
import ch.opentrainingcenter.transfer.IAthlete;

public class TrainingCenterDataCache {

    private ListenerList listeners;

    private IAthlete selectedProfile;

    private static TrainingCenterDataCache INSTANCE = null;

    private final Map<Integer, TrainingCenterRecord> list = new TreeMap<Integer, TrainingCenterRecord>();

    private static TrainingCenterRecord selected;

    private Object[] selectedItems;

    private TrainingCenterDataCache() {
    }

    public static TrainingCenterDataCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TrainingCenterDataCache();
        }
        return INSTANCE;
    }

    public void setSelectedRun(final TrainingCenterRecord selected) {
        TrainingCenterDataCache.selected = selected;
    }

    public void setSelectedProfile(final IAthlete athlete) {
        this.selectedProfile = athlete;
        resetCache();
        loadCacheForProfile();
    }

    @SuppressWarnings("unchecked")
    private void resetCache() {
        list.clear();
        selected = null;
        selectedItems = null;
        fireRecordAdded(Collections.EMPTY_LIST);
    }

    private void loadCacheForProfile() {

    }

    public TrainingCenterRecord getSelected() {
        return selected;
    }

    /**
     * @return eine Übersicht auf das selektierte Training.
     */
    public TrainingOverview getSelectedOverview() {
        return new TrainingOverview(selected);
    }

    public Collection<TrainingCenterRecord> getAllRuns() {
        return list.values();
    }

    public void addAll(final Map<Integer, TrainingCenterRecord> records) {
        for (final Map.Entry<Integer, TrainingCenterRecord> record : records.entrySet()) {
            list.put(record.getKey(), record.getValue());
        }
        fireRecordAdded(records.values());
    }

    private void fireRecordAdded(final Collection<TrainingCenterRecord> collection) {
        if (listeners == null)
            return;
        final Object[] rls = listeners.getListeners();
        for (int i = 0; i < rls.length; i++) {
            final IRecordListener listener = (IRecordListener) rls[i];
            listener.recordChanged(collection);
        }

    }

    public void addListener(final IRecordListener listener) {
        if (listeners == null) {
            listeners = new ListenerList();
        }
        listeners.add(listener);
    }

    public void removeListener(final IRecordListener listener) {
        if (listeners != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                listeners = null;
            }
        }

    }

    public void remove(final Integer id) {
        list.remove(id);
        fireRecordAdded(null);
    }

    public List<SimpleTraining> getAllSimpleTrainings() {
        final Collection<TrainingCenterRecord> values = list.values();
        final List<SimpleTraining> result = new ArrayList<SimpleTraining>();
        for (final TrainingCenterRecord t : values) {
            final TrainingOverview over = new TrainingOverview(t);
            result.add(over.getSimpleTraining());
        }
        return result;
    }

    public void setSelection(final Object[] selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<?> getSelection() {
        return Collections.unmodifiableList(Arrays.asList(selectedItems));
    }

}
