package com.crm.Tools;

import java.lang.reflect.Field;

public final class Utils
{
    public static <T> Field[] getFieldFromClass(T data)
    {
        return (data.getClass().getDeclaredFields());
    }

    public static <T> String getClassName(T data)
    {
        return (data.getClass().getSimpleName());
    }

    public static String ucFirst(String str)
    {
        return (str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
    }
}
