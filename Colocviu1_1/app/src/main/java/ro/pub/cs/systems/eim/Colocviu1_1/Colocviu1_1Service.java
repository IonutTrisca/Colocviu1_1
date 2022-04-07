package ro.pub.cs.systems.eim.Colocviu1_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Colocviu1_1Service extends Service {
    private ProcessingThread processingThread = null;

    public int onStartCommand(Intent intent, int flags, int startId) {
        String instructions = intent.getStringExtra(Constants.INSTRUCTIONS);
        processingThread = new ProcessingThread(this, instructions);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
