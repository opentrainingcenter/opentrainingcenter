package ch.iseli.sport4ever.importer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import ch.iseli.sport4ever.importer.internal.Gmn2Tcx;

public class Gmn2TcxTest {

    private static final String GARMIN_FILE = "garmin/20110814T142321.gmn";
    private Gmn2Tcx importer;
    private File file;
    private String path;

    @Before
    public void setUp() {
	file = new File(GARMIN_FILE);
	path = file.getAbsolutePath();
	path = path.replace(GARMIN_FILE, "resources");
	importer = new Gmn2Tcx(path);
    }

    @Test
    public void testFileNotNull() {
	assertNotNull(file);
	assertTrue(file.length() > 0);
    }

    @Test
    public void simpleConvert() throws IOException {
	InputStream stream = importer.convert2Tcx(file);
	assertNotNull(stream);
    }
}
