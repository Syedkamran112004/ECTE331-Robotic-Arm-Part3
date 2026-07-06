package robotarm;

/**
 * Runs one test scenario and returns Safety Monitor waiting time.
 */
public class PerformanceTest {

    public static long runTest(int mode) {

        PriorityManager.mode = mode;

        MotorController motor = new MotorController();

        ArmLogger logger = new ArmLogger(motor);
        SafetyMonitor safety = new SafetyMonitor(motor);
        MotionPlanner planner = new MotionPlanner(motor);

        System.out.println();
        System.out.println("====================================");

        if (mode == PriorityManager.BASELINE) {
            System.out.println("TEST 1: BASELINE / PRIORITY INVERSION");
        } else if (mode == PriorityManager.PRIORITY_INHERITANCE) {
            System.out.println("TEST 2: PRIORITY INHERITANCE");
        } else {
            System.out.println("TEST 3: PRIORITY CEILING");
        }

        System.out.println("====================================");

        logger.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        safety.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        planner.start();

        try {
            logger.join();
            safety.join();
            planner.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrupted.");
        }

        System.out.println("Safety Monitor waiting time = "
                + motor.getSafetyWaitingTime() + " ms");

        return motor.getSafetyWaitingTime();
    }
}