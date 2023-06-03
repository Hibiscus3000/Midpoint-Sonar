package ru.nsu.fit.g20203.sdwm.midpointsonar;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObjectClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CSVParserClass implements CSVParser {
    @Override
    public Collection<MidPointSonarObject> parseReport(String reportPath) throws IOException, CsvValidationException {
        ArrayList<MidPointSonarObject> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(reportPath)).
                withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build();) {
            String[] keys = null;
            keys = csvReader.readNext();
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                MidPointSonarObjectClass obj = new MidPointSonarObjectClass();
                int i = 0;
                for (String value : values) {
                    obj.addInValues(keys[i], value);
                    i++;
                }
                records.add(obj);
            }
        }
        return records;
    }
}
