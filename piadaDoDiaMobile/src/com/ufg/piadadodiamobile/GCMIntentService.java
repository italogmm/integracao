package com.ufg.piadadodiamobile;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
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
		if (mensagem != null && !"".equals(mensagem))
			NotificacaoPiada.mostraNotificacao("Sorria! Nova piada recebida", mensagem, context);
		
	}

	/**
	 * Método executado quando o aplicativo se desregistra do GCM.
	 */
	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.i(Constantes.TAG, "GCM Desativado.");
	}

}