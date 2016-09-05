import org.testng.annotations.Test;
import pengyi.core.util.CoreDateUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pengyi on 2016/3/10.
 */
public class TcpServiceTest {


    @Test
    public void test(){
        try {
            Socket socket = new Socket("211.149.234.36", 8888);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.FEBRUARY, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date fast = calendar.getTime();
        Calendar calendar_1 = Calendar.getInstance();
        calendar_1.set(2016, Calendar.FEBRUARY, 1);
        calendar_1.set(Calendar.DAY_OF_MONTH, calendar_1.getActualMaximum(Calendar.DAY_OF_MONTH));


        Date end = calendar_1.getTime();

        Date data = CoreDateUtils.parseDate("2016-05","yyyy-MM");

        System.out.println("");
    }
}
