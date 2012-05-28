package ch.opentrainingcenter.client;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import ch.opentrainingcenter.client.action.RestartOtc;
import ch.opentrainingcenter.client.cache.Cache;
import ch.opentrainingcenter.client.cache.impl.TrainingCenterDataCache;
import ch.opentrainingcenter.db.DatabaseAccessFactory;
import ch.opentrainingcenter.db.IDatabaseAccess;
import ch.opentrainingcenter.importer.ExtensionHelper;
import ch.opentrainingcenter.importer.IConvert2Tcx;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private static final Logger LOG = Logger.getLogger(ApplicationActionBarAdvisor.class);

    private IWorkbenchAction restart;
    private IWorkbenchAction exitAction;
    private IAction windowsAction;
    private IWorkbenchAction aboutAction;
    private Action importGpsFilesManual;
    private Action backupGpsFiles;
    private final IPreferenceStore store;
    private final IDatabaseAccess databaseAccess;

    private final Cache cache;

    private final Map<String, IConvert2Tcx> converters;

    public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
        this(configurer, Activator.getDefault().getPreferenceStore(), TrainingCenterDataCache.getInstance(), DatabaseAccessFactory
                .getDatabaseAccess(), ExtensionHelper.getConverters());
    }

    /**
     * Konstructor für tests
     */
    public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer, final IPreferenceStore store, final Cache cache,
            final IDatabaseAccess databaseAccess, final Map<String, IConvert2Tcx> converters) {
        super(configurer);
        this.store = store;
        this.cache = cache;
        this.databaseAccess = databaseAccess;
        this.converters = converters;

    }

    @Override
    protected void makeActions(final IWorkbenchWindow window) {

        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);

        restart = new RestartOtc(window, Messages.ApplicationActionBarAdvisor_Restart);
        register(restart);

        aboutAction = ActionFactory.ABOUT.create(window);
        register(aboutAction);

        windowsAction = ActionFactory.PREFERENCES.create(window);
        register(windowsAction);
    }

    @Override
    protected void fillMenuBar(final IMenuManager menuBar) {
        final MenuManager fileMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_File, IWorkbenchActionConstants.M_FILE);
        final MenuManager helpMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_Help, IWorkbenchActionConstants.M_HELP);
        final MenuManager windowsMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_Windows, IWorkbenchActionConstants.M_WINDOW);
        menuBar.add(fileMenu);
        menuBar.add(windowsMenu);
        menuBar.add(helpMenu);

        // File
        fileMenu.add(restart);
        fileMenu.add(exitAction);

        // Window
        windowsMenu.add(windowsAction);

        // Help
        helpMenu.add(aboutAction);

    }

    @Override
    protected void fillCoolBar(final ICoolBarManager coolBar) {
        // final IToolBarManager toolbar = new ToolBarManager(SWT.FLAT |
        // SWT.RIGHT);
        //        coolBar.add(new ToolBarContributionItem(toolbar, "main")); //$NON-NLS-1$
        // toolbar.add(backupGpsFiles);
    }
}
