package com.eazybytes.accounts.utils;

import java.util.Random;

public class CommonUtils {
	
	private static final Random RANDOM = new Random();
	
	public static String generateAccountNumber() {
        StringBuilder sb = new StringBuilder();
        for (int group = 0; group < 4; group++) {
            for (int i = 0; i < 4; i++) {
                sb.append(RANDOM.nextInt(10));
            }
            if (group < 3) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
