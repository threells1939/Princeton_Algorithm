import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class Test {
    private void testOne(String typeName, String fileName, String fileSize) {
        // 读取文件
        File ufFile = new File(fileName);
        int[] ufContent = new In(ufFile).readAllInts();
        int N = ufContent[0]; //触点数量N
        UF uf = null;
        //动态创建子类
        switch (typeName) {
            case "QuickFindUF":
                uf = new QuickFindUF(N);
                break;
            case "QuickUnionUF":
                uf = new QuickUnionUF(N);
                break;
            case "WeightedQuickUnionUF":
                uf = new WeightedQuickUnionUF(N);
                break;
            case "PathCompressionUF":
                uf = new PathCompressionUF(N);
        }
        //放在要检测的代码段前，毫秒计算
        long start = System.currentTimeMillis();
        //Stopwatch timer = new Stopwatch();// algs4的方法，按秒计算，太小了
        //进行连通
        for (int i = 1; i < ufContent.length; i += 2) {
            int p = ufContent[i];
            int q = ufContent[i + 1];
            if (uf.connected(p, q)) continue;//如果已经连通则忽略
            uf.union(p, q);
            StdOut.print(p + " " + q + "; ");//打印连接，不打印的话程序运行太快了
            //StdArrayIO.print(uf.id);//打印每次连接id[]数组的变化
        }
        //StdOut.println(uf.count() + "components");//打印分量数
        //放在要检测的代码段后
        long end = System.currentTimeMillis();
        long time = end - start;
        StdOut.println("\n" + typeName + " " + fileSize + " " + time + " ms");
        /*double time = timer.elapsedTime();// elapsed()方法能够返回自它创建以来所经过的时间
        StdOut.println(typeName + " " + fileSize + " " + time + " second");*/
    }

    public static void main(String[] args) {
        Test test = new Test();
        String tinyFile = "F:/Princeton_Algorithm/1_FirstWeek/union-find/src/tinyUF.txt";
        String mediumFile = "F:/Princeton_Algorithm/1_FirstWeek/union-find/src/mediumUF.txt";

        test.testOne("QuickFindUF", tinyFile, "tinyFile");
        test.testOne("QuickFindUF", mediumFile, "mediumFile");
        test.testOne("QuickUnionUF", tinyFile, "tinyFile");
        test.testOne("QuickUnionUF", mediumFile, "mediumFile");
        test.testOne("WeightedQuickUnionUF", tinyFile, "tinyFile");
        test.testOne("WeightedQuickUnionUF", mediumFile, "mediumFile");
        test.testOne("PathCompressionUF", tinyFile, "tinyFile");
        test.testOne("PathCompressionUF", mediumFile, "mediumFile");

    }

}
