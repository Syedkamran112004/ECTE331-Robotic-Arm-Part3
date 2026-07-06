package robotarm;

/**
 * Main program for the robotic arm priority management simulation.
 */
public class MainApp {

    public static void main(String[] args) {

        System.out.println("Robotic Arm Priority Management Simulation");

        long baselineWait = PerformanceTest.runTest(PriorityManager.BASELINE);
        long inheritanceWait = PerformanceTest.runTest(PriorityManager.PRIORITY_INHERITANCE);
        long ceilingWait = PerformanceTest.runTest(PriorityManager.PRIORITY_CEILING);

        System.out.println();
        System.out.println("========== PERFORMANCE SUMMARY ==========");
        System.out.println("Baseline waiting time             : " + baselineWait + " ms");
        System.out.println("Priority inheritance waiting time : " + inheritanceWait + " ms");
        System.out.println("Priority ceiling waiting time     : " + ceilingWait + " ms");
        System.out.println("=========================================");
    }
}