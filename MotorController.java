package robotarm;

/**
 * Shared motor resource used by all real-time threads.
 */
public class MotorController {

    private volatile boolean safetyWaiting = false;
    private long safetyWaitingTime = 0;

    public long getSafetyWaitingTime() {
        return safetyWaitingTime;
    }

    public void useMotor(String threadName, int workTime) {

        long requestTime = System.currentTimeMillis();
        System.out.println(threadName + " requests MotorController.");

        if (threadName.equals("Safety Monitor")) {
            safetyWaiting = true;
            System.out.println("Safety Monitor is BLOCKED waiting for motor.");
        }

        synchronized (this) {

            long startTime = System.currentTimeMillis();
            long waitingTime = startTime - requestTime;

            if (threadName.equals("Safety Monitor")) {
                safetyWaitingTime = waitingTime;
            }

            Thread current = Thread.currentThread();
            int oldPriority = current.getPriority();

            if (PriorityManager.mode == PriorityManager.PRIORITY_CEILING) {
                current.setPriority(Thread.MAX_PRIORITY);
                System.out.println("Priority ceiling applied to " + threadName);
            }

            if (PriorityManager.mode == PriorityManager.PRIORITY_INHERITANCE
                    && threadName.equals("System Logger")
                    && safetyWaiting) {

                current.setPriority(Thread.MAX_PRIORITY);
                System.out.println("Priority inheritance: System Logger temporarily raised to HIGH priority.");
            }

            System.out.println(threadName + " obtained motor after "
                    + waitingTime + " ms.");

            try {
                if (threadName.equals("System Logger")) {

                    if (PriorityManager.mode == PriorityManager.BASELINE) {
                        Thread.sleep(1700);
                    } else {
                        Thread.sleep(700);
                    }

                } else if (threadName.equals("Motion Planner")) {
                    Thread.sleep(700);
                } else {
                    Thread.sleep(300);
                }

            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }

            current.setPriority(oldPriority);

            if (threadName.equals("Safety Monitor")) {
                safetyWaiting = false;
            }

            System.out.println(threadName + " released MotorController.");

            if (PriorityManager.mode == PriorityManager.PRIORITY_CEILING) {
                System.out.println("Priority restored for " + threadName);
            }
        }
    }
}