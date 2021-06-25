package Notes.Graph;

import DS.LinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

class Vertex <T extends Comparable<T>, N extends Comparable<N>> {
    T vertexInfo;
    int indeg;
    int outdeg;
    Vertex <T,N> nextVertex;
    Edge <T,N> firstEdge;

    public Vertex(){
        vertexInfo = null;
        indeg = 0;
        outdeg = 0;
        nextVertex = null;
        firstEdge = null;
    }

    public Vertex (T vInfo, Vertex <T,N> next){
        vertexInfo = vInfo;
        indeg = 0;
        outdeg = 0;
        nextVertex = next;
        firstEdge = null;
    }
}

class Edge <T extends Comparable<T>, N extends  Comparable <N>>{
    Vertex <T,N> toVertex; // reference to adjacent vertex
    N weight; // weight of edge
    Edge <T,N> nextEdge; // reference to next edge node

    public Edge(){
        toVertex = null;
        weight = null;
        nextEdge = null;
    }

    public Edge (Vertex<T,N> destination, N w, Edge<T,N> a){
        toVertex = destination;
        weight = w;
        nextEdge = a;
    }
}

public class Graph<T extends Comparable<T>, N extends Comparable<N>>{
    Vertex <T,N> head;
    int size;

    // Constructor
    public Graph(){
        head = null;
        size = 0;
    }

    // get the number of vertices
    public int getSize(){
        return this.size;
    }

    // is this vertex in graph
    public boolean hasVertex(T v){
        if(head==null) return false;
        Vertex<T,N> temp = head;
        while (temp!=null){
            if(temp.vertexInfo.compareTo(v)==0)
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }

    // get inDeg of a vertex
    public int getIndeg(T v){
        if(hasVertex(v)==true){
            Vertex<T,N> temp = head;
            while (temp!=null){
                if(temp.vertexInfo.compareTo(v)==0)
                    return temp.indeg;
                temp = temp.nextVertex;
            }
        }
        return -1; // vertex not found
    }

    // get outDeg of a vertex
    public int getOutdeg(T v){
        if(hasVertex(v)==true){
            Vertex<T,N> temp = head;
            while (temp!=null){
                if(temp.vertexInfo.compareTo(v)==0)
                    return temp.outdeg;
                temp = temp.nextVertex;
            }
        }
        return -1; // vertex not found
    }

    // add a vertex
    public boolean addVertex(T v){
        if(hasVertex(v)==false){
            Vertex<T,N> temp = head;
            Vertex<T,N> newVertex = new Vertex<>(v, null);
            if(head==null) // graph empty, point head to this vertex
                head = newVertex;
            else {
                Vertex<T,N> previous = head;
                while(temp!= null){ // use previous to move to the last index
                    previous = temp;
                    temp = temp.nextVertex;
                }
                previous.nextVertex = newVertex; // add new vertex as last in the list
            }
            size++;
            return true;
        }
        else
            return false; // vertex already in graph
    }

    // get index of vertex
    public int getIndex (T v){
        Vertex<T,N> temp = head;
        int pos = 0;
        while (temp!=null){
            if(temp.vertexInfo.compareTo(v)==0)
                return pos;
            temp = temp.nextVertex; // move temp to next vertex
            pos+=1;
        }
        return -1; // not found
    }

    // return all vertices info to an ArrayList
    public ArrayList<T> getAllVertexObjects(){
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while (temp!=null){
            list.add(temp.vertexInfo);
            temp = temp.nextVertex;
        }
        return list;
    }

    // get vertex info at a specified index
    public T getVertex (int pos){
        if(pos>size-1 || pos<0) // position out of range
            return null;
        Vertex<T,N> temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.nextVertex;
        }
        return temp.vertexInfo;
    }

