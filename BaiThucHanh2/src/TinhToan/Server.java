package TinhToan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 2808;

    private DatagramSocket datagramSocket;

    public Server(int port) {
        try {
            datagramSocket = new DatagramSocket(port);
            System.out.println("Server is running in port " + port + " ...");
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                InetAddress addressClient = receivePacket.getAddress();
                int portClient = receivePacket.getPort();
                String request = new String(receivePacket.getData());
                System.out.println("Client send: " + request);
                byte[] sendData = TinhBieuThuc.calculator(request).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addressClient, portClient);
                datagramSocket.send(sendPacket);
            }

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server(PORT);
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
        String[] eMath = processString(sMath);
        eMath = postfix(eMath);
        return valueMath(eMath);
	}
    
}
