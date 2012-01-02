package ch.iseli.sportanalyzer.db;

import org.apache.log4j.Logger;

public class DatabaseHelper {

    public static final Logger logger = Logger.getLogger(DatabaseHelper.class);

    public static boolean isDatabaseLocked() {
        try {
            DatabaseAccessFactory.getDatabaseAccess().getAthlete(1);
        } catch (final Exception e) {
            final Throwable cause = e.getCause();
            final String message = cause.getMessage();
            if (message != null && message.contains("Locked by another process")) { //$NON-NLS-1$
                logger.error("Database Locked by another process"); //$NON-NLS-1$
                System.exit(0);
                return true;
            }
        }
        return false;
    }
}
