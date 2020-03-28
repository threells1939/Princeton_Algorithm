class WeightedQuickUnionUF extends UF {
    private int[] sz;   //（由触点索引的各个根节点所对应的分量的大小

    //构造函数，初始化分量id数组
    WeightedQuickUnionUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) sz[i] = 1;
    }

    int find(int p) {
        //一路循环找到根节点
        while (p != id[p]) p = id[p];
        return p;
    }

    void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[i] = j;
            sz[i] += sz[j];
        }
        count--;
    }

}
