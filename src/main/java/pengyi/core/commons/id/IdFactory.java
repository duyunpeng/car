package pengyi.core.commons.id;

import pengyi.application.sequence.ISequenceAppService;
import pengyi.core.redis.RedisService;
import pengyi.core.util.CoreDateUtils;
import pengyi.core.util.CoreStringUtils;

import java.util.Date;

/**
 * Created by YJH on 2016/3/22.
 */
public class IdFactory {

    private ISequenceAppService sequenceAppService;

    private String key;

    private String prefix;

    private int suffixLength;

    private Date nextDay;

    private String currentDay;

    public IdFactory() {
        setNextDay();
    }

    private void setNextDay() {
        Date now = new Date();
        this.currentDay = CoreDateUtils.formatDate(now, "yyyyMMddHHmmssSSS");
        this.nextDay = CoreDateUtils.addDay(now, 1);
    }

    public synchronized String getNextId() {

        if (CoreDateUtils.isSameDay(new Date(), nextDay)) {
            setNextDay();
            sequenceAppService.reset();
        }

        StringBuilder sb = new StringBuilder(prefix);
        sb.append(currentDay)
                .append(CoreStringUtils.fillZero(sequenceAppService.getNextSequence(suffixLength), suffixLength));

        return sb.toString();
    }

    public void setSequenceAppService(ISequenceAppService sequenceAppService) {
        this.sequenceAppService = sequenceAppService;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffixLength(int suffixLength) {
        this.suffixLength = suffixLength;
    }

}
