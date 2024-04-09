package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.MazeStructure.Path;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Benchmark {

    private long start;
    private long elapsed;
    private boolean timerOn;

    public Benchmark() {
        this.timerOn = false;
    }

    public void startTimer() {
        if (!this.timerOn) {
            this.start = System.nanoTime();
            this.timerOn = true;
        } else {
            System.out.println("Need to end timer first");
        }
    }

    public void endTimer() {
        if (this.timerOn) {
            long end = System.nanoTime();
            this.timerOn = false;
            this.elapsed = end - this.start;
        } else {
            System.out.println("Need to start timer first");
        }
    }

    public double getElapsedTime() {
        // Convert and round
        if (!this.timerOn) {
            return convertNanoMilli(this.elapsed);
        } else {
            System.out.println("No start or end time!");
            return -1.0;
        }
    }

    private double convertNanoMilli(long nano) {
        BigDecimal milli = new BigDecimal(nano).divide(new BigDecimal(1000000));
        return adjustSigFigs(milli.doubleValue(),2);
    }

    private double adjustSigFigs(double value, int sigFigs) {
        if (value == 0) { return 0; }
        return new BigDecimal(value, new MathContext(sigFigs, RoundingMode.HALF_UP)).doubleValue();
    }

    public double calcSpeedUp(Path baselinePath, Path methodPath) {
        int baselinePathLen = baselinePath.getCanonicalForm().replace(" ", "").length();
        int methodPathLen = methodPath.getCanonicalForm().replace(" ", "").length();
        return (double) baselinePathLen / methodPathLen;
    }
}
