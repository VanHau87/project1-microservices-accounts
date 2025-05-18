package com.eazybytes.accounts.config;

import java.util.Locale;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {
	
	private static final Locale DEFAULT_LOCALES = Locale.ENGLISH;
	
	@Override
    public Locale resolveLocale(HttpServletRequest request) {
        String langParam = request.getParameter("lang"); //Prioritize lang parameter first
        Locale locale = null;
        if (langParam != null && !langParam.isBlank()) {
            locale = Locale.forLanguageTag(langParam);
        } else {
        	// Fallback to Accept-Language
        	locale = request.getLocale();
        }
        return locale != null ? locale : DEFAULT_LOCALES;
    }
	@Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // do nothing (stateless)
    }
}
