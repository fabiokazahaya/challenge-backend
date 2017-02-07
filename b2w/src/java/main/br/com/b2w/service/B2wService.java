package br.com.b2w.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.b2w.exception.GenericException;
import br.com.b2w.model.Item;
import br.com.b2w.utils.DateUtils;
import br.com.b2w.utils.GsonUtils;
import br.com.b2w.utils.HttpUtils;
import br.com.b2w.utils.ListUtils;

@Path("/b2w")
public class B2wService {

	static final Logger LOG = LoggerFactory.getLogger(B2wService.class);

	static final String BEGIN_DATE = "begindate";
	static final String FINAL_DATE = "finaldate";
	static final String URL_API_ITEM = "http://www.mocky.io/v2/5817803a1000007d01cc7fc9";

	@GET
	@Path("/item")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateInfo(@QueryParam(BEGIN_DATE) String begindate, @QueryParam(FINAL_DATE) String finaldate)
			throws GenericException {

		Optional<String> begindateOpt = Optional.ofNullable(begindate);
		Optional<String> finaldateOpt = Optional.ofNullable(finaldate);

		LOG.info("Validar begindate/finaldate esta vazia ou nulo");
		DateUtils.validarVazioNulo(begindateOpt, finaldateOpt);
		
		LOG.info("Converter {} e {} para LocalDate.", BEGIN_DATE, FINAL_DATE);
		LocalDate beginLocalDate = DateUtils.convertStringToLocalDate(begindateOpt.get());
		LocalDate finalLocalDate = DateUtils.convertStringToLocalDate(finaldateOpt.get());

		LOG.info("Converter LocalDate {} e {} para ZonedDateTime.", beginLocalDate, finalLocalDate);
		ZonedDateTime beginZdt = DateUtils.convertLocalDateToZonedDateTime(beginLocalDate);
		ZonedDateTime endZdt = DateUtils.convertLocalDateToZonedDateTime(finalLocalDate);
		
		LOG.info("Validar begindate > finaldate");
		DateUtils.validarZonedDateTime(beginZdt, endZdt);

		LOG.info("Converter Gson Item API para Object Item");
		List<Item> listOfItemAPI = GsonUtils.convertGsonToObject(HttpUtils.recuperarItemAPI(URL_API_ITEM));

		LOG.info("Recuperar lista de Item entre {} e {}", BEGIN_DATE, FINAL_DATE);
		List<Item> listOfItemRange = ListUtils.getListOfItem(beginZdt, endZdt, listOfItemAPI);

		LOG.info("Retornar lista de Item entre {} e {}", BEGIN_DATE, FINAL_DATE);
		return Response.ok(GsonUtils.convertStringToGson(listOfItemRange), MediaType.APPLICATION_JSON).build();
	}
}



