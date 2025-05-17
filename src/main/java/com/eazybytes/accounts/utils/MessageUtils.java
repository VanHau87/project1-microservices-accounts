package com.eazybytes.accounts.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
	
	private static MessageSource messageSource;
	
	public MessageUtils(MessageSource injectedMessageSource) {
        MessageUtils.messageSource = injectedMessageSource;
    }

    public static String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
