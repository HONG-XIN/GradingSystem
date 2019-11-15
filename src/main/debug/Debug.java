package main.debug;

public class Debug implements DebugConstants {

    // When DEBUG is true, print debugging msg.
    public static void println(String format, String... args) {
        if (DEBUG) {
            System.out.println(String.format(format, args));
        }
    }
}
