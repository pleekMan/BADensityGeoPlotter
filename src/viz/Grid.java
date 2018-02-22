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
		
		nodes = new ArrayList<PVector>();
		geoLocations = new ArrayList<Location>();
		screenLocations = new ArrayList<ScreenPosition>();
		
		hRes = vRes = 30;
		origin = new PVector();
		size = new PVector();
	}
	
	public void update(){
		
	}
	public void render(){
		
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
