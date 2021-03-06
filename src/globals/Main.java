package globals;

import map.MapManager;
import processing.core.*;
//import processing.opengl.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;



public class Main extends PApplet {

	//MapManager mapManager;
	AppManager appManager;
	
	//PeasyCam cam;

	//Location locationBerlin = new Location(52.5f, 13.4f);
	//Location locationLondon = new Location(51.5f, 0f);

	//Location[] locations;

	float leftLongitude = 999;
	float rightLongitude = -999;
	float topLatitude = -999;
	float bottomLatitude = 999;

	// ONLY ON PROCESSING 3
	/*public void settings() {
		//size(1000,1000, "processing.opengl.PGraphics3D");
		//size(1000,1000, P3D);
	}*/

	public void setup() {
		size(800, 800, P3D);
		frameRate(30);
		setProcessingSingleton();
		
		appManager = new AppManager();
		
		
		//MAP MANAGER
		//mapManager = new MapManager();

		// 3D CAMERA
		/*
		cam = new PeasyCam(this, width);
		cam.setMinimumDistance(20);
		cam.setMaximumDistance(3000);
		*/
		
		// DATA MANAGEMENT
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

		//map = new UnfoldingMap(this, new Google.GoogleMapProvider());
		 * 
		 */

	}

	public void draw() {
		background(0);
		noStroke();
		
		appManager.update();
		appManager.render();

		//mapManager.renderMap();

		// Draws locations on screen positions according to their geo-locations.

		// Fixed-size marker
		/*
		ScreenPosition posBerlin = mapManager.getMap().getScreenPosition(locationBerlin);
		fill(0, 200, 0, 100);
		ellipse(posBerlin.x, posBerlin.y, 20, 20);

		// Zoom dependent marker size
		ScreenPosition posLondon = mapManager.getMap().getScreenPosition(locationLondon);
		fill(200, 0, 0, 100);
		float s = mapManager.getMap().getZoom();
		ellipse(posLondon.x, posLondon.y, s, s);
		*/
		//------------------


	}

	public void keyPressed() {
		appManager.keyPressed(key);

	}

	public void mousePressed() {
	}

	public void mouseReleased() {
	}

	public void mouseClicked() {
	}

	public void mouseDragged() {
	}

	public void mouseMoved() {
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { Main.class.getName() });
		// PApplet.main(new String[] { "--present", Main.class.getName() }); //
		// PRESENT MODE
	}

	private void setProcessingSingleton() {
		ProcessingSingleton.getInstance().setProcessingSingleton(this);
	}
}