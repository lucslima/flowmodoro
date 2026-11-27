package com.lucslima.flowmodoro;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;

/** Recebe o alarme do fim da pausa e mostra uma notificação com som. */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL = "break_end";
    private static final int ID = 42;

    @Override
    public void onReceive(Context ctx, Intent intent) {
        NotificationManager nm = ctx.getSystemService(NotificationManager.class);

        NotificationChannel ch = new NotificationChannel(CHANNEL, "Fim da pausa",
                NotificationManager.IMPORTANCE_HIGH);
        ch.setDescription("Avisa quando a pausa acaba");
        ch.enableVibration(true);
        ch.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
                new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build());
        nm.createNotificationChannel(ch);

        Intent open = new Intent(ctx, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent tap = PendingIntent.getActivity(ctx, 0, open, PendingIntent.FLAG_IMMUTABLE);

        Notification n = new Notification.Builder(ctx, CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("PAUSA CONCLUIDA")
                .setContentText("Bora voltar quando estiver pronto.")
                .setCategory(Notification.CATEGORY_ALARM)
                .setContentIntent(tap)
                .setAutoCancel(true)
                .build();
        nm.notify(ID, n);
    }

    static void dismiss(Context ctx) {
        ctx.getSystemService(NotificationManager.class).cancel(ID);
    }
}
