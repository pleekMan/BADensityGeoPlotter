package map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.events.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
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
}
