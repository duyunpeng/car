package pengyi.socketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import pengyi.application.order.IOrderAppService;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by pengyi on 2016/3/9.
 */
public class TcpService implements Runnable {

    private ServerSocket serverSocket;
    boolean started = false;
    public static Map<String, Client> userClients = new HashMap<String, Client>();
    public static Map<String, Client> driverClients = new HashMap<String, Client>();

    public static Map<String, List<String>> userMessages = new HashMap<String, List<String>>();
    public static Map<String, List<String>> driverMessages = new HashMap<String, List<String>>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IOrderAppService orderAppService;

    @Override
    public void run() {
        int port = 8888;
        boolean used = true;
        while (used) {
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.setReuseAddress(true);
                started = true;
                used = false;
                logger.info(messageSource.getMessage("socket.open.success.message", new Object[]{port}, Locale.CHINA));
            } catch (BindException e) {
                port++;
            } catch (IOException e) {
                logger.error("socket.open.fail.message");
                e.printStackTrace();
                used = false;
            }
        }

        try {
            while (started) {
                Socket s = serverSocket.accept();
                new Thread(new Client(s, orderAppService)).start();
            }
        } catch (IOException e) {
            logger.error("socket.server.dirty.shutdown.message");
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
