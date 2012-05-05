package ch.opentrainingcenter.client.views.dialoge;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import ch.opentrainingcenter.client.Activator;
import ch.opentrainingcenter.client.Messages;
import ch.opentrainingcenter.client.PreferenceConstants;
import ch.opentrainingcenter.importer.ConvertContainer;
import ch.opentrainingcenter.importer.ExtensionHelper;
import ch.opentrainingcenter.importer.IConvert2Tcx;

public class ImportFileDialog implements IFilterDialog {

    private final IPreferenceStore preferenceStore;
    private final String defaultLocation;
    private final ConvertContainer cc;
    private final List<String> filePrefixes = new ArrayList<String>();
    private final FileDialog dialog;

    public ImportFileDialog(final Shell parent) {
        dialog = new FileDialog(parent, SWT.MULTI);
        preferenceStore = Activator.getDefault().getPreferenceStore();
        defaultLocation = preferenceStore.getString(PreferenceConstants.GPS_FILE_LOCATION);
        dialog.setFilterPath(defaultLocation);

        cc = new ConvertContainer(ExtensionHelper.getConverters());
        readCurrentPreferences();

        dialog.setFilterExtensions(filePrefixes.toArray(new String[0]));
        dialog.setText(Messages.ImportManualGpsFiles_FileDialog);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ch.opentrainingcenter.client.views.dialoge.IFilterDialog#open()
     */
    @Override
    public String open() {
        return dialog.open();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ch.opentrainingcenter.client.views.dialoge.IFilterDialog#getFileNames()
     */
    @Override
    public String[] getFileNames() {
        return dialog.getFileNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ch.opentrainingcenter.client.views.dialoge.IFilterDialog#getFilterPath()
     */
    @Override
    public String getFilterPath() {
        return dialog.getFilterPath();
    }

    private void readCurrentPreferences() {
        for (final IConvert2Tcx tcx : cc.getAllConverter()) {
            if (preferenceStore.getBoolean(PreferenceConstants.FILE_SUFFIX_FOR_BACKUP + tcx.getFilePrefix())) {
                filePrefixes.add("*." + tcx.getFilePrefix()); //$NON-NLS-1$
                filePrefixes.add("*." + tcx.getFilePrefix().toUpperCase()); //$NON-NLS-1$
            }
        }
    }

}