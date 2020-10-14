package lesani.file;

public class ConsoleLogger extends Logger {
    public static ConsoleLogger instance = new ConsoleLogger();

    private ConsoleLogger() {
        test = new Logger() {
            @Override
            public void print(String s) {
            }

            @Override
            public void println(String s) {
            }

            @Override
            public void startLine(String s) {
            }

            @Override
            public void endLine(String s) {
            }
        };
    }

    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }

    @Override
    public void startLine(String s) {
        System.out.print(s);
    }

    @Override
    public void endLine(String s) {
        System.out.println(s);
    }
}
