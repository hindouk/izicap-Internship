package com.izicapinternship.izicapinternship.Service.ServiceImp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.izicapinternship.izicapinternship.Entity.Question;
import com.izicapinternship.izicapinternship.Repository.QuestionRepository;
import com.izicapinternship.izicapinternship.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {


    private static final String API_KEY = "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";

    private static final String CHATGPT_API_URL = "https://api.openai.com/v1/completions";

    // Insert dependency on QuestionRepository object to perform database operations
    @Autowired
    private QuestionRepository questionRepository;

    // Add a question to the database and return the answer from the OpenAI API
    @Override
    public String AddQuestion(String question) {
        String answer = Chatgpt_answer(question);
        saveQuestionToDatabase(question, answer);
        return answer;
    }

    // Returns a list of all questions stored in the database
    @Override
    public List<Question> listAllQuestion() {
        return questionRepository.findAll();
    }

    // Makes a request to the OpenAI API to get an answer to a given question
    private String Chatgpt_answer(String question) {
        String answer = "";
        try {
            // Create a new URL for the OpenAI API
            URL url = new URL(CHATGPT_API_URL);

            // Open an HTTPS connection to the OpenAI API
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            // Set authentication token for OpenAI API
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setDoOutput(true);

            // Create a JSON string containing request parameters for the OpenAI API
            String requestBody = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"max_tokens\": 4000, \"temperature\": 1.0}";

            // Écrire la chaîne JSON dans la sortie de la connexion HTTPS
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(requestBody);
            out.flush();
            out.close();

            // Read the OpenAI API response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            String res = response.toString();
            in.close();

            // Parse OpenAI API JSON response to extract response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(res);
            String text = root.path("choices").get(0).path("text").asText();
            answer = text;

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // Return response from OpenAI API
        return answer;
    }

    // Save a question and its answer in the database
    void saveQuestionToDatabase(String question, String answer) {
        Question currentQuestion = new Question();
        currentQuestion.setContent(question);
        currentQuestion.setAnswer(answer);
        questionRepository.save(currentQuestion) ;
    }



}
