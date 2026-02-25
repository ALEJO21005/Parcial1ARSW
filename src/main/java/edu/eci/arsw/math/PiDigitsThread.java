package edu.eci.arsw.math;

public class PiDigitsThread extends Thread {

    private int start;
    private int count;
    private byte[] digits;


    public PiDigitsThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void run() {
        this.digits = PiDigits.getDigits(start, count);
    }

    
    public byte[] getDigits() {
        return this.digits;
    }
}
