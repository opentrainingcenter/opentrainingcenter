package ch.opentrainingcenter.model;

import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import ch.opentrainingcenter.model.training.ISimpleTraining;
import ch.opentrainingcenter.tcx.ActivityLapT;
import ch.opentrainingcenter.tcx.ActivityT;
import ch.opentrainingcenter.tcx.ExtensionsT;
import ch.opentrainingcenter.tcx.IntensityT;
import ch.opentrainingcenter.transfer.ActivityExtension;
import ch.opentrainingcenter.transfer.CommonTransferFactory;
import ch.opentrainingcenter.transfer.ITraining;

public final class TrainingOverviewFactory {

    private static final Logger LOGGER = Logger.getLogger(TrainingOverviewFactory.class);

    private TrainingOverviewFactory() {
    }

    public static ITraining creatTrainingOverview(final ActivityT activity) {
        if (activity != null) {
            return create(activity);
        } else {
            return null;
        }
    }

    private static ITraining create(final ActivityT activity) {
        // datum
        final XMLGregorianCalendar date = activity.getId();
        final Date dateOfStart = date.toGregorianCalendar().getTime();
        final List<ActivityLapT> laps = activity.getLap();
        double distance = 0.0;
        double timeInSeconds = 0.0;
        int averageHeartRateBpm = 0;
        int maxHeartBeat = 0;
        int lapWithCardio = 0;
        double maximumSpeed = 0;
        for (final ActivityLapT lap : laps) {
            if (IntensityT.ACTIVE.equals(lap.getIntensity())) {
                distance += lap.getDistanceMeters();
                if (lap.getMaximumSpeed() != null && maximumSpeed < lap.getMaximumSpeed()) {
                    maximumSpeed = lap.getMaximumSpeed();
                }
                timeInSeconds += lap.getTotalTimeSeconds();
                if (!hasCardio(lap)) {
                    continue;
                }
                lapWithCardio++;
                averageHeartRateBpm += lap.getAverageHeartRateBpm() != null ? lap.getAverageHeartRateBpm().getValue() : 0;
                if (maxHeartBeat < lap.getMaximumHeartRateBpm().getValue()) {
                    maxHeartBeat = lap.getMaximumHeartRateBpm().getValue();
                }
            }
            LOGGER.debug("lap: " + lap.getIntensity() + " distance: " + distance); //$NON-NLS-1$//$NON-NLS-2$
        }
        Integer avgHeartRate = null;
        if (lapWithCardio > 0) {
            avgHeartRate = averageHeartRateBpm / lapWithCardio;
        } else {
            avgHeartRate = 0;
        }
        final ExtensionsT extensions = activity.getExtensions();

        ActivityExtension activityExtension = new ActivityExtension();
        if (extensions != null) {
            final List<Object> any = extensions.getAny();
            if (any != null && !any.isEmpty() && any.get(0) != null) {
                activityExtension = (ActivityExtension) any.get(0);
            }
        }

        return CommonTransferFactory.createTraining(dateOfStart, timeInSeconds, distance, avgHeartRate, maxHeartBeat, maximumSpeed, activityExtension);
    }

    private static boolean hasCardio(final ActivityLapT lap) {
        return lap.getMaximumHeartRateBpm() != null;
    }

    public static ISimpleTraining creatSimpleTraining(final ActivityT activity) {
        if (activity != null) {
            return ModelFactory.createSimpleTraining(create(activity));
        } else {
            return null;
        }
    }
}