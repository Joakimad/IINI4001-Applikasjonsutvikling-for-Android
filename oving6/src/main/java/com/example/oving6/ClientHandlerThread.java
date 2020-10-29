package com.example.oving6;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandlerThread extends Thread {

    private final static String TAG = "Handler";
    private Socket s;
    PrintWriter out = null;
    BufferedReader in = null;

    public ClientHandlerThread(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            Log.v(TAG, "client connected...");
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            out.println("Welcome client..."); //send text to client

            String fromClient = in.readLine(); //receive text from client
            Log.i(TAG, "Message from client: " + fromClient);

            if (fromClient.contains("-")) {
                String[] numbers = fromClient.split("-");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                int sum = num1 + num2;
                Log.i(TAG, "Sum: " + sum);
                out.println("" + sum);
            }

        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        }
    }
}