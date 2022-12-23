import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketClient {
    private Socket client;

    /*
        Method closes open socket
    */
    private void closeSocket() {
        try {
            if (client.isConnected()) {
                client.close();
            }
        }
        catch (IOException e) {
            System.out.println("Got exception " + e + " when the socket was closed");
        }
    }

    /*
        Method open new socket
     */
    private void openSocket(String host, int timeout) {
        try {
            client = new Socket(host, 80);
            client.setSoTimeout(timeout);
        }
        catch (IOException e) {
            closeSocket();
        }
    }

    public void request(String path, String host, int timeout, SocketCallback callback) {
        try {
            openSocket(host, timeout);
            if(client.isConnected()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
                pw.println("GET " + path + " HTTP/1.1");
                pw.println("Host: " + host);
                pw.println("Connection: close");
                pw.println();
                pw.flush();

                callback.onSuccess(in);
            }
            else {
                callback.onError(new SocketException("Unable to connect to socket"));
            }
        }
        catch (Exception e) {
            callback.onError(e);
        }
        finally {
            closeSocket();
        }
    }

    interface SocketCallback {
        void onSuccess(BufferedReader in);
        void onError(Exception e);
    }
}
