package Notes.Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class BFS {
    /*
    BFS -> Breadth-First Search
    Concepts :
    1. Locate the root node (where the traversal begins)
    2. Look for adjacent vertices of the node
     */
    private int V; // no. of vertices
    private LinkedList<Integer> adj[]; // adjacency list

    // constructor
    BFS (int v){
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // add an edge into the graph
    void addEdge(int v, int w){
        adj[v].add(w);
    }

    // print BFS traversal from a given source s
    void search (int s){
        boolean visited [] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);
        while (queue.size()!=0){
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<Integer> itr = adj[s].listIterator();
            while (itr.hasNext()){
                int n = itr.next();
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
    // Tester
    public static void main(String[] args) {
        BFS graph = new BFS(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        graph.addEdge(2,3);
        graph.addEdge(3,3);
        graph.search(2);
    }
}
