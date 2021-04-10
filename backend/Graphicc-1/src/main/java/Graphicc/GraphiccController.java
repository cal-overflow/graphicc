package Graphicc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GraphiccController {
	@Autowired
	GraphiccRepository graph;
	
	// POST method that takes an equation as input. Then, if the equation has already been computed, it outputs that equation
	// from the database. If the equation is new, it computes that data and adds it to the database.
	@RequestMapping(method = RequestMethod.POST, path ="/equationIn/{equation}")
	String stringOut(@PathVariable String equation) {
		List<Graphicc> graphs = graph.findAll();
		for (Graphicc g : graphs) {
			if (g.equation.equals(equation)) {
				return g.points;
			}
		}
		Graphicc input = new Graphicc(equation);
		graph.save(input);
		return input.points;
	}

	
}
