public class LogEntry {
    private final String level;
    private final String message;
    private final String source;
    private final String time;

    public LogEntry(String level, String message, String source, String time) {
        this.level = level;
        this.message = message;
        this.source = source;
        this.time = time;
    }

    public String getLevel() { return level; }
    public String getMessage() { return message; }
    public String getSource() { return source; }
    public String getTime() { return time; }
}
