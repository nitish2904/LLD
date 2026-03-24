package TrainPlatform;

import TrainPlatform.model.Train;
import TrainPlatform.service.TrainPlatformService;
import TrainPlatform.strategy.*;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Train Platform Management — LLD (#32)
 * Strategy Pattern for platform assignment.
 */
public class TrainPlatformMain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  Train Platform Management — LLD Demo   ║");
        System.out.println("╚════════════════════════════════════════╝");

        TrainPlatformService service = new TrainPlatformService(3, new FirstAvailableStrategy());

        Train t1 = new Train("T1", "Rajdhani", LocalTime.of(9, 0), LocalTime.of(9, 30));
        Train t2 = new Train("T2", "Shatabdi", LocalTime.of(9, 15), LocalTime.of(10, 0));
        Train t3 = new Train("T3", "Duronto",  LocalTime.of(9, 20), LocalTime.of(9, 50));
        Train t4 = new Train("T4", "Garib Rath", LocalTime.of(10, 0), LocalTime.of(10, 30));
        Train t5 = new Train("T5", "Express",  LocalTime.of(9, 10), LocalTime.of(9, 45));

        System.out.println("\n===== Schedule (FirstAvailable) =====");
        service.scheduleTrain(t1);
        service.scheduleTrain(t2);
        service.scheduleTrain(t3);
        service.scheduleTrain(t5); // 4th overlapping — no platform!
        service.printStatus();

        System.out.println("\n===== Depart T1, schedule T4 =====");
        service.removeTrain("T1");
        service.scheduleTrain(t4);
        service.printStatus();

        System.out.println("\n===== Min platforms needed =====");
        int min = service.getMinPlatformsRequired(Arrays.asList(t1, t2, t3, t4, t5));
        System.out.println("  Min platforms for all 5 trains: " + min);

        System.out.println("\n✅ Done.");
    }
}
