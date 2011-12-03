package ch.iseli.sportanalyzer.client.views.ahtlete;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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

import ch.iseli.sportanalyzer.client.Activator;
import ch.iseli.sportanalyzer.client.PreferenceConstants;
import ch.iseli.sportanalyzer.client.model.sportler.Sportler;
import ch.iseli.sportanalyzer.db.DatabaseAccessFactory;
import ch.opentrainingcenter.transfer.impl.Athlete;

public class CreateAthleteView extends ViewPart {
    public static final String ID = "ch.iseli.sportanalyzer.client.athlete.createathlete";
    private static final Logger logger = Logger.getLogger(CreateAthleteView.class);
    public static final String IMAGE = "icons/create.png";
    private final Sportler sportler = new Sportler();
    private Text nameTf;
    private Text ageText;
    private Combo genderCombo;
    private Text pulseText;
    private FormToolkit toolkit;
    private ScrolledForm form;
    private TableWrapData td;
    private final String athleteId;
    private String titleOfComposite;
    private Combo user;

    public CreateAthleteView() {
        athleteId = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.ATHLETE_NAME);
    }

    @Override
    public void createPartControl(final Composite parent) {

        logger.debug("create single activity view");
        toolkit = new FormToolkit(parent.getDisplay());
        form = toolkit.createScrolledForm(parent);
        // form.setSize(1000, 2000);
        // gridlayout definieren

        final TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 1;
        layout.makeColumnsEqualWidth = false;

        final Composite body = form.getBody();
        body.setLayout(layout);

        td = new TableWrapData(TableWrapData.FILL_GRAB);
        body.setLayoutData(td);
        form.setText("Verwalten der Benutzer / Profile");

        createSelectSportler(body);
        createAddSportlerMask(body);
    }

    private void createSelectSportler(final Composite body) {
        final Section selectSportler = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
        selectSportler.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        selectSportler.setExpanded(false);
        td = new TableWrapData();
        td.colspan = 1;
        selectSportler.setLayoutData(td);
        selectSportler.setText("Benutzer aus der Datenbank auswählen");
        selectSportler
                .setDescription("Der ausgewählte Benutzer wird dannach im Opentraining Center verwendet. Alle Daten die mit diesem User importiert werden, werden ihm auch zugewiesen.");

        final Composite sportlerComposite = toolkit.createComposite(selectSportler);
        final GridLayout layoutClient = new GridLayout(2, false);
        sportlerComposite.setLayout(layoutClient);

        final List<Athlete> allAthletes = DatabaseAccessFactory.getDatabaseAccess().getAllAthletes();

        // gender
        final Label sportlerLabel = new Label(sportlerComposite, SWT.NONE);
        sportlerLabel.setText("Sportler/in: ");

        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = false;
        sportlerLabel.setLayoutData(gridData);
        //
        user = new Combo(sportlerComposite, SWT.NONE);
        for (final Athlete athlete : allAthletes) {
            user.add(athlete.getName(), athlete.getId());
        }

        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = false;
        gridData.horizontalAlignment = SWT.LEFT;
        gridData.horizontalIndent = 5;
        user.setLayoutData(gridData);

        final Button selectUser = new Button(sportlerComposite, SWT.PUSH);
        selectUser.setText("Select");
        selectUser.setEnabled(false);
        selectUser.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                logger.info("Neuen Benutzer auswählen: ");
                user.getSelectionIndex();
            }
        });

        user.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                selectUser.setEnabled(user.getSelectionIndex() >= 0);
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }
        });

        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = false;
        gridData.horizontalAlignment = SWT.LEFT;
        selectUser.setLayoutData(gridData);

        final Label spacer = new Label(sportlerComposite, SWT.NONE);
        spacer.setText("");

        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = false;
        gridData.horizontalSpan = 2;
        gridData.widthHint = 100;
        spacer.setLayoutData(gridData);

        selectSportler.setClient(sportlerComposite);
    }

    private void createAddSportlerMask(final Composite body) {

        final Section overviewSection = toolkit.createSection(body, Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
        overviewSection.addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });
        td = new TableWrapData();
        td.colspan = 1;
        overviewSection.setLayoutData(td);
        overviewSection.setText("Einen neuen Benutzer erstellen");
        overviewSection.setDescription("Ein neuer Benutzer/Profile wird in der Datenbank abgespeichert.");

        final Composite overViewComposite = toolkit.createComposite(overviewSection);
        final GridLayout layoutClient = new GridLayout(2, false);
        overViewComposite.setLayout(layoutClient);

        // name
        final Label firstLabel = new Label(overViewComposite, SWT.NONE);
        firstLabel.setText("Name: ");
        nameTf = new Text(overViewComposite, SWT.BORDER);

        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        nameTf.setLayoutData(gridData);

        // alter
        final Label ageLabel = new Label(overViewComposite, SWT.NONE);
        ageLabel.setText("Alter: ");
        ageText = new Text(overViewComposite, SWT.BORDER);

        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        ageText.setLayoutData(gridData);

        // pulse
        final Label pulseLabel = new Label(overViewComposite, SWT.NONE);
        pulseLabel.setText("Maximal Puls: ");
        pulseText = new Text(overViewComposite, SWT.BORDER);

        gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        pulseText.setLayoutData(gridData);

        // gender
        final Label genderLabel = new Label(overViewComposite, SWT.NONE);
        genderLabel.setText("Geschlecht: ");
        genderCombo = new Combo(overViewComposite, SWT.NONE);
        genderCombo.add("Männlich");
        genderCombo.add("Weiblich");

        final Button button1 = new Button(overViewComposite, SWT.PUSH);
        button1.setText("Speichern");
        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                logger.info("save " + sportler);
                DatabaseAccessFactory.getDatabaseAccess().save(new Athlete(sportler.getName(), sportler.getAge(), sportler.getMaxHeartBeat(), null, null));
            }
        });
        gridData = new GridData();
        gridData.horizontalAlignment = SWT.LEFT;
        gridData.grabExcessHorizontalSpace = false;
        gridData.horizontalIndent = 5;
        button1.setLayoutData(gridData);

        bindValues();

        overviewSection.setClient(overViewComposite);
    }

    private void bindValues() {
        // The DataBindingContext object will manage the databindings
        // Lets bind it
        final DataBindingContext ctx = new DataBindingContext();
        IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(nameTf);
        IObservableValue modelValue = BeanProperties.value(Sportler.class, "name").observe(sportler);
        ctx.bindValue(widgetValue, modelValue);

        // Bind the age including a validator
        widgetValue = WidgetProperties.text(SWT.Modify).observe(ageText);
        modelValue = BeanProperties.value(Sportler.class, "age").observe(sportler);

        final UpdateValueStrategy strategyAge = new UpdateValueStrategy();
        strategyAge.setBeforeSetValidator(new NumberValidator(18, 120, "Bitte das Alter eingeben"));

        final Binding bindValue = ctx.bindValue(widgetValue, modelValue, strategyAge, null);
        // Add some decorations
        ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);

        widgetValue = WidgetProperties.text(SWT.Modify).observe(pulseText);
        modelValue = BeanProperties.value(Sportler.class, "maxHeartBeat").observe(sportler);
        // Add an validator so that age can only be a number

        final UpdateValueStrategy strategyPulse = new UpdateValueStrategy();
        strategyPulse.setBeforeSetValidator(new NumberValidator(160, 220, "Bin den maximal Puls angeben"));

        final Binding bindMaxPulse = ctx.bindValue(widgetValue, modelValue, strategyPulse, null);
        // Add some decorations
        ControlDecorationSupport.create(bindMaxPulse, SWT.TOP | SWT.LEFT);

        widgetValue = WidgetProperties.selection().observe(genderCombo);
        modelValue = BeansObservables.observeValue(sportler, "gender");

        ctx.bindValue(widgetValue, modelValue);
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub

    }
}
