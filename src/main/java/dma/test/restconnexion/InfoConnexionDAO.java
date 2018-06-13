package dma.test.restconnexion;

import java.util.ArrayList;
import java.util.Collection;

public class InfoConnexionDAO{
	
	private static Collection<InfoConnexionClient> infos;
	
	public InfoConnexionDAO(){
		infos = new ArrayList<>();
	}
	public Collection<InfoConnexionClient> list(){
		return infos;
	}
	
	public InfoConnexionClient add(InfoConnexionClient infoConnexionClient){
		infos.add(infoConnexionClient);
		System.out.println("j'ai bien add les infos dans mon bean");
		return infoConnexionClient;
	}
}
