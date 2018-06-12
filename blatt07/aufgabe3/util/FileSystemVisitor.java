package util;

public class FileSystemVisitor<T> implements Visitor<T> {

    /**
     * Enum for runmodes
     */
    private enum RunMode {
        CONTINUE,
        SKIP,
        BREAK,
        RECURSIVE
    }

    private boolean continues;
    private RunMode mode;

    // Constructor
    public FileSystemVisitor() {
        this.continues = true;
        this.mode = RunMode.CONTINUE;
    }

    /**
     * @brief prints element and switches runmode if possible / necessary
     * @param o element of visitable
     * @return boolean continues, indicates if Visitor will continue iterating
     */
    public boolean visit(T o) {
        if (this.mode == RunMode.BREAK) {
            this.continues = false;
        }
        String s = o.toString();
        if ((o != null && continues) || this.mode != RunMode.SKIP) {
            this.continues = true;
            if (s.contains("    ")) {
                this.mode = RunMode.RECURSIVE;
                this.recursiveEntry();
            }
            else if (this.mode == RunMode.RECURSIVE && !s.contains("    ")) {
                this.mode = RunMode.CONTINUE;
                this.recursiveExit();
            }
            else {
                this.mode = RunMode.CONTINUE;
            }
            System.out.println(o);
            return true;
        }
        else {
            this.continues = false;
            this.mode = RunMode.BREAK;
            return false;
        }
    }

    /**
     * @brief returns current runMode
     * @return RunMode
     */
    public RunMode getMode() {
        return this.mode;
    }

    /**
     * @brief sets mode
     * @param mode RunMode
     */
    public void setMode(RunMode mode) {
        this.mode = mode;
    }

    /**
     * @brief called when visitor detects recursive entry in Subdir
     */
    private void recursiveEntry() {
        // do some stuff here
    }

    /**
     * @brief called when visitor detects exit from subdir
     */
    private void recursiveExit() {
        // TODO check if subdir is finished, then call this
    }
}