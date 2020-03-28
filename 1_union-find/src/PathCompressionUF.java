//路径压缩算法
class PathCompressionUF extends WeightedQuickUnionUF {

    PathCompressionUF(int N) {
        super(N);
    }

    int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];//加了这一步
            p = id[p];
        }
        return p;
    }

}
