package ch.opentrainingcenter.client.views.overview;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.JFreeChart;
import org.jfree.experimental.chart.swt.ChartComposite;

import ch.opentrainingcenter.client.Activator;
import ch.opentrainingcenter.client.Messages;
import ch.opentrainingcenter.client.cache.Cache;
import ch.opentrainingcenter.client.cache.IRecordListener;
import ch.opentrainingcenter.client.cache.impl.TrainingCenterDataCache;
import ch.opentrainingcenter.client.charts.ChartCreator;
import ch.opentrainingcenter.client.charts.DataSetCreator;
import ch.opentrainingcenter.client.charts.internal.ChartCreatorImpl;
import ch.opentrainingcenter.client.charts.internal.DataSetCreatorImpl;
import ch.opentrainingcenter.client.model.ISimpleTraining;
import ch.opentrainingcenter.client.model.Units;
import ch.opentrainingcenter.tcx.ActivityT;

public class SingleActivityViewPart extends ViewPart {

    public static final String ID = "ch.opentrainingcenter.client.views.singlerun"; //$NON-NLS-1$
    private static final Logger LOGGER = Logger.getLogger(SingleActivityViewPart.class);
    private final Cache cache = TrainingCenterDataCache.getInstance();
    private final ISimpleTraining simpleTraining;
    private final ActivityT selected;
    private FormToolkit toolkit;
    private ScrolledForm form;
    private TableWrapData td;
    private final DataSetCreator dataSetCreator;
    private final ChartCreator chartCreator;
    private IRecordListener listener;

    public SingleActivityViewPart() {
        simpleTraining = cache.getSelectedOverview();
        selected = cache.get(simpleTraining.getDatum());
        dataSetCreator = new DataSetCreatorImpl(selected);
        chartCreator = new ChartCreatorImpl(cache, Activator.getDefault().getPreferenceStore());
        setPartName(simpleTraining.getFormattedDate());
    }

    @Override
    public void createPartControl(final Composite parent) {
        LOGGER.debug("create single activity view"); //$NON-NLS-1$
        toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createScrolledForm(parent);
        form.setText(Messages.SingleActivityViewPart_0 + simpleTraining.getDatum());
        final Composite body = form.getBody();

        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        body.setLayout(layout);

        addOverviewSection(body);
        addNoteSection(body);

        addMapSection(body);
        addHeartSection(body);
        addSpeedSection(body);
        addAltitudeSection(body);
    }

    @Override
    public void dispose() {
        cache.removeListener(listener);
        super.dispose();
        toolkit.dispose();
    }

    @Override
    public void setFocus() {
        form.setFocus();
    }

    private void addOverviewSection(final Composite body) {
        final Section overviewSection = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
                | Section.EXPANDED);
        overviewSection.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 1;
        overviewSection.setLayoutData(td);
        overviewSection.setText(Messages.SingleActivityViewPart_1);
        overviewSection.setDescription(Messages.SingleActivityViewPart_2);

        final Composite overViewComposite = toolkit.createComposite(overviewSection);
        final GridLayout layoutClient = new GridLayout(3, false);
        overViewComposite.setLayout(layoutClient);

        addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_3, simpleTraining.getZeit(), Units.HOUR_MINUTE_SEC);
        addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_4, simpleTraining.getLaengeInKilometer(), Units.KM);
        final int avgHeartRate = simpleTraining.getAvgHeartRate();
        if (avgHeartRate > 0) {
            addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_5, String.valueOf(avgHeartRate), Units.BEATS_PER_MINUTE);
        } else {
            addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_5, "-", Units.BEATS_PER_MINUTE); //$NON-NLS-1$
        }
        addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_6, simpleTraining.getMaxHeartBeat(), Units.BEATS_PER_MINUTE);
        addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_7, simpleTraining.getPace(), Units.PACE);
        addLabelAndValue(overViewComposite, Messages.SingleActivityViewPart_8, simpleTraining.getMaxSpeed(), Units.PACE);
        overviewSection.setClient(overViewComposite);
    }

    private void addNoteSection(final Composite body) {
        final Section section = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
        section.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        //
        // final GridData sectionLayoutData = new GridData(SWT.FILL, SWT.FILL,
        // true, true);
        // sectionLayoutData.horizontalSpan = 2;
        // sectionLayoutData.verticalIndent = 6;
        // section.setLayoutData(sectionLayoutData);
        section.setRedraw(true);
        section.setText("Bemerkungen");
        section.setDescription("Beschreibung von Zustand der Gesundheit, wie der Lauf erlebt wurde. Usw..");

        // td = new TableWrapData(TableWrapData.FILL_GRAB);
        // td.colspan = 1;
        // td.grabVertical = true;
        // overviewSection.setLayoutData(td);
        // overviewSection.setText("Bemerkungen");

        // final Color bodyColor =
        // Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
        // body.setBackground(bodyColor);
        //
        // final Color section =
        // Display.getDefault().getSystemColor(SWT.COLOR_RED);
        // overviewSection.setBackground(section);
        // final Color background =
        // Display.getDefault().getSystemColor(SWT.COLOR_GREEN);

        final Composite sectionClient = toolkit.createComposite(section);
        final GridLayout sectionClientLayout = new GridLayout(2, false);
        sectionClientLayout.verticalSpacing = 2;
        sectionClientLayout.horizontalSpacing = 10;
        sectionClient.setLayout(sectionClientLayout);
        section.setClient(sectionClient);

        // GridLayoutFactory.swtDefaults().numColumns(1).margins(10,
        // 10).applyTo(overViewComposite);

        final Label label = toolkit.createLabel(sectionClient, "Description : ");

        final Text note = toolkit.createText(sectionClient, "", SWT.V_SCROLL | SWT.MULTI);
        toolkit.adapt(note, true, true);
        note.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        final String notiz = simpleTraining.getNote();
        if (notiz != null) {
            note.setText(notiz);
        }

        listener = new IRecordListener() {

            @Override
            public void recordChanged(final Collection<ActivityT> entry) {
                final ActivityT act = entry.iterator().next();
                note.setText(act.getNotes());
                section.update();
            }

            @Override
            public void deleteRecord(final Collection<ActivityT> entry) {
            }
        };
        cache.addListener(listener);
        // overviewSection.setClient(overViewComposite);
    }

    private void addMapSection(final Composite body) {
        final Section mapSection = toolkit
                .createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
        mapSection.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        mapSection.setExpanded(false);

        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 2;
        td.grabHorizontal = true;
        td.grabVertical = true;

        mapSection.setLayoutData(td);
        mapSection.setText(Messages.SingleActivityViewPart_16);
        mapSection.setDescription(Messages.SingleActivityViewPart_17);

        final Composite client = toolkit.createComposite(mapSection);
        // client.setBackground(getViewSite().getWorkbenchWindow().getShell().getDisplay().getSystemColor(SWT.COLOR_CYAN));
        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 1;
        layout.topMargin = -60;
        layout.bottomMargin = 5;
        client.setLayout(layout);

        final String convertTrackpoints = MapConverter.convertTrackpoints(selected);
        final String firstPointToPan = MapConverter.getFirstPointToPan(convertTrackpoints);
        final MapViewer mapViewer = new MapViewer(client, SWT.NONE, convertTrackpoints, firstPointToPan);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.heightHint = 550;
        td.grabHorizontal = true;
        mapViewer.getComposite().setLayoutData(td);

        mapSection.setClient(client);
    }

    /**
     * Herz frequenz
     */
    private void addHeartSection(final Composite body) {
        final Section heartSection = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
                | Section.EXPANDED);
        heartSection.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        heartSection.setExpanded(true);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 2;
        td.grabHorizontal = true;
        td.grabVertical = true;
        heartSection.setLayoutData(td);
        heartSection.setText(Messages.SingleActivityViewPart_9);
        heartSection.setDescription(Messages.SingleActivityViewPart_10);
        //
        final Composite client = toolkit.createComposite(heartSection);

        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;

        client.setLayout(layout);

        final Label dauerLabel = toolkit.createLabel(client, "");

        final JFreeChart chart = chartCreator.createChart(dataSetCreator.createDatasetHeart(), ChartType.HEART_DISTANCE);
        final ChartComposite chartComposite = new ChartComposite(client, SWT.NONE, chart, true);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.heightHint = 400;
        chartComposite.setLayoutData(td);

        heartSection.setClient(client);
    }

    private void addSpeedSection(final Composite body) {
        final Section speedSection = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE
                | Section.EXPANDED);
        speedSection.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        speedSection.setExpanded(false);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 2;
        td.grabHorizontal = true;
        td.grabVertical = true;
        speedSection.setLayoutData(td);
        speedSection.setText(Messages.SingleActivityViewPart_11);
        speedSection.setDescription(Messages.SingleActivityViewPart_12);
        //
        final Composite client = toolkit.createComposite(speedSection);

        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;

        client.setLayout(layout);

        final Label dauerLabel = toolkit.createLabel(client, "");

        final JFreeChart chart = chartCreator.createChart(dataSetCreator.createDatasetSpeed(), ChartType.SPEED_DISTANCE);
        final ChartComposite chartComposite = new ChartComposite(client, SWT.NONE, chart, true);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.heightHint = 400;
        chartComposite.setLayoutData(td);

        speedSection.setClient(client);
    }

    /**
     * Verlauf der höhe
     */
    private void addAltitudeSection(final Composite body) {
        final Section altitude = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
        altitude.setExpanded(false);
        altitude.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 2;
        td.grabHorizontal = true;
        td.grabVertical = true;
        altitude.setLayoutData(td);
        altitude.setText(Messages.SingleActivityViewPart_13);
        altitude.setDescription(Messages.SingleActivityViewPart_14);

        final Composite client = toolkit.createComposite(altitude);

        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;
        client.setLayout(layout);

        final Label dauerLabel = toolkit.createLabel(client, Messages.SingleActivityViewPart_15);

        final JFreeChart chart = chartCreator.createChart(dataSetCreator.createDatasetAltitude(), ChartType.ALTITUDE_DISTANCE);
        final ChartComposite chartComposite = new ChartComposite(client, SWT.NONE, chart, true);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.heightHint = 400;
        chartComposite.setLayoutData(td);

        altitude.setClient(client);
    }

    private Label addLabelAndValue(final Composite parent, final String label, final String value, final Units unit) {
        // Label
        final Label dauerLabel = toolkit.createLabel(parent, label + ": "); //$NON-NLS-1$
        GridData gd = new GridData();
        gd.verticalIndent = 4;
        dauerLabel.setLayoutData(gd);

        // value
        final Label dauer = toolkit.createLabel(parent, value);
        gd = new GridData();
        gd.horizontalAlignment = SWT.RIGHT;
        gd.horizontalIndent = 10;
        gd.verticalIndent = 4;
        dauer.setLayoutData(gd);

        // einheit
        final Label einheit = toolkit.createLabel(parent, unit.getName());
        gd = new GridData();
        gd.horizontalAlignment = SWT.LEFT;
        gd.horizontalIndent = 10;
        gd.verticalIndent = 4;
        einheit.setLayoutData(gd);

        return dauer;
    }

}
