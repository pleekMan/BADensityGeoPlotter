package viz;

import java.util.ArrayList;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PVector;
import globals.Main;
import globals.ProcessingSingleton;

public class Grid {
	Main p5;
	ArrayList<PVector> nodes;
	ArrayList<Location> geoLocations;
	ArrayList<ScreenPosition> screenLocations;
	
	int hRes, vRes;
	PVector origin;
	PVector size;

	
	public Grid() {
		
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
		
		// THE nodes PVector could by arranged this way:
		// x: normX, y: normY, z:value after density calculation
		
		nodes = new ArrayList<PVector>();
		geoLocations = new ArrayList<Location>();
		screenLocations = new ArrayList<ScreenPosition>();
		
		hRes = vRes = 30;
		origin = new PVector();
		size = new PVector();
		
		createGrid();
	}
	
	public void update(){
		
	}
	public void render(){
		
	}
	
	public void createGrid(){
		
		float resUnit = 1.0f / hRes;
		float x = 0;
		float y = 0;
		for (int i = 0; i < hRes * vRes; i++) {
			
			PVector newNode = new PVector(x,y,0);
			nodes.add(newNode);

			if((i%hRes) >= hRes - 1){
				x = 0;
				y += resUnit;
			} else {
				x+= resUnit;
			}
		}
	}
	
	public ArrayList<PVector> getNodes(){
		return nodes;
	}
	
	public void populateGeoLocations(ArrayList<Location> locs){
		geoLocations = locs;
		p5.println("-|| GeoLocations.size() in Grid: " + geoLocations.size());
	}
	
	public ArrayList<Location> getGeoLocations(){
		return geoLocations;
	}
	
	public void setOrigin(float x, float y){
		origin.set(x, y, 0);
	}

	public PVector getSize() {
		return size;
	}

	public void setSize(PVector size) {
		this.size = size;
	}
}
