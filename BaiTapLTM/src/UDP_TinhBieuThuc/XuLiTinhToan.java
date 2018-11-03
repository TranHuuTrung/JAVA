package UDP_TinhBieuThuc;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;

public class XuLiTinhToan {
	static List<String> operator = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "sin", "cos", "log", "tan"));
	static List<String> oneOperator = new ArrayList<>(Arrays.asList("sin", "cos", "tan"));
	static List<String> bracket = new ArrayList<>(Arrays.asList("(",")"));
	static List<String> notOperand = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "sin", "cos", "tan","log", "(", ")"));
	static Stack<String> stack = new Stack<>();
	static List<String> dsOperator = new ArrayList<>(Arrays.asList("+", "-", "*", "/")); // kiem tra toan tu
	//ham check do uu tien
	public static int priorityOf(String operator) {
		switch (operator) {
		case "+" : case "-":
			return 0;
		case "*": case "/":
			return 1;
		case "sin": case "cos": case "tan": case "log": 
			return 2;
		}
		return -1;
	}
	// tinh toan phep toan co hai so hang
	public static float calc(float operand1, float operand2, String operator) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		default:
			break;
		}
		return 1;
	}
	//return operator(operand)
    private static float calc(float operand, String operator) {
        switch (operator) {
            case "sin":
                return (float) Math.sin(operand);
            case "cos":
            	return (float) Math.cos(operand);
            case "tan":
            	return (float) Math.tan(operand);
            case "log":
            	return (float) Math.log(operand);
        }
        return 1;
    }
    //chuan hoa xau, xoa ki tu dau cuoi, moi token cach nhau 1 dau cach
    public static String refine(String exp) {
    	StringBuilder strBuilder = new StringBuilder(exp);
    	for (int i = 0; i < strBuilder.length(); i++) {
			if(notOperand.indexOf(strBuilder.charAt(i)+"") != -1) {
				strBuilder.insert(i, " ").insert(i+2, " ");
				i+= 2;
			}
		}
    	String res = strBuilder.toString();
    	res = res.trim().replaceAll(" +", " ");
    	return res;
    }
    //tim cac toan tu co trong bieu thuc
    public static String cacToanTu(String exp) {
    	String strBanDau = refine(exp);
    	String dsToanTu = "";
    	String[] listStrBanDau = strBanDau.split(" ");
    	for (int i = 0; i < listStrBanDau.length; i++) {
			if(dsOperator.indexOf(listStrBanDau[i]) != -1) {
				if(dsToanTu.indexOf(listStrBanDau[i]) != -1) {
					continue;
				}else {
					dsToanTu+= listStrBanDau[i];
					dsToanTu+= " ";
				}
			}
		}
    	return dsToanTu;
    }
    //infix to postfix
    public static String toPostFix(String exp) {
        String postFix = "";
        stack.clear();
        String token = "";
        exp += " ";
        for(int i = 0 ; i < exp.length(); i++) {
            if (exp.charAt(i) == ' ') {
                //is end of token
                if (notOperand.indexOf(token) == -1) {
                    //is operand, append it to postFix
                    postFix += token;
                    postFix += " ";
                    token = "";
                }
                else if (token.equals("(")) {
                    stack.push(token);
                    token = "";
                }
                else if (token.equals(")")) {
                    String element = stack.pop();
                    while (!element.equals("(")) {
                        postFix += element;
                        postFix += " ";
                        element = stack.pop();
                    }
                    token = "";
                }
                else {
                    //is operator
                    if (stack.size() > 0) {
                        String elementAtTop = stack.peek();
                        while (operator.indexOf(elementAtTop) != -1 && priorityOf(elementAtTop) >= priorityOf(token)) {
                            String element = stack.pop();
                            postFix += element;
                            postFix += " ";
                            if (stack.size() > 0)
                                elementAtTop = stack.peek();
                            else break;
                        }
                    }
                    stack.push(token);
                    token = "";
                }
            }
            else token += exp.charAt(i);
        }
 
        while (stack.size() != 0) {
            postFix += stack.pop();
            postFix += " ";
        }
        return postFix;
    }
  //calculate postix expression
    public static float calculatePostfix(String postFix) {
        stack.clear();
        String token = "";
        for(int i = 0; i < postFix.length(); i++) {
            char c = postFix.charAt(i);
            if (c == ' ') {
                //is end of token
                if (notOperand.indexOf(token) == -1) {
                    //is operand, push it to stack
                    stack.push(token);
                    token = "";
                }
                else {
                    //is operator, pop 2 operand from stack and calculate
                    if (oneOperator.indexOf(token) != -1) {
                        //la toan tu 1 ngoi
                        float operand = Float.parseFloat(stack.pop());
                        float res = calc(operand, token);
                        stack.push(String.valueOf(res));
                        token = "";
                    }
                    else {
                        float operand1 = Float.parseFloat(stack.pop());
                        float operand2 = Float.parseFloat(stack.pop());
                        float res = calc(operand2, operand1, token);
                        stack.push(String.valueOf(res));
                        token = "";
                    }
 
                }
            }
            else
                token += c;
        }
        float result = Float.parseFloat(stack.pop());
        return result;
    }
    //tinh toan
    static float caculate(String infix) {
   	    String resInfix = "";
   	    resInfix = refine(infix);
        String postFix = toPostFix(resInfix);
        Float value = calculatePostfix(postFix);
		return value;
   }
   
    
}
