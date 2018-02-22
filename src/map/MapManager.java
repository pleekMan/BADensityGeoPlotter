package map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.events.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import globals.Main;
import globals.ProcessingSingleton;

public class MapManager {

	Main p5;

	UnfoldingMap map;
	EventDispatcher eventDispatcher;
	
	public boolean enableMouseInteraction = false;

	Location locationBerlin = new Location(52.5f, 13.4f);
	Location locationLondon = new Location(51.5f, 0f);

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
		
		ScreenPosition posBerlin = map.getScreenPosition(locationBerlin);
		p5.fill(0, 200, 0, 100);
		p5.ellipse(posBerlin.x, posBerlin.y, 20, 20);

		// Zoom dependent marker size
		ScreenPosition posLondon = map.getScreenPosition(locationLondon);
		p5.fill(200, 0, 0, 100);
		float s = map.getZoom();
		p5.ellipse(posLondon.x, posLondon.y, s, s);
		
		/*
		for (int i = 0; i < locations.length; i++) {
			ScreenPosition thisMarker = mapManager.getMap().getScreenPosition(locations[i]);
			fill(0, 0, 255, 127);
			ellipse(thisMarker.x, thisMarker.y, 5, 5);
		}

		ScreenPosition topLeftCorner = mapManager.getMap().getScreenPosition(new Location(topLatitude, leftLongitude));
		ScreenPosition bottomRightCorner = mapManager.getMap().getScreenPosition(new Location(bottomLatitude, rightLongitude));

		//fill(255,0,0);
		noFill();
		stroke(255, 0, 0);
		rect(topLeftCorner.x, topLeftCorner.y, bottomRightCorner.x - topLeftCorner.x, bottomRightCorner.y - topLeftCorner.y);
		popMatrix();
		*/
	}

	public UnfoldingMap getMap() {
		return map;
	}

	public void enableMouseInteraction(boolean state) {
		p5.println("Map Mouse Interaction: " + state);
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
}
