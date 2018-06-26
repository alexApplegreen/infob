package aufgabe2.antRace;

import aufgabe2.antRace.AntField.Field;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Ant implements Runnable {

    private AntField fields;
    private int x;
    private int y;
    private int stepCount;

    /**
     * @param fields    the {@code AntField} on which this {@code Ant} operates
     * @param x         x-axis value of the starting position
     * @param y         y-axis value of the starting position
     * @param stepCount initial stepCount of this {@code Ant}.
     * @throws IllegalArgumentException If the {@code Field} at position {@code x,y} does not exist, or
     *                                  if its value is < 0
     */
    public Ant(AntField fields, int x, int y, int stepCount) {
        this.fields = fields;
        this.stepCount = stepCount;
        this.x = x;
        this.y = y;
        Field field = fields.getField(x, y);
        if (field == null) {
            throw new RuntimeException("Field does not exist");
        }
        field.setValue(stepCount);
        fields.increaseAntCount();
    }

    @Override
    public void run() {
        boolean notDone;
        Field f;
        int newX, newY;

        do {

            notDone = false;
            newX = x;
            newY = y;

            for (int i = newX - 1; i <= newX + 1; i++) {
                for (int j = newY - 1; j <= newY + 1; j++) {

                    f = fields.getField(i, j);

                    if (f != null) {
                        synchronized (f) {

                            int value = f.getValue();

                            if (value == fields.FREE || value >= stepCount + 1) {
                                if (!notDone) {
                                    f.setValue(stepCount + 1);
                                    x = i;
                                    y = j;
                                    notDone = true;
                                } else {
                                    Ant helper = new Ant(fields, i, j, stepCount + 1);
                                    Thread t = new Thread(helper);
                                    t.start();
                                }
                            }
                        }
                    }
                }
            }

            stepCount++;

        } while (notDone);

        fields.decreaseAntCount();
    }

}
