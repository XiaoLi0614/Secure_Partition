package lesani.gui.console;

public class Logger {
    private static boolean on = false;

    public static boolean isOn() {
        return on;
    }

    public static void setOn(boolean on) {
        Logger.on = on;
    }
    public static void setOn() {
        Logger.on = true;
    }
    public static void setOff() {
        Logger.on = false;
    }

    public static void println() {
        if (on)
            System.out.println();
    }

    public static void println(String s) {
        if (on)
            System.out.println(s);
    }

    public static void println(Object o) {
        if (on)
            System.out.println(o);
    }

    public static void print(String s) {
        if (on)
            System.out.print(s);
    }

    public static void print(Object o) {
        if (on)
            System.out.print(o);
    }

}
