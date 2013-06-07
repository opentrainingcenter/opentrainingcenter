package ch.opentrainingcenter.client.preferences;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.opentrainingcenter.client.Activator;
import ch.opentrainingcenter.core.PreferenceConstants;
import ch.opentrainingcenter.core.db.DatabaseAccessFactory;
import ch.opentrainingcenter.core.db.IDatabaseAccess;
import ch.opentrainingcenter.i18n.Messages;

public class DatabasePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final Logger LOGGER = Logger.getLogger(DatabasePreferencePage.class);
    private IPreferenceStore store;
    private boolean connectionTest = false;

    public DatabasePreferencePage() {
        super(GRID);
        setDescription(Messages.DatabasePreferencePage_0);
    }

    @Override
    public void init(final IWorkbench workbench) {
        store = Activator.getDefault().getPreferenceStore();
        setPreferenceStore(store);
    }

    @Override
    protected void createFieldEditors() {
        final Composite parent = getFieldEditorParent();

        final StringFieldEditor dbUrl = new StringFieldEditor(PreferenceConstants.DB_URL, Messages.DatabasePreferencePage_2, parent);
        final StringFieldEditor dbUser = new StringFieldEditor(PreferenceConstants.DB_USER, Messages.DatabasePreferencePage_3, parent);
        final StringFieldEditor dbPass = new StringFieldEditor(PreferenceConstants.DB_PASS, Messages.DatabasePreferencePage_4, parent);

        addField(dbUser);
        addField(dbPass);
        addField(dbUrl);

        final Map<String, IDatabaseAccess> model = DatabaseAccessFactory.getDbaccesses();
        final String[][] entries = new String[model.size() + 1][model.size() + 1];
        entries[0] = new String[] { "", "" }; //$NON-NLS-1$//$NON-NLS-2$
        int i = 1;
        for (final Map.Entry<String, IDatabaseAccess> entry : model.entrySet()) {
            entries[i] = new String[] { entry.getValue().getName(), entry.getValue().getName() };
            i++;
        }

        final ComboFieldEditor dbChooser = new ComboFieldEditor(PreferenceConstants.DB, Messages.DatabasePreferencePage_6, entries, parent);
        dbChooser.setEnabled(false, parent);
        addField(dbChooser);

        setErrorMessage(Messages.DatabasePreferencePage_7);

        final Button validate = new Button(parent, SWT.NONE);
        validate.setText(Messages.DatabasePreferencePage_8);

        validate.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String url = dbUrl.getStringValue();
                final String user = dbUser.getStringValue();
                final String password = dbPass.getStringValue();
                connectionTest = DatabaseAccessFactory.getDatabaseAccess().validateConnection(url, user, password);
                if (!connectionTest) {
                    setErrorMessage(Messages.DatabasePreferencePage_9);
                } else {
                    // do nothing
                }
                checkState();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                // do nothing
            }
        });
    }

    @Override
    protected void checkState() {
        setValid(connectionTest);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }
}