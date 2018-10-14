package TinhToan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 2808;

    private ServerSocket serverSocket;

    public Server(String host, int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running in port " + port + " ...");
            while (true) {
                SocketThread socket = new SocketThread(serverSocket.accept());
                new Thread(socket).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server(IP, PORT);
    }
}

class SocketThread implements Runnable {

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
                System.out.println(receive);
                dos.writeUTF(TinhBieuThuc.calculator(receive));
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class TinhBieuThuc {
	private static int priority(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return 0;
    }
	
	private static boolean isOperator(char c) {
        char operator[] = { '+', '-', '*', '/', ')', '(' };
        for (int i = 0; i < operator.length; i++) {
        	if (c == operator[i]) return true;
        }
        return false;
    }
	
	// change string to operator array
	private static String[] processString(String sMath2) {
		String sMath = "";
        for (int i = 0; i < sMath2.length(); i++) {
        	if (sMath2.charAt(i) != ' ') sMath += sMath2.charAt(i);
        }
		
		
		String s1 = "", elementMath[] = null;
        sMath = sMath.trim();
        sMath = sMath.replaceAll("s+"," ");
        boolean preIsOpe = false;
        for (int i = 0; i < sMath.length(); i++) {
            char c = sMath.charAt(i);
            if (!isOperator(c)) {
            	s1 = s1 + c;
            	preIsOpe = true;
            }
            else {
            	if (preIsOpe) s1 += " ";
            	preIsOpe = false;
            	s1 = s1 + c + " ";
            }
        }
        s1 = s1.trim();
        s1 = s1.replaceAll("s+"," ");
        elementMath = s1.split(" "); // split s1 to operators of array elementMath
        return elementMath;
    }
	
	private static String[] postfix(String[] elementMath) {
        String s1 = "", E[];
        Stack <String> S = new Stack <String>();
        
        for (int i = 0; i < elementMath.length; i++) {
        	char c = elementMath[i].charAt(0);
 
            if (!isOperator(c)) s1 = s1 + " " + elementMath[i];
            else {
                if (c == '(') S.push(elementMath[i]);
                else{
                    if (c == ')') {
                        char c1;
                        do {
                            c1 = S.peek().charAt(0);
                            if (c1 != '(') s1 = s1 + " " + S.peek();
                            S.pop();
                        } while (c1 != '(');
                    }
                    else{
                        while (!S.isEmpty() && priority(S.peek().charAt(0)) >= priority(c)) {
                            s1 = s1 + " " + S.peek();
                            S.pop();
                        }
                        S.push(elementMath[i]);
                    }
                }
            }
        }
        while (!S.isEmpty()) {
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        s1 = s1.trim();
        E = s1.split(" ");
        return E;
    }
	
	private static String valueMath(String[] elementMath) {
        Stack <String> S = new Stack<String>();
        for (int i = 0; i < elementMath.length; i++){
            char c = elementMath[i].charAt(0);
            if (!isOperator(c)) S.push(elementMath[i]);
            else{
                double num = 0f;
                double num1 = Float.parseFloat(S.pop());
                double num2 = Float.parseFloat(S.pop());
                switch (c) {
                    case '+' : num = num2 + num1; break;
                    case '-' : num = num2 - num1; break;
                    case '*' : num = num2 * num1; break;
                    case '/' : num = num2 / num1; break;
                    default:
                        break;
                }
                S.push(Double.toString(num));
            }
        }
         return S.pop();
    }
	
	public static String calculator(String sMath) {
        //String sMath = "((78-910)+11)/12";
        if (checkString(sMath) == false) return "Biểu thức không đúng!";
        String[] eMath = processString(sMath);
        eMath = postfix(eMath);
        return valueMath(eMath);
	}

	private static boolean checkString(String str) {
		for (int i = 0; i < str.length(); i++)
		{
			if (!(str.charAt(i) == ' ' ||
			   (str.charAt(i) >= '0' && str.charAt(i) <= '9') ||
			   isOperator(str.charAt(i))))
			{
				return false;
			}
		}
		return true;
	}
}