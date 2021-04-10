package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GraphiccController {
	@Autowired
	GraphiccRepository graph;
	
	// POST outputs the input into the table
	// GET returns that output
	
	// Takes the input equation, creates the variable, saves it to the graph,
	// and then returns the equation
	@RequestMapping(method = RequestMethod.POST, path ="/equationIn/{equation}")
	Graphicc addEquation(@PathVariable String equation) {
		Graphicc temp = new Graphicc(equation);
		graph.save(temp);
		return temp;
	}
	
	@RequestMapping(method = RequestMethod.GET, path ="/equationOut/{equation}")
	Graphicc returnEquation(@PathVariable String equation) {
		List<Graphicc> graphs = graph.findAll();
		for (Graphicc g : graphs) {
			if (g.equation.equals(equation)) {
				return g;
			}
		}
		return null;
	}
	
}
