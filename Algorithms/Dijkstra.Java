import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
* Below code is implemented here - https://www.codingninjas.com/studio/problems/dijkstra's-shortest-path_985358
*
* '''
* C++ code implemented in CP3.
* vi dist(V, INF); dist[s] = 0; // INF = 1B to avoid overflow 
* priority_queue< ii, vector<ii>, greater<ii> > pq; 
* pq.push(ii(0, s)); 
* while (!pq.empty()) { // main loop
*   ii front = pq.top(); 
*   pq.pop(); // greedy: get shortest unvisited vertex 
*   int d = front.first, u = front.second;
*   if (d > dist[u]) continue; // this is a very important check 
*   for (int j = 0; j < (int)AdjList[u].size(); j++) {
*     ii v = AdjList[u][j]; // all outgoing edges from u 
*     if (dist[u] + v.second < dist[v.first]) {
*       dist[v.first] = dist[u] + v.second; // relax operation
*       pq.push(ii(dist[v.first], v.first));
*     } 
*   }
*  } // this variant can cause duplicate items in the priority queue, to make it faster keep track of visited.
*  '''
*/
public class Solution {

    static class Pair<T, V> {
        T key;
        V value;
        Pair(T key, V value) {
            this.key = key;
            this.value = value;
        }

        T getKey() {
            return this.key;
        }

        V getValue() {
            return this.value;
        }
    }

    static class PairComparator implements Comparator<Pair<Integer, Integer>> {
        public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
            return p2.getKey() - p1.getKey();
        }
    }

    public static List<Integer> dijkstra(int[][] edge,int vertices, int edges,int source){
        // Write your code here.

        PriorityQueue<Pair<Integer, Integer> > pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.getKey())); // or new PairComparator() can also work.
        
        pq.add(new Pair<Integer, Integer>(0, source));

        List<Integer> dist = new ArrayList<>();
        for (int i = 0 ; i < vertices; i++) { 
            dist.add(Integer.MAX_VALUE);
        }

        List<List<Pair<Integer, Integer>> > adj = new ArrayList<List<Pair<Integer, Integer>>>();
        for(int i = 0 ; i< vertices; i++) {
            adj.add(new ArrayList<Pair<Integer, Integer>>());
        }
        for (int i = 0; i < edges; i++) {
            int from, to, cost;
            from = edge[i][0];
            to = edge[i][1];
            cost = edge[i][2];
            adj.get(from).add(new Pair<Integer, Integer>(to, cost));
            adj.get(to).add(new Pair<Integer, Integer>(from, cost));
        }

        dist.set(source, 0);

        while(pq.size() > 0) {
            Pair<Integer, Integer> topp = pq.poll();
            int u = topp.getValue();
            int costu = topp.getKey();
            if (costu > dist.get(u)) continue;
            for (int i = 0; i < adj.get(u).size(); i++) {
                int v = adj.get(u).get(i).getKey();
                int uvcost = adj.get(u).get(i).getValue();
                if (costu + uvcost < dist.get(v)) {
                    dist.set(v, costu + uvcost);
                    pq.add(new Pair<Integer, Integer>(costu + uvcost, v));
                }
            }
        }

        return dist;
    }
}











