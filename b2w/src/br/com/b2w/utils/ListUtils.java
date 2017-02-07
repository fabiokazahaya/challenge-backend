package br.com.b2w.utils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.b2w.exception.GenericException;
import br.com.b2w.model.Item;

public class ListUtils {
	
	static final Logger LOG = LoggerFactory.getLogger(ListUtils.class);
	
	public static List<Item> getListOfItem(ZonedDateTime beginZdt, ZonedDateTime endZdt, List<Item> listOfZonedDateTime) {
		try {
			return listOfZonedDateTime.parallelStream()
					.filter(s -> DateUtils.convertStringToZonedDateTime(s.getDate())
					.isBefore(endZdt) && DateUtils.convertStringToZonedDateTime(s.getDate())
					.isAfter(beginZdt))
					.collect(Collectors.toList());
		} catch (RuntimeException e) {
			LOG.error("Erro ao recuperar a lista de Items.", e);
			throw new GenericException("Erro ao recuperar a lista de Items.", e);
		}
	}
}



