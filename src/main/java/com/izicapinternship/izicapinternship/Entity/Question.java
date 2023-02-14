package com.izicapinternship.izicapinternship.Entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "questionContent",nullable = false, columnDefinition = "VARCHAR(1000)")
    private String content;

    @Column(name = "answerContent",nullable = false, columnDefinition = "VARCHAR(1000)")
    private String answer;

	//Constructors
    public Question(String content, String answer) {
        this.content = content;
        this.answer = answer;
    }

	public Question() {
		// TODO Auto-generated constructor stub
	}

	//Getters and setters
	public Question(String s) {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
}
