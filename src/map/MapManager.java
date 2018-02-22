package map;

import java.util.ArrayList;

import viz.VizManager;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.events.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import globals.Main;
import globals.ProcessingSingleton;

public class MapManager {

	Main p5;

	public UnfoldingMap map;
	EventDispatcher eventDispatcher;
	
	public boolean enableMouseInteraction = false;
	
	VizManager vizManager;

//	Location locationBerlin = new Location(52.5f, 13.4f);
//	Location locationLondon = new Location(51.5f, 0f);
	
	Location BairesTopLeft;
	Location BairesBottomRight;
	
	ArrayList<Marker> markers;

	public MapManager() {
		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();
		init();
	}

	private void init() {
		map = new UnfoldingMap(p5, new OpenStreetMap.OpenStreetMapProvider());

		eventDispatcher = new EventDispatcher();
		MouseHandler mouseHandler = new MouseHandler(p5, map);
		eventDispatcher.addBroadcaster(mouseHandler);
		//MapUtils.createDefaultEventDispatcher(p5, map);


		map.setTweening(true);
		map.zoomToLevel(5);
		panAndZoomToBuenosAires();
		//map.setActive(false);

	}

	public void update() {

	}

	public void renderMap() {
		map.draw();

		/// --- TEST BEGIN
		/*
		ScreenPosition posBerlin = map.getScreenPosition(locationBerlin);
		p5.fill(0, 200, 0, 100);
		p5.ellipse(posBerlin.x, posBerlin.y, 20, 20);

		// Zoom dependent marker size
		ScreenPosition posLondon = map.getScreenPosition(locationLondon);
		p5.fill(200, 0, 0, 100);
		float s = map.getZoom();
		p5.ellipse(posLondon.x, posLondon.y, s, s);
		*/
		/// --- TEST END
		
		//---------
		
		drawBuenosAiresScreenBounds();
		
		drawMarkers(vizManager.grid.getGeoLocations());
		

	}

	public UnfoldingMap getMap() {
		return map;
	}
	
	public void drawMarkers(ArrayList<Location> geoLocations){
		
		for (Location location : geoLocations) {
			ScreenPosition newMarker = map.getScreenPosition(location);
			
			p5.noStroke();
			p5.fill(0,200,127);
			
			p5.pushMatrix();
			p5.translate(0, 0, 2);
			p5.rect(newMarker.x - 5, newMarker.y - 5, 10,10);
			p5.popMatrix();
		}
		
	}
	
	public void drawBuenosAiresScreenBounds(){
		ScreenPosition topLeft = map.getScreenPosition(BairesTopLeft);
		ScreenPosition bottomRight = map.getScreenPosition(BairesBottomRight);

		p5.noFill();
		p5.stroke(255,0,0);
		p5.rect(topLeft.x,topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
		p5.ellipse(topLeft.x,topLeft.y, 20,20);
	
	}
	
	public ScreenPosition getScreenPositionForGeo(Location loc){
		// NOT WORKING, SINCE ALL UNFOLDINGmAP RELATED STUFF NEEDS TO BE INSIDE THE SAME FUNCTION WHERE map.draw() is.
		// THIS SUCKS... GOTTA FIX IT SOMEHOW
		return map.getScreenPosition(loc);
	}

	public void enableMouseInteraction(boolean state) {
		p5.println("Map: Mouse Interaction: " + state);
		enableMouseInteraction = state;
		if (enableMouseInteraction) {
			eventDispatcher.register(map, PanMapEvent.TYPE_PAN, map.getId());
			eventDispatcher.register(map, ZoomMapEvent.TYPE_ZOOM, map.getId());
		} else {
			eventDispatcher.unregister(map, PanMapEvent.TYPE_PAN, map.getId());
			eventDispatcher.unregister(map, ZoomMapEvent.TYPE_ZOOM, map.getId());
		}
	}
	
	public void panAndZoomToBuenosAires(){
		map.panTo(new Location(-34.6116725,-58.4435955));
		map.zoomToLevel(5);
	}
	
	public void printCurrentCoordinates(){
		p5.println("Map Center at: LAT " + map.getCenter().getLat() + " | LON " + map.getCenter().getLon());
	}
	
	public void setVizManager(VizManager vizM){
		vizManager = vizM;
		
		BairesTopLeft = new Location(vizManager.MAP_BOUNDS.GEO_TOP, vizManager.MAP_BOUNDS.GEO_LEFT);
		BairesBottomRight = new Location(vizManager.MAP_BOUNDS.GEO_BOTTOM, vizManager.MAP_BOUNDS.GEO_RIGHT);
	}
}
