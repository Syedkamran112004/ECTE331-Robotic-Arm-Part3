package robotarm;

/**
 * Low-priority logging task.
 */
public class ArmLogger extends Thread {

    private MotorController motor;

    public ArmLogger(MotorController motor) {
        this.motor = motor;
        setName("System Logger");
        setPriority(Thread.MIN_PRIORITY);
    }

    public void run() {
        System.out.println(getName() + " started.");
        motor.useMotor(getName(), 1500);
        System.out.println(getName() + " completed.");
    }
}