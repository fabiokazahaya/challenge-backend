package br.com.b2w.date;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.b2w.exception.GenericException;
import br.com.b2w.utils.DateUtils;
import junit.framework.Assert;

public class DateUtilsTest {
	
	static String STRING_LOCAL_DATE_VALIDA = "01-02-2017";
	static String STRING_LOCAL_DATE_INVALIDA = "50-02-2017";
	static String STRING_ZONED_DATE_TIME_VALIDA = "2016-10-01T14:30:37.040Z";
	static String STRING_ZONED_DATE_TIME_INVALIDA = "2016-10/01Ts14:30:37.040Z";
	static String STRING_BEGIN_ZONED_DATE_TIME = "2016-10-06T14:30:37.040Z";
	static String STRING_END_ZONED_DATE_TIME = "2016-10-06T14:30:38.040Z";
	Optional<String> optVazioValue = Optional.ofNullable("");
	Optional<String> optNullvalue = Optional.ofNullable(null);
	Optional<String> optString = Optional.of(STRING_LOCAL_DATE_VALIDA); 
	
	@Test
	public void convertStringToLocalDateTest(){
		LocalDate localDate = DateUtils.convertStringToLocalDate(STRING_LOCAL_DATE_VALIDA);
		Assert.assertEquals(1, localDate.getDayOfMonth());
		Assert.assertEquals(2, localDate.getMonthValue());
		Assert.assertEquals(2017, localDate.getYear());
	}
	
	@Test
	public void convertLocalDateToZonedDateTimeTest(){
		LocalDate localDate = DateUtils.convertStringToLocalDate(STRING_LOCAL_DATE_VALIDA);
		ZonedDateTime zonedDateTime = DateUtils.convertLocalDateToZonedDateTime(localDate);
		Assert.assertEquals(1, zonedDateTime.getDayOfMonth());
		Assert.assertEquals(2, zonedDateTime.getMonthValue());
		Assert.assertEquals(2017, zonedDateTime.getYear());
	}
	
	@Test
	public void convertStringToZonedDateTimeTest(){
		String stringZonedDateTime = STRING_ZONED_DATE_TIME_VALIDA;
		ZonedDateTime zonedDateTime = DateUtils.convertStringToZonedDateTime(stringZonedDateTime);
		Assert.assertEquals(1, zonedDateTime.getDayOfMonth());
		Assert.assertEquals(10, zonedDateTime.getMonthValue());
		Assert.assertEquals(2016, zonedDateTime.getYear());
	}
	
	@Test(expected=GenericException.class)
	public void convertStringToLocalDateErrorTest(){
		String stringLocalDate = STRING_LOCAL_DATE_INVALIDA;
		LocalDate localDate = DateUtils.convertStringToLocalDate(stringLocalDate);
		Assert.assertEquals(1, localDate.getDayOfMonth());
	}
	
	@Test(expected=GenericException.class)
	public void convertStringToZonedDateTimeErrorTest(){
		String stringZonedDateTime = STRING_ZONED_DATE_TIME_INVALIDA;
		ZonedDateTime zonedDateTime = DateUtils.convertStringToZonedDateTime(stringZonedDateTime);
		Assert.assertEquals(1, zonedDateTime.getDayOfMonth());
		Assert.assertEquals(10, zonedDateTime.getMonthValue());
		Assert.assertEquals(2016, zonedDateTime.getYear());
	}
	
	@Test
	public void validarZonedDateTimeTest(){
		ZonedDateTime beginZdt = DateUtils.convertStringToZonedDateTime(STRING_BEGIN_ZONED_DATE_TIME);
		ZonedDateTime endZdt = DateUtils.convertStringToZonedDateTime(STRING_END_ZONED_DATE_TIME);
		DateUtils.validarZonedDateTime(beginZdt, endZdt);
	}
	
	@Test(expected=GenericException.class)
	public void validarZonedDateTimeErrorTest(){
		ZonedDateTime beginZdt = DateUtils.convertStringToZonedDateTime(STRING_END_ZONED_DATE_TIME);
		ZonedDateTime endZdt = DateUtils.convertStringToZonedDateTime(STRING_BEGIN_ZONED_DATE_TIME);
		DateUtils.validarZonedDateTime(beginZdt, endZdt);
	}
	
	@Test
	public void validarVazioNuloTest(){
		DateUtils.validarVazioNulo(optString, optString);
	}
	
	@Test(expected=GenericException.class)
	public void validarVazioNuloErrorTest(){
		DateUtils.validarVazioNulo(optVazioValue, optString);
	}
	
	@Test(expected=GenericException.class)
	public void validarVazioNuloErrorVazioTest(){
		DateUtils.validarVazioNulo(optVazioValue, optString);
	}
	
	@Test(expected=GenericException.class)
	public void validarVazioNuloErrorNullTest(){
		DateUtils.validarVazioNulo(optNullvalue, optString);
	}
}



