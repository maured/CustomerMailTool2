package dma.test.restconnexion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;


//@RequestMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE) //
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")//http://192.068.1.110:3003
public class LoginController {
	
	private final InfoConnexionDAO infoConnexionDAO;

	@Autowired
	public LoginController(InfoConnexionDAO infoConnexionDAO) {
		this.infoConnexionDAO = infoConnexionDAO;
	}

	@CrossOrigin(origins = "http://192.068.1.110:3003")
	@RequestMapping(value = "/auth/login", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE) //
	@ResponseStatus(value = HttpStatus.OK)
	public String test(){
		
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		
		return gson.toJson("c'est OK");
	}
	
//	@CrossOrigin(origins = "*", allowedHeaders = "*")//http://192.068.1.110:3003
//	@RequestMapping(value = "/auth/login", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE) //
//	@ResponseStatus(value = HttpStatus.ACCEPTED)
//	public Collection<InfoConnexionClient> listInfos() {
//		return infoConnexionDAO.list();
//	}

	//@PostMapping //(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@CrossOrigin(origins = "*", allowedHeaders = "*")//http://192.068.1.110:3003
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public InfoConnexionClient addInfos(@RequestBody InfoConnexionClient infoConnexionClient) {
		System.out.println("je passe dans addInfos");
		return infoConnexionDAO.add(infoConnexionClient);
	}
}