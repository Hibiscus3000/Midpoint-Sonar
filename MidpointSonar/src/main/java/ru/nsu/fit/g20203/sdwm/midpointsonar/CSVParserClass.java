package ru.nsu.fit.g20203.sdwm.midpointsonar;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObject;
import ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint.MidPointSonarObjectClass;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CSVParserClass<O extends MidPointSonarObject> implements CSVParser<O> {
    @Override
    public Collection<O> parseReport(String reportPath) {
        ArrayList<O> records = new ArrayList<>();
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
                    String s=Integer.toString(i);
                    obj.addInValues(keys[i], value);
                    i++;
                }
                records.add((O)obj);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
