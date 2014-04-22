package com.ufg.piadadodiamobile;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class GCM {
	/**
	 * Método responsável por ativar o uso do GCM.
	 * 
	 * @param context
	 */
	public static void ativa(Context context) {
		GCMRegistrar.checkDevice(context);
		GCMRegistrar.checkManifest(context);
		
		final String regId = GCMRegistrar.getRegistrationId(context);
		if (regId.equals("")) {
			GCMRegistrar.register(context, Constantes.SENDER_ID);
			String id = GCMRegistrar.getRegistrationId(context);
			Log.i(Constantes.TAG, "Serviço GCM ativado. ID " + id);
		} else {
			Log.i(Constantes.TAG, "O serviço GCM já está ativo. ID: " + regId);
		}
	}

	/**
	 * Método responsável por desativar o uso do GCM.
	 * 
	 * @param context
	 */
	public static void desativa(Context context) {
		GCMRegistrar.unregister(context);
		Log.i(Constantes.TAG, "Serviço GCM desativado.");
	}

	/**
	 * Método responsável por verificar se o aplicativo está ou não registrado
	 * para uso do GCM.
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAtivo(Context context) {
		return GCMRegistrar.isRegistered(context);
	}
}
