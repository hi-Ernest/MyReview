package Graph;

/**
 * @author chr
 * @data 2019/5/1 8:39
 * @vision 1.0
 * @Description TODO
 */
public interface Graph<V> {

    int getSize();

    java.util.List<V> getVertices();

    V getVertices(int index);

    int getIndex(V v);

    java.util.List<Integer> getNeighbors(int index);

    int getDegree();

    int[][] getAdjacencyMatrix();

    void printAdjacencyMatrix();

    void printEdges();

    AbstractGraph<V>.Tree dfs(int v);
}
