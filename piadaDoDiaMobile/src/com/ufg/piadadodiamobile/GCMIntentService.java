package com.ufg.piadadodiamobile;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.ufg.piadadodiamobile.activity.AtivaGCMActivity;
import com.ufg.piadadodiamobile.bean.Piada;
import com.ufg.piadadodiamobile.modelo.dao.PiadaDAO;
import com.ufg.piadadodiamobile.services.NotificacaoPiada;

public class GCMIntentService extends GCMBaseIntentService {

	/**
	 * Método executado quando o aplicativo se registra no GCM para 
	 * o recebimento de mensagens da Nuvem.
	 */
	@Override
	protected void onRegistered(Context context, String regId) {
		Log.i(Constantes.TAG, "GCM ativado.");
		/*
		 * Mostramos no console o ID de registro no GCM para usá-lo 
		 * posteriormente, no aplicativo cliente, para o envio de mensagens
		 * da Nuvem para o dispositivo Android.
		 */
		String mensagem = "ID de registro no GCM: " + regId;
		Log.i(Constantes.TAG, mensagem);
	}

	/**
	 * Método executado quando algum erro ocorre na comunicação
	 * com o GCM. 
	 */
	@Override
	protected void onError(Context context, String errorMessage) {
		Log.e(Constantes.TAG, "Erro: " + errorMessage);
	}

	/**
	 * Método executado quando uma nova mensagem é recebida 
	 * da Nuvem através do GCM.
	 */
	@Override
	protected void onMessage(Context context, Intent intent) {
		/*
		 * Recuperamos a mensagem recebida através do Extras
		 * da Intent do GCM que invocou este Service.
		 */
		String mensagem = intent.getExtras().getString("mensagem");
		Log.i(Constantes.TAG, "Mensagem recebida: " + mensagem);
		
		/*
		 * Disparamos uma Notificação para avisar o usuário sobre a 
		 * nova mensagem recebida da Nuvem.
		 */
		if (mensagem != null && !"".equals(mensagem)){
			PiadaDAO piadaDao = new PiadaDAO(context);
			piadaDao.cadastrar(new Piada(mensagem));
			
			mostraNotificacao("Sorria! Nova piada recebida", mensagem, context);
		}
		
	}
	
	public void mostraNotificacao(String titulo, String mensagem,
			Context context) {

		long tempoDefinido = System.currentTimeMillis();
		
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(context)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(titulo)
		        .setContentText(mensagem);
		
		//Notification notification = new Notification(R.drawable.ic_launcher, titulo, tempoDefinido);
		Intent resultIntent = new Intent(context, AtivaGCMActivity.class);
		resultIntent.putExtra("mensagem_recebida", mensagem);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(AtivaGCMActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		
		mBuilder.setContentIntent(resultPendingIntent);
		
		Notification notification = mBuilder.build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_ALL;
		
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(0, notification);
	}

	/**
	 * Método executado quando o aplicativo se desregistra do GCM.
	 */
	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.i(Constantes.TAG, "GCM Desativado.");
	}

}