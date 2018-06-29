package com.lawu.chick.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.lawu.utils.DateUtil;

/**  
 * 获取小鸡产蛋倒计时
 * @author lihj
 * @date 2018年6月13日
 */
public class ChickLayUtil {
	
	public static Long getLayMin(List<String> chickEggProdTime) {
		List<Date> chickEggProdTimeVal = new ArrayList<>();
        for (String time : chickEggProdTime) {
           String datetime = DateUtil.getDateFormat(new Date(),DateUtil.DATE_DEFAULT_FORMAT) + " " + time + ":00";
            chickEggProdTimeVal.add(DateUtil.formatDate(datetime,DateUtil.DATETIME_DEFAULT_FORMAT));
        }
        Collections.sort(chickEggProdTimeVal, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                int flag = o1.compareTo(o2);
                return flag;
            }
        });
        Date nearlyDate = null;
        Date now = DateUtil.getDateTimeFormat(DateUtil.getDateFormat(new Date(),DateUtil.DATETIME_DEFAULT_FORMAT));
        for (Date d : chickEggProdTimeVal) {
            if (d.compareTo(now) > 0) {
                nearlyDate = d;
                break;
            }
        }
        Long diffMin = 0L;
        if (nearlyDate != null) {
            diffMin = DateUtil.interval(now,nearlyDate,12);//相差分钟
        }
		return diffMin;
	}
}
