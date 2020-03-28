import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] table;
    private final WeightedQuickUnionUF wq;
    private final int nval;
    private int op;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();  //当n小于等于0时抛出异常
        nval = n;
        wq = new WeightedQuickUnionUF(n * n + 2);      //定义一个加权QuickUnion，在最上层和最下层分别添加一个虚拟节点
        table = new boolean[n][n];
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                table[i][j] = false;                       //初始化nxn的表格
            }
        }
        op = 0;                                            //将状态为open的格子的计数值置零
    }

    private int map2Dto1D(int row, int col) {
        return row * nval + col;                           //将行列坐标展开为一维序列值
    }

    public void open(int row, int col) {
        if (row < 1 || row > nval || col < 1 || col > nval) throw new IllegalArgumentException();
        int trow = row - 1;                                //实际的索引从0开始
        int tcol = col - 1;
        int in = map2Dto1D(trow, tcol);                    //将要open的格子位置先保存下来，之后要多次使用
        if (table[trow][tcol] == false) {
            op++;                                          //状态为open的格子数加一
            table[trow][tcol] = true;                      //true为open状态
            if (row == 1) {
                wq.union(tcol, nval * nval);            //第一行要跟最上面的虚拟节点连接
            }
            if (row == nval) {                              //最后一行要跟最下面的虚拟节点连接
                wq.union(map2Dto1D(trow, tcol), nval * nval + 1);
            }
            if (row > 1 && table[trow - 1][tcol] == true) {  //与周围的节点连接
                wq.union(map2Dto1D(trow - 1, tcol), in);
            }
            if (col > 1 && table[trow][tcol - 1] == true) {
                wq.union(map2Dto1D(trow, tcol - 1), in);
            }
            if (col < nval && table[trow][tcol + 1] == true) {
                wq.union(map2Dto1D(trow, tcol + 1), in);
            }
            if (row < nval && table[trow + 1][tcol] == true) {
                wq.union(map2Dto1D(trow + 1, tcol), in);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > nval || col < 1 || col > nval) throw new IllegalArgumentException();
        return table[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > nval || col < 1 || col > nval) throw new IllegalArgumentException();
        return wq.connected(map2Dto1D(row - 1, col - 1), nval * nval);
    }

    public int numberOfOpenSites() {
        return op;
    }

    public boolean percolates() {                  //判断是否渗透
        return wq.connected(nval * nval, nval * nval + 1);
    }
}
