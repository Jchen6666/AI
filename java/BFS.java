import java.util.HashSet;
import java.util.Stack;
import java.util.LinkedList;
import java.util.ListIterator;

class Graph {
    private int vertices;
    private LinkedList<Integer> adj[] ;

    public Graph(int vertices) {
        this.vertices = vertices; //number of vertices
        this.adj = new LinkedList[vertices];
        for (int i = 0; i<vertices; i++) {
            adj[i] = new LinkedList<Integer>();
            
        }
    }

    void addEdges(int i, int v) {
        adj[i].add(v);
    }

    void BFS(int startFrom) {
        HashSet<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> queue = new LinkedList<>();
        
        visited.add(startFrom);
        queue.add(startFrom);
        
        while(queue.size() != 0) {
            startFrom = queue.poll();
            ListIterator<Integer> i = adj[startFrom].listIterator(); 
            while(i.hasNext()) {
                int n = i.next();
                if(!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                    System.out.println(n);
                }
            }
        }
    }

    void DFS(int startFrom) {
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        
        visited.add(startFrom);
        stack.add(startFrom);
        while(visited.size() != vertices) {
            ListIterator<Integer> i = adj[startFrom].listIterator();
            while(i.hasNext()) {
                int n = i.next();
                if(!visited.contains(n)) {
                    i = adj[n].listIterator();
                    visited.add(n);
                    stack.add(n);
                    System.out.println(n);
                }
            }
           startFrom = stack.pop();
        }
    }

    public static void main(String[] args) {
       Graph g = new Graph(6);

       g.addEdges(0, 1);
       g.addEdges(0, 2);
       g.addEdges(1, 3);
       g.addEdges(2, 5);
       g.addEdges(3, 5);
       g.addEdges(3, 4);

       System.out.println("This is the result of DFS:");
       g.DFS(0);
       System.out.println("This is the result of BFS:");
       g.BFS(0);
    }
}

