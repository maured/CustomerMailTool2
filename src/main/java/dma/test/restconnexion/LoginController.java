package dma.test.restconnexion;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import data.treatment.GetDate;
import exception.MyException;
import mailjet.MailJetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
	// instantiated at null for security 
	private static MailJetDAO mailJetDAO = null;

	//Implementation of @Autowired to avoid many other configuration in another files 
	@Autowired
	public LoginController() {
		
	}
	
	/* -------------------------------------------------------------------------------------------------------
	Tant qu'on ne passe pas par ma connexion ici, on accède pas aux autres requêtes
 */
	@CrossOrigin(origins = "*", allowedHeaders = "*")//http://192.068.1.110:3003
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	//ici Spring m'instancie ma class InfoConnexionClient pour avoir accès aux attributs clé priv et clé pub
	public @ResponseBody String addInfos(@RequestBody InfoConnexionClient infoConnexionClient) {
		/* ici on instancie un mailJetDAO dans le but d'avoir un point d'accès global aux info de connexion */
		mailJetDAO = new MailJetDAO(infoConnexionClient);
		String str;
		str = mailJetDAO.isOk();
		
		System.out.println(infoConnexionClient.getPubKey());
		System.out.println(infoConnexionClient.getPrivKey());
		return str;
	}

	/* -------------------------------------------------------------------------------------------------------
	Sachant que toutes les methodes de l'api Mailjet vont être implémentées dans mailJetDAO, pour chaque nouvelles
	routes je n'aurais plus qu'à appeler un mailJetDAO.maMethode(). 
*/
	
//This one send back a json with the name Value
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/one-client-stat", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String sendClientInfo() throws MailjetSocketTimeoutException, MailjetException {
		if(mailJetDAO != null) {
			return mailJetDAO.concatNameCampaignStatsId();
		}
		MyException myException = new MyException();
		return myException.badRequest();
	}

	/*pour la date car mailjet nous renvoie que 10 donnés par appel*/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE) // La route reste à définir encore 
	@ResponseStatus(value = HttpStatus.OK)
	public String getClientDateInfo(@RequestBody GetDate date) throws MailjetSocketTimeoutException, MailjetException {
		
		if(mailJetDAO != null) {
			return mailJetDAO.concatNameCampaignStatsId();
		}
		MyException myException = new MyException();
		return myException.badRequest();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/testO", method = RequestMethod.GET) 
	@ResponseStatus(value = HttpStatus.OK)
	public String campaignOverview() throws MailjetSocketTimeoutException, MailjetException {

		if(mailJetDAO != null) {
			return mailJetDAO.testCampaign();
		}
		MyException myException = new MyException();
		return myException.badRequest();
	}
}