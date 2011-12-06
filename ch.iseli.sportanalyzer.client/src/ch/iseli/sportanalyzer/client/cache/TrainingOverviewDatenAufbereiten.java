package ch.iseli.sportanalyzer.client.cache;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ch.iseli.sportanalyzer.client.model.SimpleTraining;

public class TrainingOverviewDatenAufbereiten {

    private static final Logger logger = Logger.getLogger(TrainingOverviewDatenAufbereiten.class);

    private final List<SimpleTraining> all;
    private final List<SimpleTraining> trainingsPerWeek;
    private final List<SimpleTraining> trainingsPerMonth;

    public TrainingOverviewDatenAufbereiten() {
        super();
        final TrainingCenterDataCache cache = TrainingCenterDataCache.getInstance();
        this.all = cache.getAllSimpleTrainings();
        cache.addListener(new IRecordListener() {

            @Override
            public void recordChanged(final Collection<TrainingCenterRecord> entry) {
                // Datenstruktur updaten
                logger.debug("update Struktur...");
                trainingsPerWeek.clear();
                trainingsPerWeek.addAll(createMonatsUndWochenMap(Calendar.YEAR, Calendar.WEEK_OF_MONTH));
                //
                trainingsPerMonth.clear();
                trainingsPerMonth.addAll(createMonatsUndWochenMap(Calendar.YEAR, Calendar.MONTH));
            }
        });
        logger.debug("Initialize Woche Start");
        trainingsPerWeek = createMonatsUndWochenMap(Calendar.YEAR, Calendar.WEEK_OF_MONTH);
        logger.debug("Initialize Woche fertig");

        logger.debug("Initialize Monat Start");
        trainingsPerMonth = createMonatsUndWochenMap(Calendar.YEAR, Calendar.MONTH);
        logger.debug("Initialize Monat fertig");
    }

    private List<SimpleTraining> createMonatsUndWochenMap(final int outer, final int inner) {
        final Map<Integer, Map<Integer, List<SimpleTraining>>> trainingsPer = new HashMap<Integer, Map<Integer, List<SimpleTraining>>>();
        for (final SimpleTraining training : all) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(training.getDatum());
            final int year = cal.get(outer);
            // da monat mit 0 beginnt muss noch eins addiert werden.
            final int week = inner == Calendar.MONTH ? cal.get(inner) + 1 : cal.get(inner);
            logger.debug("Lauf aus der inner " + week + " vom Jahr: " + year);
            Map<Integer, List<SimpleTraining>> yearMap = trainingsPer.get(year);
            if (yearMap == null) {
                yearMap = new TreeMap<Integer, List<SimpleTraining>>();
            }
            List<SimpleTraining> weekMap = yearMap.get(week);
            if (weekMap == null) {
                weekMap = new ArrayList<SimpleTraining>();
            }
            weekMap.add(training);
            yearMap.put(week, weekMap);
            trainingsPer.put(year, yearMap);
        }

        return createSum(trainingsPer);
    }

    private List<SimpleTraining> createSum(final Map<Integer, Map<Integer, List<SimpleTraining>>> trainingsPer) {
        final List<SimpleTraining> result = new ArrayList<SimpleTraining>();
        for (final Map.Entry<Integer, Map<Integer, List<SimpleTraining>>> perYear : trainingsPer.entrySet()) {
            for (final Map.Entry<Integer, List<SimpleTraining>> perInner : perYear.getValue().entrySet()) {
                double distance = 0;
                double seconds = 0;
                int heartRate = 0;
                int countHeartIsZero = 0;
                Date date = null;
                for (final SimpleTraining training : perInner.getValue()) {
                    distance += training.getDistanzInMeter();
                    seconds += training.getDauerInSekunden();
                    final int avgHeartRate = training.getAvgHeartRate();
                    if (avgHeartRate <= 0) {
                        countHeartIsZero++;
                    }
                    heartRate += Integer.valueOf(avgHeartRate);
                    date = training.getDatum();
                }
                final SimpleTraining e = new SimpleTraining(distance, seconds, date, heartRate / (perInner.getValue().size() - countHeartIsZero));
                if (e.getAvgHeartRate() > 0) {
                    result.add(e);
                }
            }
        }
        return result;
    }

    public List<SimpleTraining> getTrainingsPerDay() {
        return Collections.unmodifiableList(all);
    }

    public List<SimpleTraining> getTrainingsPerMonth() {
        return Collections.unmodifiableList(trainingsPerMonth);
    }

    public List<SimpleTraining> getTrainingsPerWeek() {
        return Collections.unmodifiableList(trainingsPerWeek);
    }

    public List<SimpleTraining> getTrainingsPerYear() {
        // TODO Auto-generated method stub
        return null;
    }

}
