package ch.iseli.sportanalyzer.db;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IExecutableExtensionFactory;

import ch.opentrainingcenter.transfer.IAthlete;

public interface IDatabaseAccess extends IExecutableExtensionFactory {

    public static final String EXTENSION_POINT_NAME = "classImportedDao";

    /**
     * Gibt eine Liste von Filenamen zurück die diesem Athleten gehören. Das Resultat ist nicht ein absoluter File Pfad sondern einfach der Filename.
     * 
     * <pre>
     * z.b.: 201011201234.gmn
     * </pre>
     * 
     * @param athleteId
     *            id des sportlers.
     * @return eine liste mit den records von diesem sportler.
     */
    Map<Integer, String> getImportedRecords(IAthlete athlete);

    /**
     * Setzt in der Datenbank den Record auf importiert.
     * 
     * @param athleteId
     *            die ID des Athleten
     * @param name
     *            der Filename des GPS Files.
     * @return die ID von der Datenbank.
     */
    int importRecord(IAthlete athlete, String name);

    /**
     * Gibt den sportler mit
     * 
     * @param id
     * @return Gibt den sportler mit der angegeben id zurück oder null.
     */
    IAthlete getAthlete(int id);

    List<IAthlete> getAllAthletes();

    void removeImportedRecord(Integer id);

    /**
     * Wenn die db nicht vorhanden ist, wird die ganze datenbank mit sql queries erstellt.
     */
    void createDatabase();

    int save(IAthlete athlete);
}