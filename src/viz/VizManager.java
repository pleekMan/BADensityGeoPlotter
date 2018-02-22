package viz;

import java.util.ArrayList;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import map.MapManager;
import globals.Main;
import globals.ProcessingSingleton;

public class VizManager {

	Main p5;
	MapManager mapManager;

	public MapBounds MAP_BOUNDS;

	Grid grid;

	public VizManager() {
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
		
		mapManager = new MapManager();
		MAP_BOUNDS = new MapBounds();
		grid = new Grid();
		
		//setMapScreenBounds();

	}

	public void update() {
		setGridScreenBounds();
	}

	public void render() {
		drawMapBounds();
	}
	
	public void setGridScreenBounds(){
		
		ScreenPosition topLeft = mapManager.getMap().getScreenPosition(new Location(MAP_BOUNDS.GEO_TOP, MAP_BOUNDS.GEO_LEFT));
		ScreenPosition bottomRight = mapManager.getMap().getScreenPosition(new Location(MAP_BOUNDS.GEO_BOTTOM, MAP_BOUNDS.GEO_RIGHT));
		
		MAP_BOUNDS.SCREEN_TOP = topLeft.y;
		MAP_BOUNDS.SCREEN_LEFT = topLeft.x;
		MAP_BOUNDS.SCREEN_BOTTOM = bottomRight.y;
		MAP_BOUNDS.SCREEN_RIGHT = bottomRight.x;
		
		/*
		p5.pushStyle();
		p5.pushMatrix();
		p5.translate(0,0,10);
		p5.textSize(20);
		p5.fill(0,180,0);
		p5.text("Map Screen Bounds", -100,-80);
		p5.text(MAP_BOUNDS.SCREEN_TOP + " | " +  MAP_BOUNDS.SCREEN_LEFT, -100,-100);
		p5.text(MAP_BOUNDS.SCREEN_BOTTOM + " | " +  MAP_BOUNDS.SCREEN_RIGHT, -100,-120);

		p5.popMatrix();
		p5.popStyle();
		*/
	}
	public void drawMapBounds() {
		//p5.noFill();
		p5.fill(255,127);
		p5.stroke(255,0,0);

		Location corner = new Location(-58.536470f, -34.526591f);
		ScreenPosition topLeft = mapManager.getScreenPositionForGeo(corner);
		
		//p5.ellipse(topLeft.x,topLeft.y, 20,20);
		//p5.rect(MAP_BOUNDS.SCREEN_LEFT,MAP_BOUNDS.SCREEN_TOP, MAP_BOUNDS.SCREEN_RIGHT - MAP_BOUNDS.SCREEN_LEFT, MAP_BOUNDS.SCREEN_TOP - MAP_BOUNDS.SCREEN_BOTTOM);
	}
}
