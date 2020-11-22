package com.systemsplanet;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByPosition;
//import javax.persistence.Column;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FileUploadService {
    /**
     * @param csvUrl       location of the physical file.
     * @param type         Employee.class
     * @param delimiter    can be , | # etc
     * @param noOfLineSkip make it 0(Zero) so that it should not skip any line.
     * @return list of objects
     */
    public static <T> List<T> readCSVContentInArray(String csvUrl, Class<T> type, char delimiter, int noOfLineSkip) {
        List<T> lstCsvContent = new ArrayList<>();
        BufferedReader reader = null;
        CSVReader csv = null;
        try {
            URL stockURL = new URL(csvUrl);
            reader = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            CSVParser parser = new CSVParserBuilder().withSeparator(delimiter).withIgnoreQuotations(true).build();
            csv = new CSVReaderBuilder(reader).withSkipLines(noOfLineSkip).withCSVParser(parser).build();
            String[] nextLine;
            long rowNumber = 0;
            Field[] field = type.getDeclaredFields();
            while ((nextLine = csv.readNext()) != null) {
                rowNumber++;
                try {
                    T obj = type.getDeclaredConstructor().newInstance();
                    for (Field f : field) {
                        if (!f.isSynthetic()) {
                            f.setAccessible(true);
                            Annotation[] ann = f.getDeclaredAnnotations();
                            CsvBindByPosition csv1 = (CsvBindByPosition) ann[0];
                            //Column c = (Column) ann[1];
                            try {
                                if (csv1.position() < nextLine.length) {
                                    if (csv1.required() && (nextLine[csv1.position()] == null || nextLine[csv1.position()].trim().isEmpty())) {
                                        System.err.println("Mandatory field is missing in row " + rowNumber + ", " + csv1.position());
                                    }
                                    if (f.getType().equals(String.class)) {
                                        f.set(obj, nextLine[csv1.position()]);
                                    } else if (f.getType().equals(Boolean.class)) {
                                        f.set(obj, nextLine[csv1.position()]);
                                    } else if (f.getType().equals(Integer.class)) {
                                        f.set(obj, Integer.parseInt(nextLine[csv1.position()]));
                                    } else if (f.getType().equals(Long.class)) {
                                        f.set(obj, Long.parseLong(nextLine[csv1.position()]));
                                    } else if (f.getType().equals(Double.class) && null != nextLine[csv1.position()] && !nextLine[csv1.position()].trim().isEmpty()) {
                                        f.set(obj, Double.parseDouble(nextLine[csv1.position()]));
                                    } else if (f.getType().equals(Double.class) && ((nextLine[csv1.position()] == null) || nextLine[csv1.position()].isEmpty())) {
                                        f.set(obj, Double.parseDouble("0.0"));
                                    } else if (f.getType().equals(Date.class)) {
                                        f.set(obj, nextLine[csv1.position()]);
                                    }
                                }
                            } catch (Exception fttEx) {
                                System.err.println("Exception when parsing the file: " + csvUrl + " err:" + fttEx.getMessage());
                            }
                        }
                    }
                    lstCsvContent.add(obj);
                } catch (Exception ex) {
                    System.err.println("Exception: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.err.println("Exception:::::::: " + ex.getMessage());
        } finally {
            if (csv != null) {
                try {
                    csv.close();
                } catch (IOException ioe) {
                    System.err.println("Exception when closing csv: " + ioe.getMessage());
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    System.err.println("Exception when closing reader: " + ioe.getMessage());
                }
            }
        }
        return lstCsvContent;
    }

}