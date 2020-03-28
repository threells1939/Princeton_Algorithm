class QuickUnionUF extends UF {

    //构造函数，初始化分量id数组
    QuickUnionUF(int N) {
        super(N);
    }

    int find(int p) {
        //一路循环找到根节点
        while (p != id[p]) p = id[p];
        return p;
    }

    void union(int p, int q) {
        //将p和q的根节点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

}
