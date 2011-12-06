package ch.iseli.sportanalyzer.client.views.overview;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;

import com.eclipsesource.widgets.gmaps.GMap;
import com.eclipsesource.widgets.gmaps.LatLng;

public class MapViewer extends Composite {

    private final static Logger logger = Logger.getLogger(MapViewer.class);
    public final static String ID = "ch.iseli.sportanalyzer.client.views.overview.MapViewer";
    private GMap gmap;
    private final String INIT_CENTER;
    static final private int INIT_ZOOM = 16;
    static final private int INIT_TYPE = GMap.TYPE_HYBRID;
    private final Composite parent;
    private final String path;

    public MapViewer(final Composite parent, final int style, final String path, final String initCenter) {
        super(parent, style);
        this.parent = parent;
        this.path = path;
        INIT_CENTER = initCenter;
    }

    public Composite getComposite() {
        logger.debug("create map");
        final SashForm sash = new SashForm(parent, SWT.HORIZONTAL);
        createMap(sash, path);
        return sash;
    }

    private void createMap(final Composite parent, final String path) {
        gmap = new GMap(parent, SWT.BORDER, path);
        gmap.setCenter(stringToLatLng(INIT_CENTER));
        gmap.setZoom(INIT_ZOOM);
        gmap.setType(INIT_TYPE);
    }

    private LatLng stringToLatLng(final String input) {
        LatLng result = null;
        if (input != null) {
            final String temp[] = input.split(",");
            if (temp.length == 2) {
                try {
                    final double lat = Double.parseDouble(temp[0]);
                    final double lon = Double.parseDouble(temp[1]);
                    result = new LatLng(lat, lon);
                } catch (final NumberFormatException ex) {
                }
            }
        }
        return result;
    }

}