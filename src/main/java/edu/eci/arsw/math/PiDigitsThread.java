package edu.eci.arsw.math;

public class PiDigitsThread extends Thread {

    private int start;
    private int count;
    private byte[] digits;
    private volatile int processedDigits;
    private PauseControl pauseControl;

    public PiDigitsThread(int start, int count, PauseControl pauseControl) {
        this.start = start;
        this.count = count;
        this.pauseControl = pauseControl;
        this.processedDigits = 0;
        
    }

    @Override
    public void run() {
        digits = new byte[count];
        double sum = 0;
        int s = start;

        for (int i = 0; i < count; i++) {
            try {
                pauseControl.waitIfPaused();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            if (i % PiDigits.DigitsPerSum == 0) {
                sum = 4 * PiDigits.sum(1, s)
                        - 2 * PiDigits.sum(4, s)
                        - PiDigits.sum(5, s)
                        - PiDigits.sum(6, s);
                s += PiDigits.DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
            processedDigits = i + 1;
        }
    }

    public byte[] getDigits() {
        return this.digits;
    }

    public int getProcessedDigits() {
        return this.processedDigits;
    }
}
