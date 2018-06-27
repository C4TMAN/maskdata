package com.c4tman.play.logPrint.mask;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoman on 2018/6/5.
 */
@Slf4j
public class MaskLogUtil {

    private final static List<MaskPattern> maskPatterns = new ArrayList<>();
    static {
        MaskPattern maskPattern1 = new MaskPattern();
        maskPattern1.setRegx("(IdNo|CertId|CertID|idCard)(\":\")(\\d{6})\\d{8,11}(\\w{1})(\")");
        maskPattern1.setReplace("$1$2$3**************$4$5");
        maskPatterns.add(maskPattern1);

        MaskPattern maskPattern2 = new MaskPattern();
        maskPattern2.setRegx("(mobile|phone|mobilePh|tell)(\":\")(\\d{3})\\d{4}(\\d{4})(\")");
        maskPattern2.setReplace("$1$2$3****$4$5");
        maskPatterns.add(maskPattern2);

    }

    /**
     * 有很多bug
     * @param type
     * @param open 是否开启脱敏
     * @param data
     * @param vars
     */
    public static void print(LogType type, boolean open,  String data, Object... vars){

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<vars.length;i++){
            int start = data.indexOf("{");
            int end = data.indexOf("}");
            sb.append(data.substring(0, start)).append(vars[i]);
            data = sb.toString() + data.substring(end+1);
            sb.delete(0, sb.length());
        }
        if(open){
            for(MaskPattern pattern : maskPatterns){

                data = data.replaceAll(pattern.getRegx(), pattern.getReplace());
                System.out.println();
            }
        }
        if(type.equals(LogType.DEBUG)){
            log.debug(data);
        }else if(type.equals(LogType.INFO)){
            log.info(data);
        }else if (type.equals(LogType.ERROR)){
            log.error(data);
        }else if (type.equals(LogType.WARN)){
            log.warn(data);
        }
    }

    @Data
    public static class MaskPattern{
        String regx;
        String replace;
    }
}