    // check whether there is an edge between 2 vertices
    public boolean hasEdge (T source, T destination){
        if(head==null) return false; // empty graph
        if(!hasVertex(source) || !hasVertex(destination)) return false; // no such vertices
        Vertex<T,N> sourceVertex = head;
        while (sourceVertex!=null){ // look for source vertex
            if (sourceVertex.vertexInfo.compareTo(source)==0){
                // reached source vertex, look for destination
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null){ // look for destination vertex
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        // destination found
                        return true;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    // add a new edge from source to destination with a weight
    public boolean addEdge(T source, T destination, N w){
        if(head==null) return false;
        if(!hasVertex(source)||!hasVertex(destination)) return false;
        Vertex<T,N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0) {
                Vertex<T,N> destinationVertex = head;
                while(destinationVertex!=null){
                    if(destinationVertex.vertexInfo.compareTo(destination)==0){
                        Edge<T,N> currentEdge = sourceVertex.firstEdge;
                        Edge<T,N> newEdge = new Edge <>(destinationVertex, w, currentEdge);
                        sourceVertex.firstEdge = newEdge; // don't know what this line means
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    // retrieve the weight of an Edge
    public N getEdgeWeight (T source, T destination){
        if(head==null) return null;
        if(!hasVertex(source)||!hasVertex(destination)) return null;
        Vertex<T,N> sourceVertex = head;
        while(sourceVertex!=null){
            if(sourceVertex.vertexInfo.compareTo(source)==0){
                Edge<T,N> currentEdge = sourceVertex.firstEdge;
                while(currentEdge!=null){
                    if(currentEdge.toVertex.vertexInfo.compareTo(destination)==0)
                        return currentEdge.weight;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return null;
    }

    // return all neighbours of a vertex to an ArrayList
    public ArrayList<T> getNeighbours (T v){
        if(!hasVertex(v)) return null; // not found
        ArrayList<T> list = new ArrayList<>();
        Vertex<T,N> temp = head;
        while(temp!=null){
            if(temp.vertexInfo.compareTo(v)==0){
                Edge <T,N> currentEdge = temp.firstEdge;
                while(currentEdge!=null){
                     list.add(currentEdge.toVertex.vertexInfo);
                     currentEdge = currentEdge.nextEdge;
                }
            }
            temp = temp.nextVertex;
        }
        return list;
    }

    // print graph
    public void printEdges(){
        Vertex<T,N> temp = head;
        while(temp!=null){
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T,N> currentEdge = temp.firstEdge;
            while (currentEdge!=null){
                System.out.print("["+temp.vertexInfo+","+currentEdge.toVertex.vertexInfo+"] ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    /*

     */

    private LinkedList<T> [] adj;

    /** Default constructor with number of vertices */
    public Graph (int v){
        size = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /** Add adjacency nodes when there is an edge between 2 nodes */
    public void addEdge (T v, T w){
        adj[getIndex(v)].add(w);      // Add w to v's list
    }

    public void dfsFunc (T v, boolean visited []){
        visited [getIndex(v)] = true;     // mark current node as visited node
        System.out.print(v+" ");
        Iterator<T> i = adj[getIndex(v)].iterator();
        while(i.hasNext()){
            T n = i.next();
            if(!visited[getIndex(n)]) dfsFunc(n, visited);
        }
    }

    // Depth First Search using Stack
    public void DFS (T v){
        boolean visited [] = new boolean [size];
        dfsFunc(v,visited);
    }

    // Breadth First Search using Queue
    public void BFS (T s){
        boolean visited [] = new boolean [size];
        java.util.LinkedList<T> queue = new java.util.LinkedList<>();

        visited[getIndex(s)] = true;
        queue.add(s);
        while (queue.size()!=0){
            s = queue.poll();
            System.out.print(s+" ");
            Iterator<T> itr = adj[getIndex(s)].iterator();
            while (itr.hasNext()){
                T n = itr.next();
                if(!visited[getIndex(n)]){
                    visited[getIndex(n)] = true;
                    queue.add(n);
                }
            }
        }
    }

    private LinkedList <Double> neighboursCost [];


    public static void main(String[] args) {
        Graph<String,Integer> graph1 = new Graph<>(8);
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.addVertex("D");
        graph1.addVertex("E");
        graph1.addVertex("F");
        graph1.addVertex("G");
        graph1.addVertex("H");
        graph1.addEdge("A","B");
        graph1.addEdge("B","D");
        graph1.addEdge("B","E");
        graph1.addEdge("B","C");
        graph1.addEdge("A","C");
        graph1.addEdge("C","F");
        graph1.addEdge("F","H");
        graph1.addEdge("G","H");
        graph1.addEdge("A","G");
        graph1.BFS("A");
        System.out.println();
        graph1.DFS("A");
    }
}
