import java.util.*;
// Code works for both undirected and directed graph, there a flag to distinguish.

public class ImplementGenericGraphAdjacencyList<T> {

    /*
     * Generics : to handle data of any type (paramterised T)
     * functionalities we will work on
     * addVertexAndEdge
     * getVertexCount and getEdgeCount
     * also we will have a flag to work both on directed and undirected graph
     */

    // Map to store Vertex -----> List combination  
    private Map <T,List<T>> map = new HashMap <>();

    // idea, get source, destination and a flag for directed/undirected decision
    // and add to Map accordingly. 
    // flag = true = undirected
    public void addVertexAndEdge(T endPointOne, T endPointTwo, boolean flag){

        // check if source and destination are already present in map, if not create a vertex.
        if(!map.keySet().contains(endPointOne)){
            map.put(endPointOne, new LinkedList<>());
        }

        if(!map.keySet().contains(endPointTwo)){
            map.put(endPointTwo, new LinkedList<>());
        }

        if(!map.get(endPointOne).contains(endPointTwo)){
            map.get(endPointOne).add(endPointTwo);
        }
        if(flag){
            if(!map.get(endPointTwo).contains(endPointOne))
                map.get(endPointTwo).add(endPointOne);
        }

    }

    public int getCountOfVertex(){
        return map.keySet().size();
    }

    public int getCountOfEdges(){
        int count = 0;

        for(T vertex : map.keySet()){
            count+=map.get(vertex).size();  // iterate each vertex and get size of linked list associated with that vertex.
        }

        return count;
        


    }

    // hasVertex -- check if the vertex is present in Map
    // hasEdge --   get the Source's Adjacency List -- see if the destination is present. -- will work for both graphs.

    public boolean hasEdge (T source, T destination){
        if(map.get(source).contains(destination)){
            return true;
        }
        return false;
    }


    // Overriding toString for our need.
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for(T vertex : map.keySet()){  // traverse in keySet i.e. all vertex one by one.
            builder.append(vertex.toString() + ": ");

            for(T w : map.get(vertex)){ // traverse in LL associated with that vertex.
                builder.append(w.toString()+" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }



    public static void main(String[] args) {
        ImplementGenericGraphAdjacencyList<Integer> graphObject = new ImplementGenericGraphAdjacencyList<Integer>();

        
        // graphObject.addVertexAndEdge(endPointOne, endPointTwo, flag);
        graphObject.addVertexAndEdge(0,1,false);
        graphObject.addVertexAndEdge(0,2,false);
        graphObject.addVertexAndEdge(1,2,false);
        graphObject.addVertexAndEdge(1,3,false);
        graphObject.addVertexAndEdge(3,2,false);
        System.out.println(graphObject.toString());
    }

}