package Graphicc;

import java.util.Stack;

public class Evaluator {
	String equation;
	char Variable = ' ';
	String outputValues;
	Stack<String> eval;
	
	/**
	 * Default constructor that does nothing
	 */
	public Evaluator() {
	}
	
	public Evaluator(String equation) {
		String newEquation = "";
        for(int i = 0; i < equation.length(); i++) {
            char a = equation.charAt(i);
            //~ == divide
            if(a == '~') {
                newEquation += '/';
            }
            //@ == exponential
            else if(a == '@') {
                newEquation += '^';
            }
            //_ == modulo
            else if(a == '_') {
                newEquation += '%';
            }else {
                newEquation += a;
            }
        }
        equation = newEquation;
		eval = new Stack<String>();
		this.equation = equation;
		parseVariable();
		paranthesize();
		outputValues = "{";
	}
	
	/**
	 * Attempts to find a variable in the equation.
	 */
	private void parseVariable() {
		for(int i = 0; i < equation.length(); i++) {
			char a = equation.charAt(i);
			if(a != ' ' && !isNumber(a) && !isOperator(a) && a != '(' && a != ')') {
				Variable = a;
			}
		}
		//If we fail to define the Variable, we set it to null, as our equation has no variable
		if(Variable == ' ') {
			Variable = 0;
		}
	}
	
	/**
	 * Creates a new equation from the given one, that has correct paranthesization following the rules of PEMDAS.
	 */
	private void paranthesize() {
		String newEquation = "";
		for(int i = equation.length() - 1; i >= 0; i--) {
			char a = equation.charAt(i);
			if(a == '^') {
				String leftVal[] = findLeftValue(equation, i);
				String rightVal[] = findRightValue(newEquation, -1);
				int begin = Integer.valueOf(rightVal[1]) + 1;
				if(begin >= newEquation.length()) {
					newEquation = "";
				}else {
					newEquation = newEquation.substring(begin, newEquation.length());
				}
				i = Integer.valueOf(leftVal[1]) - 1;
				String finVal = leftVal[0] + a + rightVal[0];
				newEquation = "( " + finVal + " )" + newEquation;
			}else {
				newEquation = a + newEquation;
			}
		}
		String finalEquation = "";
		for(int i = 0; i < newEquation.length(); i++) {
			char a = newEquation.charAt(i);
			if(a == '*' || a == '%' || a == '/') {
				String leftVal[] = findLeftValue(finalEquation, finalEquation.length());
				String rightVal[] = findRightValue(newEquation, i);
				int end = Integer.valueOf(leftVal[1]) + 1;
				if(end == 0 || end == 1) {
					finalEquation = "";
				}else if(end < finalEquation.length()) {
					finalEquation = finalEquation.substring(0, end);
				}
				
				i = Integer.valueOf(rightVal[1]);
				String finVal = "(" + leftVal[0] + a + rightVal[0] + ")";
				finalEquation += finVal;
			}else {
				finalEquation += a;
			}
		}
		equation = finalEquation;
	}
	
	private String[] findLeftValue(String curr, int startIndex) {
		//Find the left and rightValues, and put them into the newEquation accordingly
		String leftValue = "";
		int numParan = 0;
		//get the value for leftValue
		int index = startIndex;
		for(int j = startIndex - 1; j >= 0; j--) {
			char b = curr.charAt(j);
			if(isNumber(b) && numParan == 0) {
				String number = "";
				while(j >= 0) {
					b = curr.charAt(j);
					if(isNumber(b)) {
						number = b + number;
						j--;
					}else {
						break;
					}
				}
				leftValue = number + leftValue;
				index = j;
				break;
			}else if(b == ')') {
				numParan++;
				leftValue = b + leftValue;
			}else if(b == '(') {
				numParan--;
				leftValue = b + leftValue;
				if(numParan == 0) {
					index = j;
					break;
				}
			}else if(b == Variable && numParan == 0) {
				index = j;
				leftValue = b + leftValue;
				break;
			}else {
				leftValue = b + leftValue;
			}
		}
		if(index == startIndex) {
			index = -1;
		}
		String[] ret = {leftValue, index + ""};
		return ret;
	}
	
