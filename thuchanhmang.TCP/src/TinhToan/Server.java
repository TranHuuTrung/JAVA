package TinhToan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
		public static final String IP = "127.0.0.1";
		public static final int PORT = 2001;
		
		private ServerSocket serverSocket;
		
		public Server(String host, int port) throws IOException {
			try {
				serverSocket = new ServerSocket(port);
				System.out.println("Server is running in port "+ port);
				while (true) {
					try {
						SocketThread socket = new SocketThread(serverSocket.accept());
						new Thread(socket).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException ie) {
				Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ie);
			}finally {
				if( serverSocket != null) {
					serverSocket.close();
				}
			}
		}
		public static void main(String[] args) throws IOException {
			new Server(IP, PORT);
		}
}
class SocketThread implements Runnable{
	private Socket s;
	public SocketThread(Socket s) {
		this.s = s;
	}
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			while (true) {
				String receive = dis.readUTF();
				System.out.println(TinhBieuThuc.toPostFix(receive));
				float result = TinhBieuThuc.caculate(receive);
				dos.writeUTF(String.valueOf(result));
			}
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
class TinhBieuThuc {
	static List<String> operator = new ArrayList<>(Arrays.asList("+", "-", "*", "/" , "sin", "cos", "tan", "log", "^"));
    static List<String> oneArgOperator = new ArrayList<>(Arrays.asList("sin", "cos", "tan"));
    static List<String> bracket = new ArrayList<>(Arrays.asList("(", ")"));
    static List<String> notOperand = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "sin","cos", "tan","log", "^", "(", ")"));
    static Stack<String> stack = new Stack<>();
    private static int priorityOf(String operator) {
        switch (operator) {
            case "+": case "-":
                return 0;
            case "*": case "/": case "^":
                return 1;
            case "sin": case "cos": case "tan": case "log": 
                return 2;
        }
        return -1;
    }
    //return operand1 + operand2
    private static float calc(float operand1, float operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "^":
            	return (float) Math.pow(operand1, operand2);
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
    //chuan hoa xau, xoa ki tu trang o dau va cuoi, moi token cach nhau bang mot dau cach
    private static String refine(String exp) {
        StringBuilder strBuilder = new StringBuilder(exp);
        for(int i = 0; i < strBuilder.length(); i++) {
            if (notOperand.indexOf(strBuilder.charAt(i) + "") != -1) {
                strBuilder.insert(i, " ").insert(i + 2, " ");
                i += 2;
            }
        }
        String res = strBuilder.toString();
        res = res.trim().replaceAll(" +", " ");
        return res;
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
    private static float calculatePostfix(String postFix) {
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
                    if (oneArgOperator.indexOf(token) != -1) {
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
    
    static float caculate(String infix) {
    	 String resInfix = "";
    	 resInfix = refine(infix);
         String postFix = toPostFix(resInfix);
         Float value = calculatePostfix(postFix);
		return value;
    }
 
}
