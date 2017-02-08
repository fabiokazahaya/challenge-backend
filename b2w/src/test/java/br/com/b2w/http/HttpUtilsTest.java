package br.com.b2w.http;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import br.com.b2w.exception.GenericException;
import br.com.b2w.utils.HttpUtils;

@RunWith(JUnitPlatform.class)
public class HttpUtilsTest {
	
	static final String URL_API_ITEM = "http://www.mocky.io/v2/5817803a1000007d01cc7fc9";
	static final String URL_API_ITEM_BROKEN = "http://www.mocky.io/v2/5817803da1000007d01cc7fc9";
	static final String URL_API_ITEM_NULL = null;
	
	@Test
	public void testURL(){
		assertNotNull(HttpUtils.recuperarItemAPI(URL_API_ITEM));
	}
	
	@Test
	public void testURBroken(){
		assertThrows(GenericException.class, () -> {
			HttpUtils.recuperarItemAPI(URL_API_ITEM_BROKEN);
		});
	}
	
	@Test
	public void testURBrokenNull(){
		assertThrows(GenericException.class, () -> {
			HttpUtils.recuperarItemAPI(URL_API_ITEM_NULL);
		});
	}
}




