package ch.iseli.sportanalyzer.client;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import ch.iseli.sportanalyzer.client.views.statistics.StatisticView;

public class StatisticPerspectiveFactory implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);

        layout.addStandaloneView(StatisticView.ID, false, IPageLayout.LEFT, 1f, editorArea);
        layout.getViewLayout(StatisticView.ID).setCloseable(false);
    }

}