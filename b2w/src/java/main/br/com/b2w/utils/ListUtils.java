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

	public static List<Item> getListOfItem(ZonedDateTime beginZdt, ZonedDateTime endZdt, List<Item> listOfItemApi) {
		List<Item> listOfItemsRange = listOfItemApi.parallelStream()
				.filter(s -> DateUtils.convertStringToZonedDateTime(s.getDate())
				.isBefore(endZdt) && DateUtils.convertStringToZonedDateTime(s.getDate())
				.isAfter(beginZdt))
				.collect(Collectors.toList());
		if (listOfItemsRange.isEmpty()) {
			LOG.error("Lista vazia, tente novamente");
			throw new GenericException("Lista vazia, tente novamente");
		}
		return listOfItemsRange;
	}
}



