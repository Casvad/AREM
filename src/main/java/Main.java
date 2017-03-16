/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 2105534
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        //Integer port = new Integer(System.getenv("PORT"));
        try {
            //System.out.println(port);
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: ");//+port);
            System.exit(1);
        }
        
        // Concurrente
        Socket clientSocket = null;
        ExecutorService ex = Executors.newFixedThreadPool(10);
        GetHttp tarea;    
        while(true){
            clientSocket = serverSocket.accept();
            tarea = new GetHttp(clientSocket);
            ex.execute(tarea);
        }
    }
}