	private String[] findRightValue(String curr, int startIndex) {
		String rightValue = "";
		int numParan = 0;
		int index = 0;
		for(int i = startIndex + 1; i < curr.length(); i++) {
			char b = curr.charAt(i);
			if(isNumber(b) && numParan == 0) {
				String number = "";
				while(i < curr.length()) {
					b = curr.charAt(i);
					if(isNumber(b)) {
						number += b;
						i++;
					}else {
						break;
					}
				}
				index = i;
				rightValue += number;
				break;
			}else if(b == '(') {
				numParan++;
				rightValue += b;
			}else if(b == ')') {
				numParan--;
				rightValue += b;
				if(numParan == 0) {
					index = i;
					break;
				}
			}else if(b == Variable && numParan == 0){
				index = i;
				rightValue += b;
				break;
			}else{
				rightValue += b;
			}
		}
		String[] ret = {rightValue, index + ""};
		return ret;
	}
	
	public String evaluate() {
		for(int i = -100; i <= 100; i++) {
			outputValues += "\"" + i + "\"" + ":" + evaluate(i);
			if(i != 100) {
				outputValues += ", ";
			}
		}
		outputValues += "}";
		return outputValues;
	}
	
	private String evaluate(int replacement) {
		int paranOnStack = 0;
		for(int i = 0; i < equation.length(); i++) {
			char a = equation.charAt(i);
			if(a == ' ') {
				
			}else if(isOperator(a)) {
				//put the operator onto the stack
				if(a == '-') {
					i++;
					if(i < equation.length() && isNumber(equation.charAt(i))){
						char b = equation.charAt(i);
						String number = "-";
						while(i < equation.length()) {
							b = equation.charAt(i);
							if(isNumber(b)) {
								number += b;
								i++;
							}else {
								i--;
								break;
							}
						}
						eval.push(number);
					}else {
						String op = "" + a;
						eval.push(op);
						i--;
					}
				}else {
					String op = "" + a;
					eval.push(op);
				}
			}else if(isNumber(a)) {
				String number = "";
				while(i < equation.length()) {
					a = equation.charAt(i);
					if(isNumber(a)) {
						number += a;
						i++;
					}else {
						i--;
						break;
					}
				}
				if(eval.size() != 0 && isOperator(eval.peek().charAt(0))) {
					String op = eval.pop();
					String numPrevious = eval.pop();
					eval.push(evaluate(numPrevious, op, number));
					
				}else {
					eval.push(number);
				}
				//put number onto stack, and peek before hand to see if the previous item was an operator
				//if it was an operator previously, pop it and the next thing off the stack and evaluate that, and put it back on the stack
			}else if(a == Variable) {
				//put the variables value onto the stack, or in this case replacement
				String num = "" + replacement;
				eval.push(num);
				attemptEval();
			}else if(a == '(') {
				//put onto the stack a '(', and increase paranOnStack
				String lParan = "" + '(';
				paranOnStack++;
				eval.push(lParan);
			}else if(a == ')') {
				//put onto the stack a ')', and decrease the paranOnStack, and throw an exception if it goes negative
				//Pop from the stack until you find the first '(', and evaluate the equation in between
				paranOnStack--;
				if(paranOnStack < 0) {
					//TO DO: throw exception that it went negative
				}
				String number = eval.pop();
				eval.pop(); //pop off the '('
				eval.push(number);
				attemptEval(); //We may be able to evaluate more of the stack now
			}
		}
		if(eval.size() == 1) {
			return eval.pop();
		}else {
			return "null";
		}
	}
	
	private String evaluate(String num1S, String operator, String num2S) {
		char op = operator.charAt(0);
		int value = 0;
		int num1 = Integer.valueOf(num1S);
		int num2 = Integer.valueOf(num2S);
		switch(op) {
		case('+'):
			value = num1 + num2;
			break;
		case('-'):
			value = num1 - num2;
			break;
		case('*'):
			value = num1 * num2;
			break;
		case('/'):
			value = num1 / num2;
			break;
		case('%'):
			value = num1 % num2;
			break;
		case('^'):
			value = (int) Math.pow(num1, num2);
			break;
		}
		return "" + value;
	}
	
	private void attemptEval() {
		while(eval.size() != 0 && isNumber(eval.peek().charAt(0))) {
			String number = eval.pop();
			if(eval.size() != 0 && isOperator(eval.peek().charAt(0))) {
				String operator = eval.pop();
				String num1 = eval.pop();
				eval.push(evaluate(num1, operator, number));
			}else {
				eval.push(number);
				break;
			}
		}
	}
	
	private boolean isNumber(char a) {
		if(Character.isDigit(a) || a == '-') {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isOperator(char a) {
		switch(a) {
		case('+'):
			return true;
		case('-'):
			return true;
		case('*'):
			return true;
		case('/'):
			return true;
		case('^'):
			return true;
		case('%'):
			return true;
		default:
			return false;
		}
	}
}

