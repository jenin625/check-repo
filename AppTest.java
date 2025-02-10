package com.example.assignment2;

import java.io.*;
import java.nio.file.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    private static final String CSV_FILE = "src/test/java/com/example/assignment2/test.csv";  // Path to the CSV file
    private static final String OUTPUT_PDF = "output.pdf";
    private static final String OUTPUT_XLS = "output.xlsx";

    @BeforeAll
    static void setUp() throws IOException {
        // Create a sample CSV file for testing in the resources folder
        String csvContent = "Name, Age, Occupation\nJohn Doe, 25, Developer\nJane Smith, 30, Designer";
        Files.write(Paths.get(CSV_FILE), csvContent.getBytes());
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Clean up the generated files after tests
        Files.deleteIfExists(Paths.get(CSV_FILE));
        Files.deleteIfExists(Paths.get(OUTPUT_PDF));
        Files.deleteIfExists(Paths.get(OUTPUT_XLS));
    }

    @Test
    void testPdfisCreated() throws Exception {
        String[] args = {"PDF", CSV_FILE};
        App.main(args);

        // test that the PDF file was created
        File pdfFile = new File(OUTPUT_PDF);
        assertTrue(pdfFile.exists(), "PDF should be generated");


    }

    @Test
    void testXlsisCreated() throws Exception {
        String[] args = {"XLS", CSV_FILE};
        App.main(args);

        // test that the XLS file was created
        File xlsFile = new File(OUTPUT_XLS);
        assertTrue(xlsFile.exists(), "XLS should be generated");


    }

    @Test
    void testInvalidArguments() {
        // Test for incorrect arguments (invalid file type)
        String[] args = {"INVALID", "file.csv"};
        assertDoesNotThrow(() -> App.main(args), "App should not crash on invalid argument");
    }
}
