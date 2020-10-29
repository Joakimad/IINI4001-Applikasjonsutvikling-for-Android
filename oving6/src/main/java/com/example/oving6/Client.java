package com.example.oving6;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private final static String TAG = "O6_Client";
    private final static String IP = "10.0.2.2";
    private final static int PORT = 12345;

    private MainActivity activity;
    private int num1;
    private int num2;

    public Client(MainActivity activity, int num1, int num2) {
        this.activity = activity;
        this.num1 = num1;
        this.num2 = num2;
    }

    public void run() {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(IP, PORT);
            Log.v(TAG, "Connected to server" + socket.toString());

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String res = in.readLine();
            Log.i(TAG, res);

            out.println("" + num1 + "-" + num2);

            // Wait for server response
            String fromServer = in.readLine(); //receive text from server
            Log.i(TAG, "Message from Server: " + fromServer);

            activity.showResponseFromServer(fromServer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {//close socket!!
            try {
                Log.i(TAG, "Closing down client...");
                assert out != null;
                out.close();
                assert in != null;
                in.close();
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}