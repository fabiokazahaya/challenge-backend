package br.com.b2w.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.b2w.exception.GenericException;

public class DateUtils {
	
	static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
	
	public static ZonedDateTime convertLocalDateToZonedDateTime(LocalDate beginLocalDate) {
		try{
			return beginLocalDate.atStartOfDay(ZoneOffset.UTC).withNano(40000000);
		}catch (DateTimeException  e) {
			LOG.error("Erro ao converter LocalDate para ZoneDateTime.", e);
			throw new GenericException("Erro ao converter para ZoneDateTime.", e);
		}
	}
	
	public static LocalDate convertStringToLocalDate(String begindate) {
		try{
			return LocalDate.parse(begindate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}catch (DateTimeParseException e) {
			LOG.error("Erro ao converter String para LocalDate.", e);
			throw new GenericException("Erro ao converter para LocalDate.", e);
		}
	}
	
	public static ZonedDateTime convertStringToZonedDateTime(String value) {
		try {
			return ZonedDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
		} catch (DateTimeParseException e) {
			LOG.error("Erro ao converter String para ZonedDateTime.", e);
			throw new GenericException("Erro ao converter String para ZonedDateTime.", e);
		}
	}
}



