/*
This class implements the Union Find algorithm for Quick Find
 */
public class QuickFind implements UnionFind {
    
    private int id[];

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        
        //get the id of p
        int p_id=id[p];
        for(int i=0;i<id.length;i++){
            if (id[i] == p_id){
                id[i]=id[q];//update all ids to that of q 
            }
        }
        
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p]==id[q];
    }

    @Override
    public int find(int p) {
        return id[p];

    }

    /*
    Allows us to see the id array for debugging
     */
    public int[] getId() {
        return id;
    }
}
