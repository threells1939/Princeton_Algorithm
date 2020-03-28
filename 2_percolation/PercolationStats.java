import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double threshold[];
    private final int trials;
    private final double shu = 1.96;
    private double meanVal;
    private double stddevVal;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        threshold = new double[trials];
        for (int i = 1; i <= trials; i++) {          //试验次数循环
            Percolation per = new Percolation(n);
            while (per.percolates() == false) {      //循环直到渗透
                int r = StdRandom.uniform(n) + 1;    //生成随机坐标
                int c = StdRandom.uniform(n) + 1;
                per.open(r, c);                      //open选定的格子
            }
            threshold[i - 1] = (double) per.numberOfOpenSites() / (n * n);    //循环完毕后保存阈值
        }
        meanVal = StdStats.mean(threshold);
        stddevVal = StdStats.stddev(threshold);
    }

    public double mean() {                           //计算各次试验阈值的均值
        return meanVal;
    }

    public double stddev() {                         //计算标准差
        return stddevVal;
    }

    public double confidenceLo() {
        return meanVal - shu * stddevVal / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return meanVal + shu * stddevVal / Math.sqrt(trials);
    }

    public static void main(String[] args) {   //主程序
        StdOut.printf("%-25s\n", "输入行列数n和试验次数t");
        StdOut.print("n=");
        int n = StdIn.readInt();
        StdOut.print("t=");
        int t = StdIn.readInt();

        Stopwatch wt = new Stopwatch();
        PercolationStats ps = new PercolationStats(n, t);
        double elapsed = wt.elapsedTime();  // elapsed CPU time in seconds

        StdOut.printf("%-25s= %f%s\n", "elapsed CPU time", elapsed, "s");   //默认输出7位
        StdOut.printf("%-25s= %.7f\n", "mean", ps.mean());
        StdOut.printf("%-25s= %.17f\n", "stddev", ps.stddev());
        StdOut.printf("%-25s= [%.15f, %.15f]\n", "%95 confidence interval",
                      ps.confidenceLo(), ps.confidenceHi());
    }
}
