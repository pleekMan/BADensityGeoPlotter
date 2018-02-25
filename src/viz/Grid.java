package viz;

import java.util.ArrayList;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PVector;
import globals.Main;
import globals.ProcessingSingleton;

public class Grid {
	Main p5;
	ArrayList<PVector> gridNodes;
	ArrayList<Location> markerGeoLocations;
	ArrayList<ScreenPosition> screenLocations;

	int hRes, vRes;
	PVector origin;
	PVector size;
	float distanceThreshold;

	public Grid() {

		p5 = ProcessingSingleton.getInstance().getProcessingSingleton();

		// THE nodes PVector could by arranged this way:
		// x: normX, y: normY, z:value after density calculation

		gridNodes = new ArrayList<PVector>();
		markerGeoLocations = new ArrayList<Location>();
		screenLocations = new ArrayList<ScreenPosition>();

		hRes = vRes = 50;
		origin = new PVector();
		size = new PVector();
		distanceThreshold = 0.01f;

		createGrid();
	}

	@SuppressWarnings("static-access")
	public void update() {
		/*
		for (int i = 0; i < gridNodes.size(); i++) {

			for (int j = 0; j < markerGeoLocations.size(); j++) {
				
				float nodeX = gridNodes.get(i).x;
				float nodeY = gridNodes.get(i).y;
				float markerX = markerGeoLocations.get(j).getLon();
				float markerY = markerGeoLocations.get(j).getLat();
				
				float gridNodeToGeoDistance = p5.dist(nodeX, nodeY, markerX, markerY);
				if (gridNodeToGeoDistance < distanceThreshold) {
					gridNodes.get(i).z += 1;
				}
			}

			//p5.println("GridNode " + i + " Z:" + gridNodes.get(i).z);

			//ScreenPosition newPos = map.getScreenPosition(new Location(geoY,geoX));

		}
		*/

	}

	public void render() {

	}

	public void createGrid() {

		float resUnit = 1.0f / hRes;
		float x = 0;
		float y = 0;
		for (int i = 0; i < hRes * vRes; i++) {

			PVector newNode = new PVector(x, y, 0);
			gridNodes.add(newNode);

			if ((i % hRes) >= hRes - 1) {
				x = 0;
				y += resUnit;
			} else {
				x += resUnit;
			}
		}
	}

	public ArrayList<PVector> getGridNodes() {
		return gridNodes;
	}

	public ArrayList<ScreenPosition> getScreenLocations() {
		return screenLocations;
	}

	public void setScreenLocation(int i, ScreenPosition sp) {
		screenLocations.get(i).set(sp);
	}

	public void populateGeoLocations(ArrayList<Location> locs) {
		markerGeoLocations = locs;
		//initScreenLocations(markerGeoLocations.size());

		PVector geoOrigin = new PVector(VizManager.MAP_BOUNDS.GEO_LEFT, VizManager.MAP_BOUNDS.GEO_TOP);
		PVector geoEnd = new PVector(VizManager.MAP_BOUNDS.GEO_RIGHT, VizManager.MAP_BOUNDS.GEO_BOTTOM);

		for (int i = 0; i < gridNodes.size(); i++) {

			gridNodes.get(i).x = p5.map(gridNodes.get(i).x, 0, 1, geoOrigin.x, geoEnd.x);
			gridNodes.get(i).y = p5.map(gridNodes.get(i).y, 0, 1, geoOrigin.y, geoEnd.y);

			for (int j = 0; j < markerGeoLocations.size(); j++) {

				float markerX = markerGeoLocations.get(j).getLon();
				float markerY = markerGeoLocations.get(j).getLat();

				float gridNodeToGeoDistance = p5.dist(gridNodes.get(i).x, gridNodes.get(i).y, markerX, markerY);
				if (gridNodeToGeoDistance < distanceThreshold) {
					gridNodes.get(i).z += 1;
				}
			}

		}
		p5.println("-|| GeoLocations.size() in Grid: " + markerGeoLocations.size());
	}

	public void initScreenLocations(int count) {
		for (int i = 0; i < count; i++) {
			screenLocations.add(new ScreenPosition(0, 0));
		}
	}

	public ArrayList<Location> getGeoLocations() {
		return markerGeoLocations;
	}

	public void setOrigin(float x, float y) {
		origin.set(x, y, 0);
	}

	public PVector getSize() {
		return size;
	}

	public void setSize(PVector size) {
		this.size = size;
	}
}
