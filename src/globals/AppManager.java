package globals;

import map.MapManager;
import dataManagement.DataManager;
import peasy.*;

public class AppManager {

	Main p5;
	PeasyCam camera;
	DataManager dataManager;
	MapManager mapManager;
	
	public AppManager(){
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
		init();
	}
	
	public void init(){
		
		dataManager = new DataManager();
		loadDataSets();
		
		mapManager = new MapManager();
		mapManager.enableMouseInteraction(true);
		
		camera = new PeasyCam(p5, p5.width);
		camera.setMinimumDistance(20);
		camera.setMaximumDistance(3000);
		camera.setActive(false);
	}
	
	private void loadDataSets() {
		dataManager.loadDataSet("atencion-ciudadana-2017_short.csv");
		
		//MANUALLY CHECK FIRST ROW OF FIRST DATASET
		String[] dataSetRow0 = dataManager.getDataSetById(0).getRowAsArray(0);
		p5.printArray(dataSetRow0);
	}

	public void update(){
		
	}
	
	public void render(){
		
		p5.pushMatrix();
		p5.translate(-(p5.width * 0.5f), -(p5.height * 0.5f));
		
		drawMap();
		
		p5.popMatrix();
		
	}
	
	private void drawMap(){
		mapManager.renderMap();
	}
	
	public void switchControlBetweenCamAndMap(){
		mapManager.enableMouseInteraction(!mapManager.enableMouseInteraction);
		camera.setActive(!camera.isActive());
	}

	public void keyPressed(char key) {
		if (key == ' ') {
			switchControlBetweenCamAndMap();
		}

		if (key == 'c') {
			//camera.setActive(!camera.isActive());
		}
		
		if (key == 'b') {
			mapManager.panAndZoomToBuenosAires();
		}
		
		if (key == 'p') {
			mapManager.printCurrentCoordinates();
		}
		
	}
}