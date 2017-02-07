package br.com.b2w.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.b2w.exception.GenericException;

public class HttpUtils {

	static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

	public static String recuperarItemAPI(String url) {
		StringBuffer response = null;

		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			String inputLine;

			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			LOG.error("Erro ao consumir a API de Item", e);
			throw new GenericException("Erro ao consumir a API de Item", e);
		} catch (ProtocolException e) {
			LOG.error("Erro ao consumir a API de Item", e);
			throw new GenericException("Erro ao consumir a API de Item", e);
		} catch (IOException e) {
			LOG.error("Erro ao consumir a API de Item", e);
			throw new GenericException("Erro ao consumir a API de Item", e);
		}
		return response.toString().trim();
	}
}



