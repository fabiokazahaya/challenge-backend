package br.com.b2w.utils;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

import br.com.b2w.exception.GenericException;
import br.com.b2w.model.Item;

public class GsonUtils {
	
	static final Logger LOG = LoggerFactory.getLogger(GsonUtils.class);
	
	public static List<Item> convertGsonToObject(String gsonString) {
		try {
			return new ObjectMapper().readValue(gsonString,TypeFactory.defaultInstance().constructCollectionType(List.class, Item.class));
		} catch (IOException e) {
			LOG.error("Erro ao converter Gson para Itens", e);
			throw new GenericException("Erro ao converter Gson para Itens", e);
		}
	}
	
	public static String convertStringToGson(List<Item> listOfPeriod){
		return GsonUtils.criarGson().toJson(listOfPeriod);
	}
	
	public static Gson criarGson(){
		Gson gson = new Gson();
		return gson;
	}
}



