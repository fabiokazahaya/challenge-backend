package br.com.b2w.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import br.com.b2w.exception.GenericException;
import br.com.b2w.model.Dimension;
import br.com.b2w.model.Item;
import br.com.b2w.utils.DateUtils;
import br.com.b2w.utils.ListUtils;

@RunWith(JUnitPlatform.class)
public class ListUtilsTest {

	@Test
	public void getListOfItemTest() {

		String date1 = "2016-10-01T14:30:37.040Z";
		String date2 = "2016-10-02T14:30:37.040Z";
		String date3 = "2016-10-03T14:30:37.040Z";
		String date4 = "2016-10-04T14:30:37.040Z";
		String date5 = "2016-10-05T14:30:37.040Z";

		Dimension dimension = new Dimension();
		dimension.setWeight(10.5);
		dimension.setHeight(10.5);
		dimension.setWidth(10.5);
		dimension.setWeight(10.5);
		dimension.setLength(10.5);

		Item item1 = new Item();
		item1.setName("Celular");
		item1.setCode(1);
		item1.setDate(date1);
		item1.setDimension(dimension);

		Item item2 = new Item();
		item2.setName("Xbox");
		item2.setCode(2);
		item2.setDate(date2);
		item2.setDimension(dimension);

		Item item3 = new Item();
		item3.setName("Televisao");
		item3.setCode(3);
		item3.setDate(date3);
		item3.setDimension(dimension);

		Item item4 = new Item();
		item4.setName("Caixa de Som");
		item4.setCode(4);
		item4.setDate(date4);
		item4.setDimension(dimension);

		Item item5 = new Item();
		item5.setName("Impressora");
		item5.setCode(5);
		item5.setDate(date5);
		item5.setDimension(dimension);

		List<Item> listOfItemApi = Arrays.asList(item1, item2, item3, item4, item5);
		ZonedDateTime beginZdt = DateUtils.convertStringToZonedDateTime("2016-10-01T00:00:00.040Z");
		ZonedDateTime endZdt = DateUtils.convertStringToZonedDateTime("2016-10-04T18:00:00.040Z");
		assertEquals(4, ListUtils.getListOfItem(beginZdt, endZdt, listOfItemApi).size());
	}

	@Test
	public void getListOfItemTestBlank() {

		ZonedDateTime beginZdt = DateUtils.convertStringToZonedDateTime("2016-10-01T00:00:00.040Z");
		ZonedDateTime endZdt = DateUtils.convertStringToZonedDateTime("2016-10-04T18:00:00.040Z");
		List<Item> listOfItemApi = new ArrayList<Item>();
		assertThrows(GenericException.class, () -> {
			ListUtils.getListOfItem(beginZdt, endZdt, listOfItemApi);
		});
	}
}




