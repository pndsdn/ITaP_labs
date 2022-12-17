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
    private void openSocket(String host) {
        try {
            client = new Socket(host, 80);
        }
        catch (IOException e) {
            closeSocket();
        }
    }

    public void request(String path, String host, SocketCallback callback) {
        try {
            openSocket(host);
            if(client.isConnected()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                requestHTTP1_1(client.getOutputStream(), path, host);
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

    private static void requestHTTP1_1(OutputStream outputStream, String path, String host) {
        PrintWriter out = new PrintWriter(outputStream, true);
        out.println("GET " + path+ " HTTP/1.1");
        out.println("Host: " + host);
        out.println("Connection: close");
        out.println();
        out.flush();
    }

    interface SocketCallback {
        void onSuccess(BufferedReader in);
        void onError(Exception e);
    }
}
