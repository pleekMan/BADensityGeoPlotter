package dataManagement;

import java.util.ArrayList;

import processing.data.Table;
//import de.fhpotsdam.unfolding.geo.Location;
import globals.Main;
import globals.ProcessingSingleton;

public class DataManager {
	Main p5;
	ArrayList<DataSet> dataSets;
	
	public DataManager(){
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
		dataSets = new ArrayList<DataSet>();
	}
	
	public void loadDataSet(String pathInDataFolder){
		
		Table newTable = new Table();
		newTable = p5.loadTable(pathInDataFolder);
		String dataSetName = pathInDataFolder.substring(0, pathInDataFolder.length() - 3);
		DataSet newDataSet = new DataSet(dataSetName, newTable);
		dataSets.add(newDataSet);
		
		/*
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
		*/
	}
	
	public DataSet getDataSetByName(String dataSetName){
		DataSet tableToReturn = null;
		for (int i = 0; i < dataSets.size(); i++) {
			if(dataSets.get(i).getName().equals(dataSetName)){
				tableToReturn = getDataSet(i);
				break;
			}
		}
		return tableToReturn;
	}
	
	public DataSet getDataSetById(int dataSetId){
		return getDataSet(dataSetId);
	}
	
	private DataSet getDataSet(int idInList){
		return dataSets.get(idInList);
	}
}
