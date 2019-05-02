package Graph;

import java.util.List;

/**
 * @author chr
 * @data 2019/5/2 14:13
 * @vision 1.0
 */
public class UnweightedGraph<V> extends AbstractGraph<V>{
    public UnweightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
    }

    public UnweightedGraph(List<AbstractGraph.Edge> edges, List<V> vertices) {
        super(edges, vertices);
    }

}
