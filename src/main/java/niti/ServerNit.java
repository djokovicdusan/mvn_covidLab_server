/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import konstante.Konstante;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dule Djo
 */
public class ServerNit extends Thread {

    private List<KlijentNit> klijenti;
    private ServerSocket ss;

    public ServerNit() {
        klijenti = new ArrayList<>();
        initializeServer();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {

                System.out.println("Čekam klijenta...");
                Socket socket = ss.accept();
                System.out.println("Novi klijent");
                KlijentNit clientThread = new KlijentNit(socket, this);
                clientThread.start();
                klijenti.add(clientThread);
            } catch (IOException ex) {
                Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<KlijentNit> getKlijenti() {
        return klijenti;
    }

    private void initializeServer() {
        try {
            ss = new ServerSocket(Konstante.PORT);
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {
        try {
            System.out.println("Server se gasi");
            for (KlijentNit cl : klijenti) {
                cl.diskonektujAdministratora();
                klijenti.remove(cl);
            }
            ss.close();
            this.interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
