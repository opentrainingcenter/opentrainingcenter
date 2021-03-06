package ch.opentrainingcenter.client.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewLayout;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.opentrainingcenter.client.views.statistics.StatisticView;

@SuppressWarnings("nls")
public class StatisticPerspectiveFactoryTest {
    private StatisticPerspective perspective;
    private IPageLayout layout;

    @Before
    public void before() {
        layout = Mockito.mock(IPageLayout.class);
    }

    @Test
    public void testInitialLayout() {
        // prepare
        perspective = new StatisticPerspective();
        final IViewLayout viewLayout = Mockito.mock(IViewLayout.class);
        Mockito.when(layout.getViewLayout(StatisticView.ID)).thenReturn(viewLayout);

        Mockito.when(layout.getEditorArea()).thenReturn("editorArea");

        // execute
        perspective.createInitialLayout(layout);
        // assert
        Mockito.verify(layout, Mockito.times(1)).setEditorAreaVisible(false);
        Mockito.verify(layout, Mockito.times(1)).addStandaloneView(StatisticView.ID, false, IPageLayout.LEFT, 1f, "editorArea");

        Mockito.verify(viewLayout, Mockito.times(1)).setCloseable(false);

        Mockito.verify(layout, Mockito.times(1)).addPerspectiveShortcut(MainPerspective.ID);
        Mockito.verify(layout, Mockito.times(1)).addPerspectiveShortcut(TablePerspective.ID);
        Mockito.verify(layout, Mockito.times(1)).addPerspectiveShortcut(StatisticPerspective.ID);
        Mockito.verify(layout, Mockito.times(1)).addPerspectiveShortcut(EinstellungenPerspective.ID);
    }
}
