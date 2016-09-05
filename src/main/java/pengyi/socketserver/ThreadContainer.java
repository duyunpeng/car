package pengyi.socketserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pengyi on 2016/1/14.
 */
class ThreadContainer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Thread thread;

    @Autowired
    private TcpService tcpService;

    public void start() {
        thread = new Thread(tcpService);
        thread.start();
    }

    public void stop(){
        thread.interrupt();
    }
}