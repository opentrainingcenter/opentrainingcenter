package ch.opentrainingcenter.db;

public enum USAGE {
    /**
     * Use a production db configuration
     */
    PRODUCTION("otc", false, false),
    /**
     * use the otc_dev database
     */
    DEVELOPING("otc_dev", true, true),
    /**
     * the content of the database will be deleted after each test.
     */
    TEST("otc_junit", true, true);

    private final String dbName;
    private final boolean showSql;
    private final boolean formatSql;

    private USAGE(final String dbName, final boolean showSql, final boolean formatSql) {
        this.dbName = dbName;
        this.showSql = showSql;
        this.formatSql = formatSql;
    }

    public String getDbName() {
        return dbName;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public boolean isFormatSql() {
        return formatSql;
    }
}