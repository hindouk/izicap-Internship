package com.izicapinternship.izicapinternship.Service;

import com.izicapinternship.izicapinternship.Entity.Question;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface IExportcsvService {
    void export(List<Question> questions, HttpServletResponse response ) ;

}
