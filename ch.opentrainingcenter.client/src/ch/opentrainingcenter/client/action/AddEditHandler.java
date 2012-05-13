package ch.opentrainingcenter.client.action;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import ch.opentrainingcenter.client.cache.impl.TrainingCenterDataCache;
import ch.opentrainingcenter.db.DatabaseAccessFactory;
import ch.opentrainingcenter.transfer.IImported;

public class AddEditHandler extends AbstractHandler {

    public static final String ID = "ch.opentrainingcenter.client.action.AddEditHandler"; //$NON-NLS-1$

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);
        final TreeSelection tree = (TreeSelection) selection;

        final IImported record = (IImported) tree.getFirstElement();
        final String initialValue = record.getNote();
        final InputDialog dialog = new InputDialog(null, "Notiz hinzufügen / bearbeiten", "Notiz bearbeiten", initialValue, null);
        if (dialog.open() == InputDialog.OK) {
            record.setNote(dialog.getValue());
            updateNote(record);
        }
        return null;
    }

    private void updateNote(final IImported record) {
        DatabaseAccessFactory.getDatabaseAccess().updateRecord(record);
        TrainingCenterDataCache.getInstance().update(record);
    }
}
