package br.com.b2w.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import br.com.b2w.exception.GenericException;
import br.com.b2w.utils.DateUtils;

@RunWith(JUnitPlatform.class)
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
		assertEquals(1, localDate.getDayOfMonth());
		assertEquals(2, localDate.getMonthValue());
		assertEquals(2017, localDate.getYear());
	}
	
	@Test
	public void convertLocalDateToZonedDateTimeTest(){
		LocalDate localDate = DateUtils.convertStringToLocalDate(STRING_LOCAL_DATE_VALIDA);
		ZonedDateTime zonedDateTime = DateUtils.convertLocalDateToZonedDateTime(localDate);
		assertEquals(1, zonedDateTime.getDayOfMonth());
		assertEquals(2, zonedDateTime.getMonthValue());
		assertEquals(2017, zonedDateTime.getYear());
	}
	
	@Test
	public void convertStringToZonedDateTimeTest(){
		String stringZonedDateTime = STRING_ZONED_DATE_TIME_VALIDA;
		ZonedDateTime zonedDateTime = DateUtils.convertStringToZonedDateTime(stringZonedDateTime);
		assertEquals(1, zonedDateTime.getDayOfMonth());
		assertEquals(10, zonedDateTime.getMonthValue());
		assertEquals(2016, zonedDateTime.getYear());
	}
	
	@Test
	public void convertStringToLocalDateErrorTest() {

		String stringLocalDate = STRING_LOCAL_DATE_INVALIDA;
		assertThrows(GenericException.class, () -> {
			DateUtils.convertStringToLocalDate(stringLocalDate);
		});
	}
	
	public void convertStringToZonedDateTimeErrorTest(){
		String stringZonedDateTime = STRING_ZONED_DATE_TIME_INVALIDA;
		assertThrows(GenericException.class, () -> {
			DateUtils.convertStringToZonedDateTime(stringZonedDateTime);
		});
	}
	
	@Test
	public void validarZonedDateTimeTest(){
		ZonedDateTime beginZdt = DateUtils.convertStringToZonedDateTime(STRING_END_ZONED_DATE_TIME);
		ZonedDateTime endZdt = DateUtils.convertStringToZonedDateTime(STRING_BEGIN_ZONED_DATE_TIME);
		assertThrows(GenericException.class, () -> {
			DateUtils.validarZonedDateTime(beginZdt, endZdt);
		});
	}
	
	@Test
	public void validarVazioNuloTest(){
		DateUtils.validarVazioNulo(optString, optString);
	}
	
	public void validarVazioNuloErrorVazioTest(){
		DateUtils.validarVazioNulo(optVazioValue, optString);
	}
	
	public void validarVazioNuloErrorNullTest(){
		DateUtils.validarVazioNulo(optNullvalue, optString);
	}
}




