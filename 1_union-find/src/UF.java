class UF {
    int[] id;   //分量id（以触点作为索引)
    int count;  //分量数量

    //构造函数，初始化分量id数组
    UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    //获取连通分量的数量
    int count() {
        return count;
    }

    //判断两者是否相连
    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //查找p所在分量的标识符
    int find(int p) {
        return 0;
    }

    //在p和q之间添加一条连接
    void union(int p, int q) {
    }

}
