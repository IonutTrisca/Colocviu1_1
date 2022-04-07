package ro.pub.cs.systems.eim.Colocviu1_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;
    private ProcessingThread processingThread = null;

    private String intructions;

    private final int SLEEP_TIME = 5000;

    public ProcessingThread(Context context, String instructions) {
        this.context = context;
        this.intructions = instructions;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started!");
        sleep();
        sendMessage();
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }


    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.SERVICE_INTENT);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + intructions);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
