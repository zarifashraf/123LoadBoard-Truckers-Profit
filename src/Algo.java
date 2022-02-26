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

class Vertex implements Comparable<Vertex> {

    private String id;
    private Double distance;

    public Vertex(String id, Double distance) {
        super();
        this.id = id;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (distance == null) {
            if (other.distance != null)
                return false;
        } else if (!distance.equals(other.distance))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vertex [id=" + id + ", distance=" + distance + "]";
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.distance < o.distance)
            return -1;
        else if (this.distance > o.distance)
            return 1;
        else
            return this.getId().compareTo(o.getId());
    }

}

class Graph {

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
                if (alt < distances.get(neighbor.getId())) {
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
            LatLong originLatLong = new LatLong(origin_latitude, origin_longitude);
            String origin_city = (String) load.get("origin_city");

            double destination_latitude = (double) load.get("destination_latitude");
            double destination_longitude = (double) load.get("destination_longitude");
            LatLong destLatLong = new LatLong(destination_latitude, destination_longitude);
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
        }
    }

}
