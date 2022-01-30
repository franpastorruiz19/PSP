package AE5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {
//	Correo de prueba: pruebapspae5@gmail.com
//	Contraseña de prueba: PSP123456
	int temperaturaActual = 15;
	int temperaturaTermostato = 15;
	int tipoPost = 0;
	String post = "";
	String emailRemitente = "";
	String contraseñaRemitente = "";
	String hostEmail = "smtp.gmail.com";
	String emailTecnico="mantenimientoinvernalia@gmail.com";
	String emailLordStark="megustaelfresquito@gmail.com";
	String UrlPdf = "AE5_T5_ServiciosRed.pdf";
	String UrlImg = "Florida.png";
	
	/*
	 * - Método: handleGetRequest
	 * - Descripción: Este método valida que la instrucción get este escrita correctamente. 
	 * - Parámetros de entrada: HttpExchange httpExchange. 
	 * - Parámetros de salida: true | false.
	 * 
	 */

	private boolean handleGetRequest(HttpExchange httpExchange) {
		if (httpExchange.getRequestURI().toString().split("\\?")[1].equals("temperaturaActual")) {
			return true;
		} else {
			return false;
		}

	}
	
	/*
	 * - Método: handleGetResponse
	 * - Descripción: Este método devuelve en forma de html los datos pedidos en handleGetRequest. 
	 * - Parámetros de entrada: HttpExchange httpExchange. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */

	private void handleGETResponse(HttpExchange httpExchange) throws IOException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "<html><body>Temperatura actual: " + temperaturaActual + "<p>Temperatura termostato: "
				+ temperaturaTermostato + "</p></body></html>";
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
	}

	/*
	 * - Método: handlePostRequest
	 * - Descripción: Este método valida que la instrucción post este escrita correctamente
	 *   y indica que tipo de POST se ha hecho. 
	 * - Parámetros de entrada: HttpExchange httpExchange. 
	 * - Parámetros de salida: true | false.
	 * 
	 */
	
	private boolean handlePostRequest(HttpExchange httpExchange) throws IOException {
		InputStream is = httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isr);
		post = bf.readLine();
		if (post.split("=")[0].equals("setTemperatura")) {
			if (post.split("=")[1].matches("[+-]?\\d*(\\.\\d+)?")) {
				tipoPost = 1;
				return true;
			} else {
				return false;
			}
		} else if (post.split(";")[0].split("=")[0].equals("notificarAveria:email_remitente")) {
			if (post.split(";")[1].split("=")[0].equals("pass_remitente")) {
				tipoPost = 2;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/*
	 * - Método: handlePostResponse
	 * - Descripción: Este método realiza dependiendo del tipo de POST que se ha asignado
	 *   en handlePostRequest la operación de cambiar la temperatura del termostato o de enviar
	 *   un correo de Alerta a dos correos electrónicos. 
	 * - Parámetros de entrada: HttpExchange httpExchange. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */
	
	private void handlePOSTResponse(HttpExchange httpExchange)
			throws IOException, AddressException, MessagingException {

		String htmlResponse = "";

		if(tipoPost==1) {
			temperaturaTermostato = Integer.parseInt(post.split("=")[1]);
			htmlResponse = "Respuesta a la petición POST: " + temperaturaTermostato;
		}
		else if (tipoPost == 2) {

			emailRemitente = post.split(";")[0].split("=")[1];
			contraseñaRemitente = post.toString().split(";")[1].split("=")[1];
			htmlResponse = "Respuesta a la petición POST: " + emailRemitente + "     -     " + contraseñaRemitente;
			Properties props = System.getProperties();
			props.put("mail.smtp.host", hostEmail);
			props.put("mail.smtp.user", emailRemitente);
			props.put("mail.smtp.clave", contraseñaRemitente);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", 587);
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailRemitente));
			message.addRecipients(Message.RecipientType.TO, emailTecnico);
			message.addRecipients(Message.RecipientType.TO, emailLordStark);
			message.setSubject("AVERIA");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("ALERTA AUTOMÁTICA");
			BodyPart messageBodyPart2 = new MimeBodyPart();
			DataSource img = new FileDataSource(UrlImg);
			messageBodyPart2.setDataHandler(new DataHandler(img));
			messageBodyPart2.setFileName(UrlImg);
			BodyPart messageBodyPart3 = new MimeBodyPart();
			DataSource pdf = new FileDataSource(UrlPdf);
			messageBodyPart3.setDataHandler(new DataHandler(pdf));
			messageBodyPart3.setFileName(UrlPdf);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart3);
			message.setContent(multipart);
			Transport transport = session.getTransport("smtp");
			transport.connect(hostEmail, emailRemitente, contraseñaRemitente);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		OutputStream os = httpExchange.getResponseBody();
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		os.write(htmlResponse.getBytes());
		os.flush();
		os.close();

	}

	/*
	 * - Método: regularTemperatura
	 * - Descripción: Este método regula la temperatura actual con intervalos de 
	 *   5 segundos para que así sea la misma que la temperatura del termostato. 
	 * - Parámetros de entrada: Ningún parámetro de entrada. 
	 * - Parámetros de salida: Ningún parámetro de salida.
	 * 
	 */
	
	private void regularTemperatura() throws InterruptedException {
		if (tipoPost == 1) {
			if (temperaturaActual < temperaturaTermostato) {
				for (int i = temperaturaActual; i < temperaturaTermostato; i++) {
					temperaturaActual++;
					Thread.sleep(5000);
				}
			} else {
				for (int i = temperaturaActual; i > temperaturaTermostato; i--) {
					temperaturaActual--;
					Thread.sleep(5000);
				}
			}
		}

	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParamValue = null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			if (handleGetRequest(httpExchange)) {
				handleGETResponse(httpExchange);
			}

		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			if (handlePostRequest(httpExchange)) {
				try {
					handlePOSTResponse(httpExchange);
					regularTemperatura();
				} catch (AddressException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}