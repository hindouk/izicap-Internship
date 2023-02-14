package com.izicapinternship.izicapinternship.Repository;


import com.izicapinternship.izicapinternship.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//to perform database operations (chatgpt_db)
@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {


}
