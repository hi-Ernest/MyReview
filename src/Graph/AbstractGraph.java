package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chr
 * @data 2019/5/1 10:49
 * @vision 1.0
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices; //存储所有顶点
    protected List<List<Integer>> neighbors; //存储临接list

    protected AbstractGraph(int[][] edges, V[] vertices) {
        this.vertices = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.add(vertices[i]);
        }

        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
        this.vertices = vertices;
        createAdjacencyLists(edges, vertices.size());
    }


    //为每个顶点创建邻接链表
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        //创建一个链表
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        System.out.println(edges.length);

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }


    //为每个顶点创建邻接链表
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        //创建一个链表
        neighbors = new ArrayList<List<Integer>>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (Edge edge : edges) {
            neighbors.get(edge.u).add(edge.v);
        }
    }

    protected static class Edge {
        public int u; //边的起始点
        public int v; //边的结束点

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    //广度优先遍历
    //从顶点v开始遍历
    public Tree bfs(int v) {

        //存储遍历顶点的顺序
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        //初始化
        for(int i=0; i<parent.length; i++) {
            parent[i] = -1;
        }

        java.util.LinkedList<Integer> queue =
                new java.util.LinkedList<>();

        boolean[] isVisited = new boolean[vertices.size()];

        queue.offer(v); //添加新元素进去
        isVisited[v] = true; //设置为已访问

        //该队列不为空则进入循环
        while (!queue.isEmpty()) {
            int u = queue.poll(); //删除队列的元素(即顶点)并返回
            searchOrders.add(u); //将删除的该顶点u添加到遍历顶点的顺序列表中

            //遍历出与顶点u相邻的其他顶点
            for (int w: neighbors.get(u)) {
                if (!isVisited[w]) {
                    queue.offer(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }

        return new Tree(v, parent, searchOrders);
    }



    /**
     * 深度优先遍历(从v顶点开始)
     * @param v
     * @return
     */
    public Tree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        //初始化
        for(int i=0; i<parent.length; i++) {
            parent[i] = -1;
        }

        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrders, isVisited);

        return new Tree(v, parent, searchOrders);
    }

    private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited) {
        //存储访问过的顶点 并标记为已访问
        searchOrders.add(v);
        isVisited[v] = true;

        //访问该顶点的邻接顶点，若没有访问过则加入成为该顶点v的孩子(即顶点v是该顶点i的父亲)
        for (int i: neighbors.get(v)) {
            if(!isVisited[i]) {
                parent[i] = v;
                dfs(i, parent, searchOrders, isVisited);
            }
        }

    }



    public class Tree {
        private int root; //树的根节点
        private int[] parent; //存储每个顶点的父亲
        private List<Integer> searchOrders; //存储搜索的顺序

        public Tree(int root, int[] parent, List<Integer> searchOrders) {
            this.root = root;
            this.parent = parent;
            this.searchOrders = searchOrders;
        }
        public Tree(int root, int[] parent) {
            this.root = root;
            this.parent = parent;
        }

        public int getRoot() {
            return root;
        }
        public int getParent(int v) {
            return parent[v];
        }
        public List<Integer> getSearchOrders() {
            return searchOrders;
        }

        //从指定的顶点下标到root路径
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            }while (index != -1);

            return path;
        }

        public void printTree() {
            System.out.println("Root is: "+ vertices.get(root));
            System.out.println("Edges: ");
            for(int i=0; i<parent.length; i++) {
                if(parent[i] != -1) {
                    //显示该边
                    System.out.print("(" + vertices.get(parent[i])+ ", "+vertices.get(i)+ ") ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return vertices.size();
    }
    public List<V> getVertices() {
        return vertices;
    }
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public V getVertices(int index) {
        return vertices.get(index);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    @Override
    public int getDegree() {
        return 0;
    }

    @Override
    public int[][] getAdjacencyMatrix() {
        return new int[0][];
    }

    @Override
    public void printAdjacencyMatrix() {

    }

    @Override
    public void printEdges() {

    }
}

