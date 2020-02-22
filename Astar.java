import java.util.HashMap;
import java.util.HashSet;

class Node {
    public String name;
    public int heurisitic ;
    public Edge[] adjacentNodes;
    
    public Node(String name, int heurisitic) {
        this.name = name;
        this.heurisitic = heurisitic;
    }
}

class Edge {
    public int cost;
    public Node target;

    public Edge(int cost, Node target) {
        this.cost = cost;
        this.target = target;
    }
}

class aStarGraph {
    public Node[] graphNodes;
    public Node startNode;
    public Node destination;
    
    public aStarGraph() {
        setGraph();
    }
    private void setGraph() {
        Node a = new Node("a", 21);
        Node b = new Node("b", 14);
        Node c = new Node("c", 18);
        Node d = new Node("d", 18);
        Node e = new Node("e", 5);
        Node f = new Node("f", 8);
        Node z = new Node("z", 0); // node z is the destination

        Edge ab = new Edge(9, b);
        Edge ac = new Edge(4, c);
        Edge ad = new Edge(7, d);
        Edge[] aEdges = {ab, ac, ad};
        a.adjacentNodes = aEdges;

        Edge be = new Edge(11, e);
        Edge[] bEdges = {be};
        b.adjacentNodes = bEdges;

        Edge ce = new Edge(17, e);
        Edge cf = new Edge(12, f);
        Edge[] cEdges = {ce, cf};
        c.adjacentNodes = cEdges;

        Edge df = new Edge(18, f);
        Edge[] dEdges = {df}; 
        d.adjacentNodes = dEdges;

        Edge ez = new Edge(5, z);
        Edge[] edges = {ez};
        e.adjacentNodes = edges;

        
        Edge fz = new Edge(9, z);
        Edge[] fdges = {fz};
        f.adjacentNodes = fdges;
        
        Node[] nodes = {a, b, c, d, e, f, z};
        this.startNode = a;
        this.destination = z;
        this.graphNodes = nodes;
    }

    public int aStar() {
        HashSet<Node> visitNodes = new HashSet<>();
        HashSet<Node> queue = new HashSet<>();
        HashMap<Node, Integer> shortest = new HashMap<>();
        HashMap<Node, Integer> shortestTotal = new HashMap<>();
        boolean finished = false;
        queue.add(this.startNode);
        Node currentNode = this.startNode;

        shortest.put(this.startNode,0);
        shortestTotal.put(this.startNode, this.startNode.heurisitic);
        while(!finished && !queue.isEmpty()) {
            for (Edge edge : currentNode.adjacentNodes) {
                int currentCost = edge.cost + shortest.get(currentNode);
                if(!shortest.containsKey(edge.target)) {
                    shortest.put(edge.target, currentCost);
                    shortestTotal.put(edge.target, currentCost + edge.target.heurisitic);
                    queue.add(edge.target);
                } else if(currentCost<shortest.get(edge.target) && !visitNodes.contains(edge.target)) {
                    shortest.replace(edge.target, currentCost);
                    shortestTotal.replace(edge.target, currentCost + edge.target.heurisitic);
                } else {
                    continue;
                }
            }
            visitNodes.add(currentNode);
            queue.remove(currentNode);

            if(shortestTotal.containsKey(this.destination)) {
                int currentMinimum = shortestTotal.get(currentNode);
                boolean shouldContinue = false;
                for(Node node : queue) {
                    if(shortestTotal.get(node)< currentMinimum) {
                        currentMinimum = shortestTotal.get(node);
                        currentNode = node;
                        shouldContinue = true;
                    } 
                }
                if(!shouldContinue) {
                    finished = true;
                }
            } else {
                boolean start = true; 
                int minimum = 0;
                for(Node node: queue) {
                    if (start) {
                        currentNode = node;
                        minimum = shortestTotal.get(node);
                        start = false;
                    } else if(shortestTotal.get(node)<minimum) {
                        currentNode = node;
                        minimum = shortestTotal.get(node);
                    } else {
                        continue;
                    }
                }
            }
        }
        return shortest.get(this.destination);
    }
}

class Main {
    public static void main(String[] args) {
        aStarGraph g = new aStarGraph();
        System.out.println("a star search result from Node " + g.startNode.name + " to Node " + g.destination.name + ": " + g.aStar());

    }
}