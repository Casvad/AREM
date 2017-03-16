
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */

    
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author estudiante
 */
public class GetHttp implements Runnable{
    Socket clientSocket;
    
    public GetHttp(Socket clientSocket) {
        this.clientSocket=clientSocket;
    }
            
    @Override
    public void run() {
        try {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                String raiz = "/raiz";
                String recurso = in.readLine().trim().split(" ")[1];
                System.out.println("Llamo al recurso: "+recurso);
                //byte[] outputLine = Files.readAllBytes(Paths.get(raiz+recurso));
                byte[] outputLine = Files.readAllBytes(Paths.get("raiz/index.html"));
                clientSocket.getOutputStream().write(outputLine);
            }
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("Error cargando la pagina");
        }
    }
}


