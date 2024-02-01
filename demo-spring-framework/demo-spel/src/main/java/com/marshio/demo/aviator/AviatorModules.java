package com.marshio.demo.aviator;

import com.googlecode.aviator.annotation.Import;

/**
 * Aviator Modules
 */
@SuppressWarnings("unused")
public class AviatorModules {

    @Import(ns = "stringModules")
    public static class StringModule {
        public static boolean isBlank(final String s) {
            return s == null || s.trim().isEmpty();
        }
    }
}
