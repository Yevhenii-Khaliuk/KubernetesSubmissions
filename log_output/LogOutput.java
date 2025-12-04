import java.time.LocalDateTime;
import java.util.UUID;

public class LogOutput {

    public static void main(String[] args) throws InterruptedException {
        var uuid = UUID.randomUUID().toString();

        while (true) {
            System.out.println("%s: %s".formatted(LocalDateTime.now(), uuid));
            Thread.sleep(5000);
        }
    }
}
