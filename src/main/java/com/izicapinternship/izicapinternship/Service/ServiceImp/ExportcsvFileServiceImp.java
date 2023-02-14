package com.izicapinternship.izicapinternship.Service.ServiceImp;

import com.izicapinternship.izicapinternship.Entity.Question;
import com.izicapinternship.izicapinternship.Service.IExportcsvService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ExportcsvFileServiceImp implements IExportcsvService {

    private static final String FILE_TYPE = "text/csv" ;
    @Override
    public void export(List<Question> questions, HttpServletResponse response) {
        // Set the content type of the response to "text/csv"
        response.setContentType(FILE_TYPE);
        // Set the Content-Disposition header to specify the filename of the CSV file
        response.setHeader("Content-Disposition", "attachment; filename=\"questions.csv\"");

        try (
                // Open a new Writer with the HttpServletResponse's output stream
                Writer writer= new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))
        ) {
            // Create a new CSVPrinter object with the Writer and CSVFormat.DEFAULT, including a header row
            @SuppressWarnings("resource")
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("questions and answers"));

            // Loop through each Question object in the list
            for (Question question : questions) {
                // Print a new record to the CSV file with the question content and answer separated by a semicolon
                printer.printRecord(question.getContent()+" ; "+ question.getAnswer());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






}

