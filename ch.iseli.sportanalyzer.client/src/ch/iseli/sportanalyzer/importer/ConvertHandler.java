package ch.iseli.sportanalyzer.importer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertHandler {
    final Map<String, IConvert2Tcx> converters;

    public ConvertHandler() {
        this.converters = new HashMap<String, IConvert2Tcx>();
    }

    public void addConverter(final IConvert2Tcx converter) {
        converters.put(converter.getFilePrefix(), converter);
    }

    public IConvert2Tcx getMatchingConverter(final File fileToImport) {
        final String name = fileToImport.getName();
        final String prefix = name.substring(name.indexOf('.') + 1, name.length());
        return converters.get(prefix);
    }

    /**
     * Gibt alle unterstützen File Suffixes zurück. Also zum Beispiel {"*.gmn", "*.fitnesslog"}
     */
    public List<String> getSupportedFileSuffixes() {
        final List<String> suffixes = new ArrayList<String>();
        for (final Map.Entry<String, IConvert2Tcx> entry : converters.entrySet()) {
            suffixes.add("*." + entry.getKey()); //$NON-NLS-1$
        }
        return suffixes;
    }
}