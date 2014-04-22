package com.ufg.piadaDoDia.envio;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaPiada {

	private static final String ID_DISPOSITIVO_GCM = "APA91bHKGJz7HDkrgD6K_C8_yKqv99PqEKcs-c5CFQCWStTcdcZ3ev10lgeU0tsYcYxAnGM8-tOoAyrIokKZO2MhO3nn1SzOHXuOpvlqu4c_uwAWJkAmZLBL7mq2is1H-XZeFzrF-ANervFE_NPW93xrN9Zgb7BfNelfmPEmAtu0e96oWyqHBV8";

	private static final String API_KEY = "AIzaSyBs_MPtEdFyEpYSYdAbCzXGmmU5rhjyQHA";

	public static void main(String[] args) {
		
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder()
				.collapseKey("1")
				.timeToLive(3)
				.delayWhileIdle(true)
				.addData("mensagem", // identificador da mensagem
						"Teste mensagem khghkg envikjhkhkh ada 1234567!")
				.build();

		Result result = null;

		/**
		 * Envia a mensagem para o dispositivo
		 */
		try {
			result = sender.send(message, ID_DISPOSITIVO_GCM, 1);
			String canonicalRegId = result.getCanonicalRegistrationId();
			System.out.println("canonicalRegId: " + canonicalRegId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Imprime o resultado do envio na sa�da padr�o
		if (result != null)
			System.out.println(result.toString());

	}
}