package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "GraphiccController")
@RestController
public class GraphiccController {
	@Autowired
	GraphiccRepository graph;
	
	// POST outputs the input into the table
	// GET returns that output
	
	// Takes the input equation, creates the variable, saves it to the graph,
	// and then returns the equation
	@RequestMapping(method = RequestMethod.POST, path ="/equation/{equation}")
	Graphicc addEquation(@PathVariable String equation) {
		Graphicc temp = new Graphicc(equation);
		graph.save(temp);
		return temp;
	}
	
}
