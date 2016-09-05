package pengyi.socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by pengyi on 2016/3/10.
 */
public class TcpClient {

    Socket s = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    private boolean bConnected = false;

    public TcpClient(String host) {

        int port = 8888;
        boolean used = true;
        while (used) {

            try {
                s = new Socket(host, port);
                dos = new DataOutputStream(s.getOutputStream());
                dis = new DataInputStream(s.getInputStream());
                used = false;
                bConnected = true;
            } catch (BindException e) {
                port++;
            } catch (UnknownHostException e) {
                used = false;
                e.printStackTrace();
            } catch (IOException e) {
                used = false;
                e.printStackTrace();
            }


        }

    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (bConnected) {
                        String str = dis.readUTF();
                        System.out.println(str);
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (EOFException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void disconnect() {
        try {
            bConnected = false;
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(String str) {
        try {
            dos.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
