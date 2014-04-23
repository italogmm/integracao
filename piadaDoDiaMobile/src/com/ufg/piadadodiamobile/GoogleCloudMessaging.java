package com.ufg.piadadodiamobile;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class GoogleCloudMessaging {
	
	public static void ativa(Context context) {
		GCMRegistrar.checkDevice(context);
		GCMRegistrar.checkManifest(context);
		
		final String regId = GCMRegistrar.getRegistrationId(context);
		if (regId.equals("")) {
			GCMRegistrar.register(context, Constantes.SENDER_ID);
			String id = GCMRegistrar.getRegistrationId(context);
			Log.i(Constantes.TAG, "Servi�o GoogleCloudMessaging ativado. ID " + id);
		} else {
			Log.i(Constantes.TAG, "O servi�o GoogleCloudMessaging j� est� ativo. ID: " + regId);
		}
	}

	public static void desativa(Context context) {
		GCMRegistrar.unregister(context);
		Log.i(Constantes.TAG, "Servi�o GoogleCloudMessaging desativado.");
	}

	public static boolean isAtivo(Context context) {
		return GCMRegistrar.isRegistered(context);
	}
}
