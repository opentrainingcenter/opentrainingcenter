package ch.iseli.sportanalyzer.client.views.overview;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import ch.iseli.sportanalyzer.client.cache.TrainingCenterDataCache;
import ch.iseli.sportanalyzer.client.cache.TrainingCenterDatabaseTParent;

public class SingleActivityViewPart extends ViewPart {

    public static final String ID = "ch.iseli.sportanalyzer.client.views.singlerun";
    private final TrainingCenterDataCache cache = TrainingCenterDataCache.getInstance();
    private TrainingCenterDatabaseTParent selected;

    public SingleActivityViewPart() {
    }

    @Override
    public void createPartControl(Composite parent) {
        selected = cache.getSelected();
        setPartName(selected.getTrainingCenterDatabase().getActivities().getActivity().get(0).getId().toString());

    }

    @Override
    public void setFocus() {
    }

}
