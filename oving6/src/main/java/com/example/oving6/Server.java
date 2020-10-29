package com.example.oving6;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final static String TAG = "O6_Server";
    private final static int PORT = 12345;

    public void run() {
        ServerSocket ss = null;
        Socket s = null;

        try {
            Log.i(TAG, "start server....");
            ss = new ServerSocket(PORT);
            Log.i(TAG, "serversocket created, wait for client....");

            while (true) {
                s = ss.accept();
                new ClientHandlerThread(s).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ss != null;
                ss.close();
                assert s != null;
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
