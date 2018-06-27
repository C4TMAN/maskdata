package com.c4tman.play.logPrint.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.pattern.RegexReplacement;

/**
 * https://blog.csdn.net/vcstrong/article/details/80527455
 *
 * https://github.com/apache/logging-log4j2/blob/a801104552b6dbf961eab807d4c68054b88cc5de/log4j-core/src/test/resources/log4j-replace.xml
 *
 * Created by zhangxiaoman on 2018/6/1.
 */
@Slf4j
@Plugin(name = "replaces", category = "Core", printObject = true)
public final class CustomRegexReplaces {


    // replace标签，复用log4j已有plugin， replaces 下可以0，1，多个replace
    private final RegexReplacement[] replaces;

    private CustomRegexReplaces(RegexReplacement[] replaces) {
        this.replaces = replaces;
    }

    /**
     * 格式化输出日志信息， 此方法会执行多个正则表达式匹配与替换
     *
     * @param msg
     * @return
     */
    public String format(String msg) {
        for (RegexReplacement replace : replaces) {
            msg = replace.format(msg);
        }
        return msg;
    }

    /**
     * 实现pluginFactory， 用于生成pugin
     *
     * @param replaces
     * @return
     */
    @PluginFactory
    public static CustomRegexReplaces createRegexReplacement(
            @PluginElement("replaces") final RegexReplacement[] replaces) {
        if (replaces == null) {
            log.info("no replaces is defined");
            return null;
        }

        return new CustomRegexReplaces(replaces);
    }

}