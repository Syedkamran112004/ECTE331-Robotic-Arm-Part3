package robotarm;

/**
 * High-priority safety task.
 */
public class SafetyMonitor extends Thread {

    private MotorController motor;

    public SafetyMonitor(MotorController motor) {
        this.motor = motor;
        setName("Safety Monitor");
        setPriority(Thread.MAX_PRIORITY);
    }

    public void run() {
        System.out.println(getName() + " started.");
        motor.useMotor(getName(), 300);
        System.out.println(getName() + " completed.");
    }
}