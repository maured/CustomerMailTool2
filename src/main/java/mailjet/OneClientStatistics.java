package mailjet;

//import com.edeal.data.treatment.InfoConnexionClient;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Apikey;
import com.mailjet.client.resource.Campaign;
import com.mailjet.client.resource.Campaignstatistics;
import dma.test.restconnexion.InfoConnexionClient;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class OneClientStatistics {
	
	public static MailjetClient getAccessToSpecificClient(InfoConnexionClient infoConnexionClient) throws MailjetSocketTimeoutException, MailjetException {
		MailjetClient client;

		String pubKey;
		String privKey;

		pubKey = infoConnexionClient.getPubKey();
		privKey = infoConnexionClient.getPrivKey();

		client = new MailjetClient(pubKey, privKey);

		return client;
	}
	
	public static JSONArray getNameToSpecificClient(InfoConnexionClient infoConnexionClient) throws MailjetSocketTimeoutException, MailjetException {

		MailjetClient client = getAccessToSpecificClient(infoConnexionClient);
		MailjetRequest request;
		MailjetResponse response;

		request = new MailjetRequest(Apikey.resource); // i get the name (and other useless informations) here with Apikey.resource
		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		return clientData;
	}
	
	public static JSONArray campaignID(InfoConnexionClient infoConnexionClient) throws MailjetSocketTimeoutException, MailjetException {
		MailjetClient client = getAccessToSpecificClient(infoConnexionClient);
		MailjetRequest request;
		MailjetResponse response;

		request = new MailjetRequest(Campaign.resource); // This instance is only for E-Deal CampaignID.
				
		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		return clientData;
	}

	public static JSONArray otherCampaignStats(InfoConnexionClient infoConnexionClient) throws MailjetSocketTimeoutException, MailjetException {
		MailjetClient client = getAccessToSpecificClient(infoConnexionClient);
		MailjetRequest request;
		MailjetResponse response;
		//		boolean boovar = true;
		//		String str = String.valueOf(boovar);
		//Campaign Name, Sender, Send date, Number mails sent.  Campaignoverview .filter(Campaignoverview.ALL, str);
		request = new MailjetRequest(Campaignstatistics.resource)
				.filter(Campaignstatistics.MINLASTACTIVITYAT, "2016-01-01") //Date to define.
				.filter(Campaignstatistics.MAXLASTACTIVITYAT, "2018-06-06");
		
		response = client.get(request);

		JSONArray clientData = response.getData();
		

		System.out.println(clientData);
		return clientData;
	}
}

//dans le but de laisser le front s'occuper de traiter le json j'ai mit ça en commentaire
//		String json;
//		json = allTreatmentFromMailjetResponse(clientData);
//
//		Gson gson = new Gson();
//		InfoApiKeyForOneClient infoApiKeyForOneClient = gson.fromJson(json, InfoApiKeyForOneClient.class);
//
//		System.out.print(infoApiKeyForOneClient.getName());
//Mon API key95901f5853673f7593f1c84d9ff824c7, API key priv7dccb2162f16f24c0b20b44fb834ff17