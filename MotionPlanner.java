package robotarm;

/**
 * Medium-priority motion planning task.
 */
public class MotionPlanner extends Thread {

    private MotorController motor;

    public MotionPlanner(MotorController motor) {
        this.motor = motor;
        setName("Motion Planner");
        setPriority(Thread.NORM_PRIORITY);
    }

    public void run() {
        System.out.println(getName() + " started and is doing medium-priority work.");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrupted.");
        }

        motor.useMotor(getName(), 300);
        System.out.println(getName() + " completed.");
    }
}