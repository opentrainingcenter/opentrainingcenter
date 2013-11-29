package ch.opentrainingcenter.db.postgres;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import ch.opentrainingcenter.database.dao.DbScriptReader;

@SuppressWarnings("nls")
public class DbScriptReaderTest {

    @Test(expected = FileNotFoundException.class)
    public void readNull() throws FileNotFoundException {
        DbScriptReader.readDbScript(null);
    }

    @Test
    public void readFound() throws FileNotFoundException {
        final InputStream in = DatabaseAccessPostgres.class.getClassLoader().getResourceAsStream("otc_postgres.sql"); //$NON-NLS-1$
        final List<String> sql = DbScriptReader.readDbScript(in);
        assertNotNull(sql);
        assertTrue("Es müssen sich mehrere Queries im File befinden.", sql.size() > 10);
    }
}
