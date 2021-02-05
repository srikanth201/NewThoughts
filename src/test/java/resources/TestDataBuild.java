package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public  AddPlace addPlacePayload(String name, String langauge, String address)
	
	{
		
		AddPlace ap=new AddPlace();
		ap.setAccuracy(30);
		ap.setName(name);
		ap.setPhone_number("9866333454");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(langauge);
		ArrayList a=new ArrayList();
		a.add("caps");
		a.add("NonCaps");
		
		ap.setTypes(a);
		Location l=new Location();
		l.setLat(-32.56);
		l.setLng(35.67);
		ap.setLocation(l);
		
		return ap;
	}

}
