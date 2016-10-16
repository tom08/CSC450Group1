package com.CSC450.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UpdateClient {
    private String address;
    private BufferedReader in;
    private PrintWriter out;

    public void UpdateClient(){
        address = "127.0.0.1";
    }

    public void connectToServer() throws IOException {

        Socket socket = new Socket(address, 12000);
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("..");
        String msg = in.readLine();
        while(msg != null && msg != "..") {
            if(msg.equals(".."))
                out.println("..");
            msg = in.readLine();
        }
    }
}
