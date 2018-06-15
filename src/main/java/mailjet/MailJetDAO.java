package mailjet;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Apikey;
import com.mailjet.client.resource.Campaign;
import com.mailjet.client.resource.Campaignoverview;
import com.mailjet.client.resource.Campaignstatistics;
import data.treatment.GetDate;
import dma.test.restconnexion.InfoConnexionClient;
import org.json.JSONArray;
import org.json.JSONObject;
import static data.treatment.TreatmentJsonData.allTreatmentFromMailjetResponse;

public class MailJetDAO{
	//tant que nous avons une instance de mailJet DAO (que la JVM tourne) les infos de connxion seront stockées dans mon objet connexion
	private InfoConnexionClient connexion;
	
	//private String dateTime = "1970-01-02T13:00:00";
	
	public MailJetDAO(InfoConnexionClient infoConnexionClient) {
		this.connexion = infoConnexionClient;
	}

	public String isOk() {
		JSONObject obj = new JSONObject();
		obj.put("message", "it's work you will be redirected");

		return String.valueOf(obj);
	}

	public  MailjetClient getAccessToSpecificClient() throws MailjetSocketTimeoutException, MailjetException {
		MailjetClient client;

		String pubKey;
		String privKey;

		pubKey = this.connexion.getPubKey();
		privKey = this.connexion.getPrivKey();

		client = new MailjetClient(pubKey, privKey);

		return client;
	}

	public String getNameToSpecificClient() throws MailjetSocketTimeoutException, MailjetException {

		MailjetClient client = getAccessToSpecificClient();
		MailjetRequest request;
		MailjetResponse response;

		request = new MailjetRequest(Apikey.resource); // i get the name (and other useless information) here with Apikey.resource
		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		String json;
		json = allTreatmentFromMailjetResponse(clientData);
		
		return json;
	}

	public String campaignID() throws MailjetSocketTimeoutException, MailjetException { //changer le nom en OtherCampaign stat

		
		MailjetClient client = getAccessToSpecificClient();
		MailjetRequest request;
		MailjetResponse response;
		String dateTime =  GetDate.dateTime;
		
		request = new MailjetRequest(Campaign.resource) // E-Deal CampaignID, Subject, 
				.filter(Campaign.FROMTS, dateTime);

		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		String json;
		json = allTreatmentFromMailjetResponse(clientData);
		return json;
	}

	public String otherCampaignStats() throws MailjetSocketTimeoutException, MailjetException {
		
		MailjetClient client = getAccessToSpecificClient();
		MailjetRequest request;
		MailjetResponse response;
		String dateTime =  GetDate.dateTime; //Access to the date informed by user.
		
		request = new MailjetRequest(Campaignstatistics.resource) // This method is only for processedCount value, witch represent number of mail send in this campaign.
				.filter(Campaign.FROMTS, dateTime); //Date send by user.
		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		String json;
		json = allTreatmentFromMailjetResponse(clientData);
		return json;
	}
	
	/* Here i must create this method to return all information needed by the front-end Mailjet "Client" information in only one Json Object */
	/* To be clear i regroup the Name, the Edeal campaignID and the other Campaign stats (informed in my above comment)*/
	public String concatNameCampaignStatsId() throws MailjetSocketTimeoutException, MailjetException {
		String globalOneClientInfos;
		String getNameToSpecificClientStr;
		String campaignIDStr;
		String otherCampaignStatsStr;
		
		getNameToSpecificClientStr = getNameToSpecificClient();
		campaignIDStr = campaignID();
		otherCampaignStatsStr = otherCampaignStats();
		
		globalOneClientInfos = getNameToSpecificClientStr + ',' + campaignIDStr + ',' + otherCampaignStatsStr;
		return globalOneClientInfos;
	}

	public String testCampaign() throws MailjetSocketTimeoutException, MailjetException { //changer le nom en OtherCampaign stat


		MailjetClient client = getAccessToSpecificClient();
		MailjetRequest request;
		MailjetResponse response;
		String dateTime =  GetDate.dateTime;

		request = new MailjetRequest(Campaignoverview.resource) // E-Deal CampaignID, Subject, 
				.filter(Campaignoverview.SENT, "true");

		response = client.get(request);

		JSONArray clientData = response.getData();

		System.out.print(clientData);
		String json;
		json = allTreatmentFromMailjetResponse(clientData);
		return json;
	}
	
}
