package dataManagement;

import de.fhpotsdam.unfolding.geo.Location;
import globals.Main;
import globals.ProcessingSingleton;

public class DataManager {
	Main p5;
	
	public DataManager(){
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
	}
	
	public void loadCsvFrom(String path){
		
		String[] dataLines = loadStrings("atencion-ciudadana-2017_short.csv");
		locations = new Location[dataLines.length];
		println("DataSize: " + locations.length);
		
		// NO HEADERS ON THIS CSV
		for (int i = 0; i < dataLines.length; i++) {
			String[] data = split(dataLines[i], ";");
			float lat = Float.parseFloat(data[13]);
			float lng = Float.parseFloat(data[14]);
			//println("Lat: "+lat+"\nLong: "+lng);
			locations[i] = new Location(lat, lng);

			if (lng < leftLongitude)
				leftLongitude = lng;
			if (lng > rightLongitude)
				rightLongitude = lng;
			if (lat < bottomLatitude)
				bottomLatitude = lat;
			if (lat > topLatitude)
				topLatitude = lat;
		}

		println("LEFT: " + leftLongitude);
		println("RIGHT: " + rightLongitude);
		println("BOTTOM: " + bottomLatitude);
		println("TOP: " + topLatitude);
	}
}
