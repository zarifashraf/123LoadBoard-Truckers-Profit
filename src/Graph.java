import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Graph {

    private final Map<String, List<Vertex>> vertices;

    public Graph() {
        this.vertices = new HashMap<String, List<Vertex>>();
    }

    public void addVertex(String string, List<Vertex> vertex) {
        this.vertices.put(string, vertex);
    }

    public List<String> getShortestPath(String start, String finish) {
        final Map<String, Double> distances = new HashMap<String, Double>();
        final Map<String, Vertex> previous = new HashMap<String, Vertex>();
        PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();

        for(String vertex : vertices.keySet()) {
            if (vertex.equalsIgnoreCase(start)) {
                distances.put(vertex, 0.0);
                nodes.add(new Vertex(vertex, 0.0));
            } else {
                distances.put(vertex, Double.MAX_VALUE);
                nodes.add(new Vertex(vertex, Double.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            Vertex smallest = nodes.poll();
            if (smallest.getId() == finish) {
                final List<String> path = new ArrayList<String>();
                while (previous.get(smallest.getId()) != null) {
                    path.add(smallest.getId());
                    smallest = previous.get(smallest.getId());
                }
                return path;
            }

            if (distances.get(smallest.getId()) == Double.MAX_VALUE) {
                break;
            }

            for (Vertex neighbor : vertices.get(smallest.getId())) {
                Double alt = distances.get(smallest.getId()) + neighbor.getDistance();
                if (distances.get(neighbor.getId()) != null && alt < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), alt);
                    previous.put(neighbor.getId(), smallest);

                    forloop:
                    for(Vertex n : nodes) {
                        if (n.getId() == neighbor.getId()) {
                            nodes.remove(n);
                            n.setDistance(alt);
                            nodes.add(n);
                            break forloop;
                        }
                    }
                }
            }
        }

        return new ArrayList<String>(distances.keySet());
    }
    
    public void createGraph(LatLong startLocation , String json) {

        JSONArray jsonarray = null;
        try {
            jsonarray = (JSONArray) JSONValue.parse(new FileReader(new File("util").getAbsoluteFile().toString()
                    + json));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Object o : jsonarray) {

            JSONObject load = (JSONObject) o;

            double origin_latitude = (double) load.get("origin_latitude");
            double origin_longitude = (double) load.get("origin_longitude");
            String origin_city = (String) load.get("origin_city");

            double destination_latitude = (double) load.get("destination_latitude");
            double destination_longitude = (double) load.get("destination_longitude");
            String destination_city = (String) load.get("destination_city");

            double amount = (long) load.get("amount");

            // calc starting point to origin distance
            // calc origin to destination distance

            // add vertex from starting point to origin, weight is the -ve of distance
            // add vertex from origin to destination, weight is the (reward - distance)

            double startToOrigin = Utils.geoDist(startLocation.getLatitude(), startLocation.getLongitude(),origin_latitude, origin_longitude);
            double originToDest = Utils.geoDist(origin_latitude, origin_longitude, destination_latitude, destination_longitude);


            Graph g = new Graph();
            g.addVertex("Start", Arrays.asList(new Vertex(origin_city, 0 - startToOrigin)));
            g.addVertex(origin_city, Arrays.asList(new Vertex(destination_city, amount - originToDest)));
            System.out.println(g.getShortestPath("Start", "Muncy"));
        }
    }

}