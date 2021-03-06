package ch.opentrainingcenter.core.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.ListenerList;

public abstract class AbstractCache<K, V> implements ICache<K, V> {

    private static final Logger LOG = Logger.getLogger(AbstractCache.class);
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<K, V>();
    private ListenerList listeners;

    public abstract K getKey(V value);

    @Override
    public void addAll(final List<V> values) {
        for (final V v : values) {
            add(v);
        }
    }

    @Override
    public void add(final V value) {
        cache.put(getKey(value), value);
        LOG.info("Cache Size: (Element added)" + cache.size()); //$NON-NLS-1$
        final List<V> v = new ArrayList<V>();
        v.add(value);
        fireRecordAdded(v);
    }

    @Override
    public V get(final K key) {
        return cache.get(key);
    }

    @Override
    public void remove(final K key) {
        final V value = cache.remove(key);
        LOG.info("Cache Size: (Element removed)" + cache.size()); //$NON-NLS-1$
        final List<V> values = new ArrayList<V>();
        if (value != null) {
            values.add(value);
        }
        fireRecordDeleted(values);
    }

    @Override
    public void remove(final List<K> keys) {
        for (final K key : keys) {
            remove(key);
        }
    }

    @Override
    public boolean contains(final K key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public List<V> getAll() {
        return new ArrayList<V>(cache.values());
    }

    public List<V> getSortedElements(final Comparator<V> comparator) {
        final List<V> values = getAll();
        Collections.sort(new ArrayList<V>(values), comparator);
        return values;
    }

    @Override
    public void notifyListeners() {
        if (listeners == null) {
            return;
        }
        final Object[] rls = listeners.getListeners();
        for (int i = 0; i < rls.length; i++) {
            @SuppressWarnings("unchecked")
            final IRecordListener<V> listener = (IRecordListener<V>) rls[i];
            listener.recordChanged(null);
        }
    }

    @Override
    public void addListener(final IRecordListener<V> listener) {
        if (listeners == null) {
            listeners = new ListenerList();
        }
        listeners.add(listener);
    }

    @Override
    public void removeListener(final IRecordListener<V> listener) {
        if (listeners != null && listener != null) {
            listeners.remove(listener);
            if (listeners.isEmpty()) {
                listeners = null;
            }
        }
    }

    private void fireRecordAdded(final Collection<V> values) {
        if (listeners == null) {
            return;
        }
        final Object[] rls = listeners.getListeners();
        for (int i = 0; i < rls.length; i++) {
            @SuppressWarnings("unchecked")
            final IRecordListener<V> listener = (IRecordListener<V>) rls[i];
            listener.recordChanged(values);
        }

    }

    private void fireRecordDeleted(final List<V> values) {
        if (listeners == null) {
            return;
        }
        final Object[] rls = listeners.getListeners();
        for (int i = 0; i < rls.length; i++) {
            @SuppressWarnings("unchecked")
            final IRecordListener<V> listener = (IRecordListener<V>) rls[i];
            listener.deleteRecord(values);
        }
    }

    public void resetCache() {
        cache.clear();
    }
}
