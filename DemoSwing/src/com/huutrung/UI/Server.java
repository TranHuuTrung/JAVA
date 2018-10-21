package com.huutrung.UI;

import java.io.*;
import java.net.*;

/**
 *
 * @author HB
 */
public class Server {

    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(9876);// Tao cong 9999 de server lang nghe
            System.out.println("sever duoc tao.....");
            while (true)// Cho client ket noi
            {
                // Su dung multithread
                // Khi co 1 client gui yeu cau toi thi se tao ra 1 thread phuc vu client do
                new ThreadSocket(ss.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
