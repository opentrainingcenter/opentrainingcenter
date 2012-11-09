package ch.opentrainingcenter.client.model.planing.impl;

import java.util.List;

import ch.opentrainingcenter.client.model.RunType;
import ch.opentrainingcenter.client.model.planing.IPastPlanung;
import ch.opentrainingcenter.transfer.IImported;
import ch.opentrainingcenter.transfer.IPlanungWoche;
import ch.opentrainingcenter.transfer.ITrainingType;

public class PastPlanungImpl implements IPastPlanung {

    private final IPlanungWoche planung;
    private final int kmEffective;
    private final int kmLangerLaufEffective;
    private final boolean interval;

    public PastPlanungImpl(final IPlanungWoche planung, final List<IImported> effective) {
        this.planung = planung;
        if (effective == null || effective.isEmpty()) {
            kmEffective = 0;
            kmLangerLaufEffective = 0;
            interval = false;
        } else {
            final int[] analyse = analyse(effective);
            kmEffective = analyse[0];
            kmLangerLaufEffective = analyse[1];
            interval = analyse[2] == 1 ? true : false;
        }

    }

    private int[] analyse(final List<IImported> effective) {
        final int[] result = new int[3];
        int km = 0;
        int longest = 0;
        boolean inter = false;
        for (final IImported record : effective) {
            final double kmAbgerundet = Math.floor(record.getTraining().getLaengeInMeter() / 1000);
            if (longest < kmAbgerundet) {
                longest = (int) kmAbgerundet;
            }
            km += kmAbgerundet;
            final ITrainingType type = record.getTrainingType();
            if (isInterval(type.getId())) {
                inter = true;
            }
        }
        result[0] = km;
        result[1] = longest;
        result[2] = inter ? 1 : 0;
        return result;
    }

    private boolean isInterval(final int id) {
        final boolean ext = RunType.EXT_INTERVALL.equals(RunType.getRunType(id));
        final boolean in = RunType.INT_INTERVALL.equals(RunType.getRunType(id));
        return ext || in;
    }

    @Override
    public IPlanungWoche getPlanung() {
        return planung;
    }

    @Override
    public int getKmEffective() {
        return kmEffective;
    }

    @Override
    public int getLangerLaufEffective() {
        return kmLangerLaufEffective;
    }

    @Override
    public boolean hasInterval() {
        return interval;
    }

}
