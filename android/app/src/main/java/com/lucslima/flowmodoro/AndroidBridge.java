package com.lucslima.flowmodoro;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.webkit.JavascriptInterface;

/**
 * Ponte entre o JavaScript do index.html e o Android.
 * No navegador ela não existe; o index.html só a usa se window.AndroidBridge estiver presente.
 */
public class AndroidBridge {

    private final MainActivity activity;

    AndroidBridge(MainActivity activity) { this.activity = activity; }

    /** Agenda o alarme do fim da pausa, para tocar mesmo com o app fechado ou a tela apagada. */
    @JavascriptInterface
    public void scheduleBreakEnd(double epochMs) {
        Context ctx = activity.getApplicationContext();
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = pending(ctx);
        long at = (long) epochMs;
        if (Build.VERSION.SDK_INT >= 31 && !am.canScheduleExactAlarms()) {
            am.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, pi);
        } else {
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, at, pi);
        }
    }

    /** Cancela o alarme (pausa pulada ou encerrada no app). */
    @JavascriptInterface
    public void cancelBreakEnd() {
        Context ctx = activity.getApplicationContext();
        AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pending(ctx));
    }

    @JavascriptInterface
    public void keepScreenOn(boolean on) { activity.setKeepScreenOn(on); }

    private static PendingIntent pending(Context ctx) {
        Intent i = new Intent(ctx, AlarmReceiver.class);
        return PendingIntent.getBroadcast(ctx, 0, i,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }
}
