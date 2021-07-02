import java.util.LinkedList;
import java.util.Scanner;

public class Flow {
    private static int maxN = 1000;
    private static int[][] graph = new int[maxN][maxN];
    private static int[] parent = new int[maxN];
    private static int[][] ans = new int[maxN][maxN];
    private static boolean bfs(int s, int t) {
        boolean[] mark = new boolean[maxN];
        for (int i = 0; i < maxN; ++i)
            mark[i] = false;

        LinkedList<Integer> queue = new LinkedList<>();
        mark[s] = true;
        queue.add(s);
        parent[s] = -1;

        while (queue.size() != 0) {
            int now = queue.poll();
            for (int v = 0; v < maxN; v++) {
                if (!mark[v] && graph[now][v] > 0) {
                    if (v == t) {
                        parent[v] = now;
                        return true;
                    }
                    mark[v] = true;
                    queue.add(v);
                    parent[v] = now;
                }
            }
        }

        return false;
    }

    private static boolean hasTwoDisjointPath(int s, int t) {
        int max_flow = 0;

        while (bfs(s, t)) {
            int path_flow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int now = parent[v];
                ans[now][v] += 1;
                path_flow = Math.min(path_flow, graph[now][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int now = parent[v];
                graph[now][v] -= path_flow;
                graph[v][now] += path_flow;
            }

            max_flow += path_flow;
            if (max_flow == 2)
                return true;

        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter N:");
        int n= sc.nextInt();
        System.out.println("Enter M:");
        int m= sc.nextInt();
        System.out.println("Enter edges:");
        for (int i = 0; i < m; i++){
            int fi= sc.nextInt();
            int se= sc.nextInt();
            graph[fi][se] = 1;
        }
        System.out.println("Enter S:");
        int s= sc.nextInt();
        System.out.println("Enter T:");
        int t= sc.nextInt();
        if(hasTwoDisjointPath(s, t)){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++) {
                    if (ans[i][j] == 1 && ans[j][i] == 1){
                        ans[i][j] = 0;
                        ans[j][i] = 0;
                    }
                }
            }
            System.out.println("The edges of two paths are:");
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++) {
                    System.out.print(ans[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        else
            System.out.println("There is no two disjoint paths between s and t.");
    }
}
