package ch.iseli.sportanalyzer.client;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import ch.iseli.sportanalyzer.client.action.ImportGpsFilesAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of the actions added to a workbench window. Each window will be populated with new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    // Actions - important to allocate these only in makeActions, and then use
    // them
    // in the fill methods. This ensures that the actions aren't recreated
    // when fillActionBars is called with FILL_PROXY.
    private IWorkbenchAction exitAction;
    private IWorkbenchAction aboutAction;
    private IWorkbenchAction newWindowAction;
    private OpenViewAction openViewAction;
    private Action messagePopupAction;
    private Action importGpsFiles;
    private IAction windowsAction;
    private IWorkbenchAction openPerspective;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    @Override
    protected void makeActions(final IWorkbenchWindow window) {

        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);

        aboutAction = ActionFactory.ABOUT.create(window);
        register(aboutAction);

        windowsAction = ActionFactory.PREFERENCES.create(window);

        openPerspective = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);

        newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
        register(newWindowAction);

        openViewAction = new OpenViewAction(window, "Open Another Message View", View.ID);
        register(openViewAction);

        messagePopupAction = new MessagePopupAction("Open Message", window);
        register(messagePopupAction);

        importGpsFiles = new ImportGpsFilesAction(window, "Import GPS Files");
        register(importGpsFiles);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        MenuManager windowsMenu = new MenuManager("&Windows", IWorkbenchActionConstants.M_WINDOW);
        menuBar.add(fileMenu);
        // Add a group marker indicating where action set menus will appear.
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(helpMenu);

        menuBar.add(windowsMenu);

        // File
        fileMenu.add(newWindowAction);
        fileMenu.add(new Separator());
        fileMenu.add(messagePopupAction);
        fileMenu.add(openViewAction);
        fileMenu.add(new Separator());
        fileMenu.add(exitAction);

        // Help
        helpMenu.add(aboutAction);

        // Window
        windowsMenu.add(windowsAction);
        windowsMenu.add(openPerspective);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));
        toolbar.add(importGpsFiles);
        toolbar.add(openPerspective);
    }
}
