package ch.iseli.sportanalyzer.client.views.navigation;

import java.util.Collection;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

    @Override
    public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public Object[] getElements(Object parent) {
        Collection<?> l = (Collection<?>) parent;
        return l.toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        // TrainingCenterDatabaseTParent parent = (TrainingCenterDatabaseTParent) parentElement;
        // return parent.getChilds().toArray();
        return null;
    }

    @Override
    public Object getParent(Object element) {
        // if (element instanceof TrainingCenterDatabaseTParent) {
        // return null;
        // }
        // TrainingCenterDatabaseTChild child = (TrainingCenterDatabaseTChild) element;
        // return child.getParent();
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        // return element instanceof TrainingCenterDatabaseTParent;
        return false;
    }

}