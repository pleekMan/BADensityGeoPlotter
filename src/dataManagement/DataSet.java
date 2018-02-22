package dataManagement;

import java.util.ArrayList;
import java.util.Arrays;

import processing.data.Table;
import processing.data.TableRow;

public class DataSet {
	String name;
	//ArrayList<ArrayList<String>> data;
	
	Table dataTable;
	
	
	public DataSet(String _name, Table inTable){
		name = _name;
		//data = new ArrayList<ArrayList<String>>();
		dataTable = inTable;
	}
	
	public String[] getRowAsArray(int rowNumber){
		TableRow row = dataTable.getRow(rowNumber);
		String[] rowAsArray = new String[row.getColumnCount()];
		for (int i = 0; i < rowAsArray.length; i++) {
			rowAsArray[i] = row.getString(i);
		}
		return rowAsArray;
	}
	
	public Table getData(){
		return dataTable;
	}
	
	public String getName(){
		return name;
	}
}
