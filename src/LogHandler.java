import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHandler {
    public static ArrayList<String> log = new ArrayList<String>();

    public static void addLog(String event)
    {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YY HH:mm");
        String formattedDateTime = dateTime.format(formatter);

        log.add(formattedDateTime + ": " + event);
    }
}
