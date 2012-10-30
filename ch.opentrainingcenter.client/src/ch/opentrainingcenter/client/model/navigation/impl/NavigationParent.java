package ch.opentrainingcenter.client.model.navigation.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.opentrainingcenter.client.Messages;
import ch.opentrainingcenter.client.model.KalenderWoche;
import ch.opentrainingcenter.client.model.navigation.INavigationItem;
import ch.opentrainingcenter.client.model.navigation.INavigationParent;

public class NavigationParent implements INavigationParent {

    List<INavigationItem> items = new ArrayList<INavigationItem>();
    private KalenderWoche kw = null;

    public NavigationParent() {

    }

    @Override
    public String getName() {
        final StringBuffer result = new StringBuffer(Messages.NavigationParent_0);
        result.append(kw.getKw());
        return result.toString();
    }

    @Override
    public List<INavigationItem> getChilds() {
        Collections.sort(items);
        return items;
    }

    @Override
    public void add(final INavigationItem item) {
        items.add(item);
        if (kw == null) {
            kw = new KalenderWoche(item.getDate());
        }
    }

    @Override
    public void remove(final INavigationItem item) {
        items.remove(item);
    }

    @Override
    public KalenderWoche getKalenderWoche() {
        return kw;
    }
}