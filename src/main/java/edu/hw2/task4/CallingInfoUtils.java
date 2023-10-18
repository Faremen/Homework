package edu.hw2.task4;

public class CallingInfoUtils {

    private CallingInfoUtils() {}

    public static CallingInfo getCallingInfo() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];

        return new CallingInfo(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
    }
}
