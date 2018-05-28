import java.util.HashMap;
import java.util.LinkedList;

public class Cache {

    private LinkedList<Data> lru;
    private HashMap<Object,Object> cacheMemory;
    private MemoryManagementUnit mmu;
    private int additionalPages;
    private int cacheSize;
    private static int hit;
    private static int miss;
    private static int lookUp;

    public Cache(int size,String source,int additionalPages){
        mmu= new MemoryManagementUnit(source,additionalPages);
        lru = new LinkedList<>();
        cacheMemory= new HashMap<>();
        this.additionalPages=additionalPages;
        cacheSize=size;
        }

    //user enters key, we give them value

    public Object get(Object key){
        lookUp++;//increment miss count
        Data newPage;
       //check cache for the value
       if(cacheMemory.get(key)==null){
           miss++;
           //go to source, mmu returns the search pages and the following 2 pages after the one of interest
           LinkedList<Data> search = mmu.readSource(key);
           if(search==null){//if no search results found, print statement
               System.out.println("Page doesn't exist in Memory");
               return null;
           }else{
               //if Page present in source, get it and add Page into cache
               put(search);
               return cacheMemory.get(key);//return value of search key to user
           }
       }else{
           //if present in cache memory
           hit++;//increment hit count
           newPage=new Data(key,cacheMemory.get(key));
           //update lru by moving page to the front of linked list
           lru.remove(newPage);
           lru.addFirst(newPage);
       }
        return newPage.getValue();

    }

    /*
    This method adds new page(s) to the cache memory, should actually be a private method.Made public to enable testing
     */
    public void put(LinkedList<Data> newRead){

        while(newRead.size()!=0){

            Data newPage= newRead.getLast();//get element
            newRead.removeLast();//remove it from newRead LL

        if(cacheMemory.size()!=cacheSize){
            //add to cache memory hashmap
            cacheMemory.put(newPage.getKey(),newPage.getValue());
            //add to linked list at the top 
            lru.addFirst(newPage);
        }else {
            //get the last element in linked list, and remove it from cache
            cacheMemory.remove(lru.getLast().getKey());
            //add new element to cache
            cacheMemory.put(newPage.getKey(), newPage.getValue());

            //if cache memory at full cap,delete last element and add new element in the LinkedList at the head 
            lru.removeLast();
            lru.addFirst(newPage);
        }
        }
    }

    public void clear(){
        cacheMemory.clear();
        lru.clear();
    }

    public double getMissRatio(){
        return (double)(miss/(lookUp));
    }

    public double getHitRatio(){
        return (double)(hit/(lookUp));
    }

    public boolean contains(Object key){
        return cacheMemory.containsKey(key);

    }

    public LinkedList<Data> getLru() {
        return lru;
    }

    public HashMap<Object, Object> getCacheMemory() {
        return cacheMemory;
    }


}
