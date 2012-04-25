package ch.opentrainingcenter.client.charts;

import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.plot.IntervalMarker;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import ch.opentrainingcenter.client.Messages;
import ch.opentrainingcenter.client.helper.ZoneHelper;
import ch.opentrainingcenter.transfer.IAthlete;

public final class HeartIntervallCreator {

    private HeartIntervallCreator() {

    }

    public static Map<ZoneHelper.Zone, IntervalMarker> createMarker(final IAthlete athlete) {
        final double aerobeStart = ZoneHelper.getZonenWert(athlete, ZoneHelper.Zone.AEROBE);
        final double schwelleStart = ZoneHelper.getZonenWert(athlete, ZoneHelper.Zone.SCHWELLE);
        final double anaerobeStart = ZoneHelper.getZonenWert(athlete, ZoneHelper.Zone.ANAEROBE);

        final Map<ZoneHelper.Zone, IntervalMarker> result = new HashMap<ZoneHelper.Zone, IntervalMarker>();

        final IntervalMarker aerobe = new IntervalMarker(aerobeStart, schwelleStart);
        aerobe.setLabel(Messages.HeartIntervallCreator_0);
        aerobe.setLabelAnchor(RectangleAnchor.LEFT);
        aerobe.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        aerobe.setPaint(ZoneHelper.getZonenFarbe(ZoneHelper.Zone.AEROBE));
        result.put(ZoneHelper.Zone.AEROBE, aerobe);

        final IntervalMarker schwelle = new IntervalMarker(schwelleStart, anaerobeStart);
        schwelle.setLabel(Messages.HeartIntervallCreator_1);
        schwelle.setLabelAnchor(RectangleAnchor.LEFT);
        schwelle.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        schwelle.setPaint(ZoneHelper.getZonenFarbe(ZoneHelper.Zone.SCHWELLE));
        result.put(ZoneHelper.Zone.SCHWELLE, schwelle);

        final IntervalMarker anaerobe = new IntervalMarker(anaerobeStart, athlete.getMaxHeartRate());
        anaerobe.setLabel(Messages.HeartIntervallCreator_2);
        anaerobe.setLabelAnchor(RectangleAnchor.LEFT);
        anaerobe.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        anaerobe.setPaint(ZoneHelper.getZonenFarbe(ZoneHelper.Zone.ANAEROBE));
        result.put(ZoneHelper.Zone.ANAEROBE, anaerobe);
        return result;
    }

}
