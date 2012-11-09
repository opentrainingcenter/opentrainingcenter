package ch.opentrainingcenter.client;

/**
 * Constant definitions for plug-in preferences
 */
public final class PreferenceConstants {

    private static final String COLOR_POSTFIX = "_color"; //$NON-NLS-1$
    /**
     * Ort wo die Daten auf dem Compi gespeichert sind
     */
    public static final String GPS_FILE_LOCATION = "gps_path"; //$NON-NLS-1$
    /**
     * Ort, wo OTC die Daten absichert.
     */
    public static final String GPS_FILE_LOCATION_PROG = "gps_path_prog"; //$NON-NLS-1$
    public static final String ATHLETE_ID = "athlete_id"; //$NON-NLS-1$
    public static final String BACKUP_FILE_LOCATION = "backup_path"; //$NON-NLS-1$
    public static final String FILE_SUFFIX_FOR_BACKUP = "filesuffix_"; //$NON-NLS-1$
    //    public static final String KM_PER_WEEK = "KM_PER_WEEK"; //$NON-NLS-1$
    public static final String WEEK_FOR_PLAN = "WEEK_FOR_PLAN"; //$NON-NLS-1$
    public static final String KM_PER_WEEK_COLOR_NOT_DEFINED = "KM_PER_WEEK_COLOR_NOT_DEFINED"; //$NON-NLS-1$
    public static final String KM_PER_WEEK_COLOR_BELOW = "KM_PER_WEEK_COLOR_BELOW"; //$NON-NLS-1$
    public static final String KM_PER_WEEK_COLOR_ABOVE = "KM_PER_WEEK_COLOR_ABOVE"; //$NON-NLS-1$
    public static final String DISTANCE_CHART_COLOR = "DISTANCE_CHART_COLOR"; //$NON-NLS-1$
    public static final String DISTANCE_HEART_COLOR = "DISTANCE_HEART_COLOR"; //$NON-NLS-1$
    public static final String RECOM = "RECOM";//$NON-NLS-1$
    public static final String RECOM_COLOR = RECOM + COLOR_POSTFIX;
    public static final String GA1 = "GA1";//$NON-NLS-1$
    public static final String GA1_COLOR = GA1 + COLOR_POSTFIX;
    public static final String GA12 = "GA12";//$NON-NLS-1$
    public static final String GA12_COLOR = GA12 + COLOR_POSTFIX;
    public static final String GA2 = "GA2";//$NON-NLS-1$
    public static final String GA2_COLOR = GA2 + COLOR_POSTFIX;
    public static final String WSA = "WSA";//$NON-NLS-1$
    public static final String WSA_COLOR = WSA + COLOR_POSTFIX;
    public static final String RUHEPULS_COLOR = "RUHEPULS"; //$NON-NLS-1$
    public static final String GEWICHT_COLOR = "GEWICHT"; //$NON-NLS-1$
    public static final String ZIEL_ERFUELLT_COLOR = "ZIEL_ERFUELLT_COLOR"; //$NON-NLS-1$
    public static final String ZIEL_NICHT_ERFUELLT_COLOR = "ZIEL_NICHT_ERFUELLT_COLOR"; //$NON-NLS-1$

    private PreferenceConstants() {

    }

}
