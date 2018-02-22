package dataManagement;

import java.util.ArrayList;

import processing.data.Table;
import processing.data.TableRow;
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
		//newTable = p5.loadTable(pathInDataFolder);
		// TURNING THE semiColonSeparatedValue FILE INTO CSV BREAKS IT (SOME FIELDS HAVE COMAS)
		// THEREFORE, loadTable IS USELESS, SINCE IT ONLY ACCEPTS csv, tsv or binaries.
		
		String[] fileLines = p5.loadStrings(pathInDataFolder);
		for (String line : fileLines) {
			String[] fields = p5.split(line, ';');
			TableRow newRow = newTable.addRow(fields);
		}
		
		String dataSetName = pathInDataFolder.substring(0, pathInDataFolder.length() - 3);
		DataSet newDataSet = new DataSet(dataSetName, newTable);
		dataSets.add(newDataSet);

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
