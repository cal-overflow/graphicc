package com.example.demo;

//import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Entity
@Table(name = "graphicc")
public class Graphicc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column
	String equation;
	
	@Column
	String points;
	
	public Graphicc() {}
	
	public Graphicc(String equation) {
		this.equation = equation;
		
		
		
		
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getEquation() {
		return equation;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEquation(String equation) {
		this.equation = equation;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}

}
