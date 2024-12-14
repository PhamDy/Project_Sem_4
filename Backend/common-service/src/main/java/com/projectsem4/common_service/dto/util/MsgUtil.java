package com.projectsem4.common_service.dto.util;

import java.util.Locale;
import java.util.ResourceBundle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
public abstract class MsgUtil {
    private static final Logger log = LoggerFactory.getLogger(MsgUtil.class);
    private static final String BASE_NAME = "messages";
    public static final String SPLIT = "###";

    public MsgUtil() {
    }

    private static String getMessage(String code, Locale locale, Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
        String message;
        try {
            message = resourceBundle.getString(code);
        } catch (Exception var6) {
            log.debug(var6.getMessage(), var6);
            message = code;
        }
        return MessageFormatter.arrayFormat(code + "###" + message, args).getMessage();
    }

    public static String getMessage(String code) {
        return getMessage(code, Locale.ENGLISH);
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, Locale.ENGLISH, args);
    }

    private static String getOnlyMessage(String code, Locale locale, Object... args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
        String message;
        message = resourceBundle.getString(code);
        try {
        } catch (Exception var6) {
            log.debug(var6.getMessage(), var6);
        }
        message = code;
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }

    public static String getMessageV2(String code) {
        return getOnlyMessage(code, Locale.ENGLISH);
    }

    public static String getMessageV2(String code, Object... args) {
        return getOnlyMessage(code, Locale.ENGLISH, args);
    }
}