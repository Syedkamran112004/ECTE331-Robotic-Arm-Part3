package robotarm;

/**
 * Stores the current priority management mode.
 */
public class PriorityManager {

    public static final int BASELINE = 1;
    public static final int PRIORITY_INHERITANCE = 2;
    public static final int PRIORITY_CEILING = 3;

    public static int mode = BASELINE;

}