package com.ufg.piadaDoDia.envio;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class EnviaPiada {


	
	// Vari�vel com o ID do dispositivo registrado no GCM
	private static final String ID_DISPOSITIVO_GCM = "APA91bFwBJIJk37M4rDAB39sxGT3ilddjvIJHVpGaOxbGYkpCyGug4mkojkYaBDcx1U3TDBaM6NMI6YO3mSgW1U1uZv3gNRP-p5HIXTTn8QJEemqqDJJOOyBuZhjUGqV0pfz5pRszLrDVvV9bdX6Sn0oe9y2EiNN2JVzeRIT64nTXedhh91HyQs";
	// Vari�vel com a chave obtida em API ACCESS no Google APIs
	private static final String API_KEY = "AIzaSyCE-UiNpWF9ZSAv92tTbUPJUYv3a7ZV4k8";

	public static void main(String[] args) {

		/**
		 * ID do Sender (Enviador)
		 */
		Sender sender = new Sender(API_KEY);

		/**
		 * Mensagem a ser enviada
		 */
		Message message = new Message.Builder()
				.collapseKey("1")
				.timeToLive(3)
				.delayWhileIdle(true)
				.addData("mensagem", // identificador da mensagem
						"Ol�! Este � um teste de envio de mensagem atrav�s do GCM!")
				.build();

		Result result = null;

		/**
		 * Envia a mensagem para o dispositivo
		 */
		try {
			result = sender.send(message, ID_DISPOSITIVO_GCM, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Imprime o resultado do envio na sa�da padr�o
		if (result != null)
			System.out.println(result.toString());

	}
}
