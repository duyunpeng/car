package pengyi.socketserver;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pengyi.application.order.IOrderAppService;
import pengyi.core.type.UserType;
import pengyi.socketserver.model.ReceiveObj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by pengyi on 2016/3/25.
 */
public class Client implements Runnable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Socket s;
    private String phone;
    private UserType userType;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private IOrderAppService orderAppService;


    public Client(Socket s, IOrderAppService orderAppService) {
        this.s = s;
        this.orderAppService = orderAppService;
        try {
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (EOFException e) {
            logger.info("socket.shutdown.message" + phone + userType);
            close();
        } catch (IOException e) {
            logger.info("socket.connection.fail.message" + phone + userType + e.getMessage());
            close();
        }
    }

    public boolean send(String str) {
        try {
            dos.writeUTF(str.replace(" ", "").replace("\n", "").replace("\t", ""));
            logger.info("socket.server.sendMessage.success.message" + phone + userType + s.isConnected());
            return true;
        } catch (IOException e) {
            logger.info("socket.server.sendMessage.fail.message" + phone + userType + e.getMessage());
            close();
            return false;
        }
    }

    public void close() {

        try {
            if (dis != null)
                dis.close();
            if (dos != null)
                dos.close();
            if (s != null) {
                s.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (null != userType && null != phone) {
                switch (userType) {
                    case USER:
                        if (TcpService.userClients.containsKey(phone) && TcpService.userClients.get(phone) == this) {
                            TcpService.userClients.remove(phone);
                        }
                        break;
                    case DRIVER:
                        if (TcpService.driverClients.containsKey(phone) && TcpService.driverClients.get(phone) == this) {
                            TcpService.driverClients.remove(phone);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = dis.readUTF();
                if (str.length() == 24 && str.startsWith("xg")) {
                    switch (userType) {
                        case USER:
                            if (TcpService.userMessages.containsKey(phone)) {
                                List<String> messages = TcpService.userMessages.get(phone);
                                if (messages.contains(str)) {
                                    messages.remove(str);
                                }
                                TcpService.userMessages.remove(phone);
                                TcpService.userMessages.put(phone, messages);
                            }
                            break;
                        case DRIVER:
                            if (TcpService.driverMessages.containsKey(phone)) {
                                List<String> messages = TcpService.driverMessages.get(phone);
                                if (messages.contains(str)) {
                                    messages.remove(str);
                                }
                                TcpService.driverMessages.remove(phone);
                                TcpService.driverMessages.put(phone, messages);
                            }
                            break;
                    }
                } else {
                    ReceiveObj obj = JSON.parseObject(str, ReceiveObj.class);
                    phone = obj.getPhone();
                    userType = obj.getType();
                    logger.info("socket.connection.success.message" + phone + userType);
                    switch (obj.getType()) {
                        case USER:
                            if (TcpService.userClients.containsKey(phone) && TcpService.userClients.get(phone) != this) {
                                TcpService.userClients.get(phone).send("exit");
//                                TcpService.userClients.get(phone).close();
                            }
                            TcpService.userClients.put(phone, this);
                            if (TcpService.userMessages.containsKey(phone)) {
                                List<String> messages = TcpService.userMessages.get(phone);
                                for (String message : messages) {
                                    send(JSON.toJSONString(orderAppService.byOrderNumber(message)));
                                }
                            }
                            break;
                        case DRIVER:
                            if (TcpService.driverClients.containsKey(phone) && TcpService.driverClients.get(phone) != this) {
                                TcpService.driverClients.get(phone).send("exit");
//                                TcpService.driverClients.get(phone).close();
                            }
                            TcpService.driverClients.put(phone, this);
                            if (TcpService.driverMessages.containsKey(phone)) {
                                List<String> messages = TcpService.driverMessages.get(phone);
                                for (String message : messages) {
                                    send(JSON.toJSONString(orderAppService.byOrderNumber(message)));
                                }
                            }
                            break;
                    }
                }
            }
        } catch (EOFException e) {
            logger.info("socket.shutdown.message" + phone + userType);
        } catch (IOException e) {
            logger.info("socket.dirty.shutdown.message" + phone + userType + e.getMessage());
        } finally {
            close();
        }
    }
}