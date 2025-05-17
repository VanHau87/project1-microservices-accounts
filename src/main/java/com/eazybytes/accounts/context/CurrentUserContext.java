package com.eazybytes.accounts.context;

public class CurrentUserContext {
	private static final ThreadLocal<String> currentUser = ThreadLocal.withInitial(() -> "system");

    public static void set(String username) {
        currentUser.set(username);
    }

    public static String get() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
