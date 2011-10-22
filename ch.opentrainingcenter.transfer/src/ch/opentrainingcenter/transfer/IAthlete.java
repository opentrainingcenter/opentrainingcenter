package ch.opentrainingcenter.transfer;

import java.util.Set;

import ch.opentrainingcenter.transfer.internal.Health;
import ch.opentrainingcenter.transfer.internal.Imported;

public interface IAthlete {

    public abstract int getId();

    public abstract void setId(int id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Set<Health> getHealths();

    public abstract void setHealths(Set<Health> healths);

    public abstract Set<Imported> getImporteds();

    public abstract void setImporteds(Set<Imported> importeds);

}