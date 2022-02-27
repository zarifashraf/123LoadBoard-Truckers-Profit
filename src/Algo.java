import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Algo {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("A", Arrays.asList(new Vertex("B", 7.0), new Vertex("C", 8.0)));
        g.addVertex("B", Arrays.asList(new Vertex("A", 7.0), new Vertex("F", 2.0)));
        g.addVertex("C", Arrays.asList(new Vertex("A", 8.0), new Vertex("F", 6.0), new Vertex("G", 4.0)));
        g.addVertex("D", Arrays.asList(new Vertex("F", 8.0)));
        g.addVertex("E", Arrays.asList(new Vertex("H", 1.0)));
        g.addVertex("F", Arrays.asList(new Vertex("B", 2.0), new Vertex("C", 6.0), new Vertex("D", 8.0), new Vertex("G", 9.0), new Vertex("H", 3.0)));
        g.addVertex("G", Arrays.asList(new Vertex("C", 4.0), new Vertex("F", 9.0)));
        g.addVertex("H", Arrays.asList(new Vertex("E", 1.0), new Vertex("F", 3.0)));
        System.out.println(g.getShortestPath("A", "H"));
        LatLong currentLocation = new LatLong(3.222, -15.788);
        g.createGraph(currentLocation, "/123Loadboard_CodeJam_2022_dataset.json");
    }

}
