package dma.test.restconnexion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
@CrossOrigin("http://192.068.1.110:3003")
@RequestMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	
	private final InfoConnexionDAO infoConnexionDAO;

	@Autowired
	public LoginController(InfoConnexionDAO infoConnexionDAO) {
		this.infoConnexionDAO = infoConnexionDAO;
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public Collection<InfoConnexionClient> listInfos(@RequestBody InfoConnexionClient infoConnexionClient) {
		return infoConnexionDAO.list();
	}

	@PostMapping //(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public InfoConnexionClient addInfos(@RequestBody InfoConnexionClient infoConnexionClient) {
//		System.out.println(infoConnexionClient.getPubKey());
//		System.out.println(infoConnexionClient.getPrivKey());
		return infoConnexionDAO.add(infoConnexionClient);
	}
}