package ru.nsu.fit.g20203.sdwm.midpointsonar;

import com.opencsv.exceptions.CsvValidationException;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;

import java.io.IOException;
import java.util.Collection;

public interface CSVParser {

    Collection<MidPointSonarObject> parseReport(String reportPath) throws IOException, CsvValidationException;
}
