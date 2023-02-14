package com.izicapinternship.izicapinternship.Service;

import com.izicapinternship.izicapinternship.Entity.Question;

import java.util.List;

public interface QuestionService {
    String AddQuestion(String question) ;

    List<Question> listAllQuestion() ;

}
