package com.izicapinternship.izicapinternship.Controller;

import com.izicapinternship.izicapinternship.Entity.Question;
import com.izicapinternship.izicapinternship.Service.IExportcsvService;
import com.izicapinternship.izicapinternship.Service.QuestionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ChatgptController {

    //instance of the service interface QuestionService
    @Autowired
    private QuestionService questionService ;

    //instance of the service interface IExportcsvService
    @Autowired
    private IExportcsvService exportService ;

    //"/question" API route that receives a string in the request body
    // passes it to a questionService to add the question then returns the response as a string.
    @PostMapping("/question")
    public String  CallChatGptApi(@RequestBody String question){
        String response =  questionService.AddQuestion(question);
        return response ;
    }

    //The method retrieves a list of Question objects using a questionService instance
    //calls an export method on an exportService instance to export the list of questions as a CSV file.
    @GetMapping("/export")
    public void exportCSV(HttpServletResponse response) throws IOException {
        List<Question> questions = questionService.listAllQuestion() ;
        exportService.export(questions , response);
    }

}
