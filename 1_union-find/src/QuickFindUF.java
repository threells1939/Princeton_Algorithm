class QuickFindUF extends UF {

    //构造函数，初始化分量id数组
    QuickFindUF(int N) {
        super(N);
    }

    int find(int p) {
        return id[p];
    }

    //将p和q归并到相同的分量中
    void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        //如果p和q已经在相同的分量之中则不需要采取任何行动
        if (pID == qID) return;
        //将id为p的分量全部改id为q
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }
}
