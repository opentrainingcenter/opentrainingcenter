package ch.opentrainingcenter.client.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ch.opentrainingcenter.transfer.IAthlete;

/**
 * App Context
 * 
 */
public class ApplicationContext {

    private static final ApplicationContext INSTANCE = new ApplicationContext();

    private Date selectedId;

    private IAthlete athlete;

    private final List<Object> selectedItems = new ArrayList<Object>();

    private ApplicationContext() {
    }

    public static ApplicationContext getApplicationContext() {
        return INSTANCE;
    }

    public Date getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(final Date selectedId) {
        this.selectedId = selectedId;
    }

    public IAthlete getAthlete() {
        return athlete;
    }

    public void setAthlete(final IAthlete athlete) {
        this.athlete = athlete;
    }

    public void setSelection(final Object[] items) {
        selectedItems.clear();
        if (items != null) {
            selectedItems.addAll(Arrays.asList(items));
        }
    }

    public List<?> getSelection() {
        return Collections.unmodifiableList(selectedItems);
    }
}