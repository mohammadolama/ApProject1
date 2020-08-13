package Server.Controller.Manager;


public class MyTimer extends Thread {
    private final java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat(" ss");
    private long startTime;
    private boolean p2Turn;
    private boolean flag1 = true;
    private boolean flag2 = true;
    private final Managers m;

    public MyTimer(boolean p2Turn, Managers m) {
        this.p2Turn = p2Turn;
        this.m = m;
    }

    public void run() {
        while (flag2) {
            while (flag1) {
                startTime = 60 * 1000 + System.currentTimeMillis();
                while (flag1 && startTime - System.currentTimeMillis() > 0) {
                    long time1 = startTime - System.currentTimeMillis();
                    m.setTime(timerFormat.format(time1));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (flag1) {
                    if (p2Turn)
                        m.getCl2().notifyEndturn();
                    else
                        m.getCl1().notifyEndturn();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset1(boolean p2Turn) {
        this.p2Turn = p2Turn;
        flag1 = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag1 = true;
    }

    public void stopTimer() {
        flag1 = false;
        flag2 = false;
    }

    public boolean isStopped() {
        return !flag1 && !flag2;
    }
}