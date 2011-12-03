package ch.iseli.sportanalyzer.client.athlete;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class NumberValidator implements IValidator {

    private final int max;
    private final int min;

    public NumberValidator(final int min, final int max) {
        this.min = min;
        this.max = max;

    }

    @Override
    public IStatus validate(final Object value) {
        if (value instanceof Integer) {
            final String s = String.valueOf(value);
            if (s.matches("\\d*")) {
                final int number = Integer.parseInt(s);
                if (number < min) {
                    return ValidationStatus.error("Zahl zu klein. Muss mindestens " + min + " sein.");
                } else if (number > max) {
                    return ValidationStatus.error("Zahl zu gross. Muss kleiner als " + max + " sein.");
                } else {
                    return ValidationStatus.OK_STATUS;
                }
            }
        }
        return ValidationStatus.error("Keine Zahl");
    }
}
