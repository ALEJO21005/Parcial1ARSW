package edu.eci.arsw.math;

public class PauseControl {

    private volatile boolean paused = false;

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notifyAll();
    }

    public synchronized void waitIfPaused() throws InterruptedException {
        while (paused) {
            wait();
        }
    }

    public boolean isPaused() {
        return paused;
    }
}
