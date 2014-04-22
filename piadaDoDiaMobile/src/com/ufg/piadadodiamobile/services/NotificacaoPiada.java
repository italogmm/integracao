package com.ufg.piadadodiamobile.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ufg.piadadodiamobile.R;
import com.ufg.piadadodiamobile.activity.AtivaGCMActivity;
import com.ufg.piadadodiamobile.activity.PiadaActivity;

public class NotificacaoPiada {
	/**
	 * M�todo respons�vel por disparar uma notifica��o na barra de status.
	 * 
	 * @param titulo
	 * @param mensagem
	 * @param context
	 */
	public static void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		long tempoDefinido = System.currentTimeMillis();

		// Objeto Notification
		Notification notification = new Notification(R.drawable.ic_launcher,
				titulo, tempoDefinido);

		// Intent que será disparada quando o usuário clicar sobre a
		// Notificação
		Intent intent = new Intent(context, PiadaActivity.class);
		intent.putExtra("mensagem_recebida", mensagem);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Configurando os dados da Notificação
		notification.setLatestEventInfo(context, titulo, mensagem,
				pendingIntent);

		// Oculta a notificação após o usuário clicar sobre ela
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Define alertas de acordo com o padrão definido no dispositivo
		notification.defaults = Notification.DEFAULT_ALL;

		// Agenda a Notificação
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}
}
