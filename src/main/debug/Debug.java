package main.debug;

public class Debug implements DebugConstants {

    // When DEBUG is true, print debugging msg.
    public static void println(String format, String... args) {
        if (DEBUG) {
            System.out.println(String.format(format, args));
        }
    }

    public static void printList(String[][] list) {
        if(list.length == 0) return;
        int cols = list[0].length;
        for (String[] strings : list) {
            System.out.print("\t\t");
            for (int j = 0; j < cols; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }
}
