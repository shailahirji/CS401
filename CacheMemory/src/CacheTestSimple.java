import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;


class CacheTestSimple {

    /*
    -This test guarantees that the 'put' method used to add data from source into cache works
    -put method takes in a Linked List as a parameter enabling us to read and add more than 1 page from source into cache
    - put method is genrally called within the get method in the cache. put method shouldnt be accessible to client class, made public for testing purpose .
     */
    @Test
    void put() {

        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);
        LinkedList<Data> input= new LinkedList<>();
        input.add(new Data("F8640D32E533"," Brian Collier"));
        input.add(new Data("7E910C13C8BD"," Hyacinth Sanchez"));
        input.add(new Data("12456F618908"," Fulton Hooper"));
        cm.put(input);
        System.out.println(cm.getCacheMemory());
        System.out.println();
        assertEquals(3,cm.getCacheMemory().size());

    }

    /*
    This test guarantees that our cache structure can succesfully get the data requested for based on the key. 3 cases:
    1. Data doesnt exists in cache and is fetched from the source, and value is returned to client
    2. Data already exists in cache as a result of a previous fetch, value returned to client
    3. Data doesnt exist at all. Not in cache nor in source
    This method calls put method within its implementation
    This test will also show how cache memory will evict an old data cell and replace with newer data
     */
    @Test
    void get() {
        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //gets the correct data that was requested for
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));

        //requested data is stored as the "recently" access data
        assertEquals(0,cm.getLru().indexOf(new Data("F8640D32E533"," Brian Collier")));

        //gets the correct data that was requested
        assertEquals(" Hyacinth Sanchez",cm.get("7E910C13C8BD"));

        //requested data is stored as the "recently" access data at head of Linked List
        assertEquals(0,cm.getLru().indexOf(new Data("7E910C13C8BD"," Hyacinth Sanchez")));

        System.out.println("Inside Cache-->"+cm.getCacheMemory());
        System.out.println("Inside Linked list(LRU)-->"+cm.getLru());
        System.out.println();

        //lets add some more data to see how cache will handle eviction
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");

        //cache is now at full capacity

        assertEquals(5, cm.getCacheMemory().size());

        System.out.println("Inside Cache-->"+cm.getCacheMemory());
        System.out.println("Inside Linked list(LRU)-->"+cm.getLru());
        System.out.println();
        System.out.println("Request for Data already in cache");
        System.out.println();

        // request for data already in cache
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));

        //updates LRU, moves this data to head of list
        System.out.println("Inside Linked list(LRU)-->"+cm.getLru());

        System.out.println();
        System.out.println("Adding to cache when at full capacity");

        //data at LRU tail gets evicted, new data is added at LRU's head
        assertEquals(" Rahim Logan",cm.get("D110C1E64A8B"));

        System.out.println("Updated LRU-->"+cm.getLru());
        System.out.println("Inside cache-->"+cm.getCacheMemory());
        System.out.println();

        //fetching data that doesnt exists
        assertEquals(null,cm.get("JHAD873JD28"));

    }


    //run with get test
    @Test
    void getMissRatio() {

        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to see how cache will handle eviction
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");
        cm.get("JHAD873JD28");//doesn't exists in memory

        //we fetched for total of 7 data look ups , 1 was a hit, 6 were misses (1 of 6 was none existent data)
        assertEquals((double)(6/7),cm.getMissRatio());
    }

    //run with get test
    @Test
    void getHitRatio() {

        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to see how cache will handle eviction
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache, the only hit
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");
        cm.get("JHAD873JD28");//doesn't exists in memory

        assertEquals((1/7),cm.getHitRatio());
    }

    //run with get() test
    @Test
    void contains() {

        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to see how cache will handle eviction
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");
        System.out.println(cm.getCacheMemory());
        assertEquals(true,cm.contains("64E6836B17D9"));
    }

    @Test
    void getRecentlyUsed() {
        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to see how cache will handle eviction
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");
        System.out.println(cm.getLru());

    }

    @Test
    void getCacheMemory() {
        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to cache
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");
        System.out.println(cm.getCacheMemory());
    }

    @Test
    void clear() {
        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);

        //lets add some more data to cache
        cm.get("55AC72FCEF72");
        cm.get("64E6836B17D9");
        cm.get("677165FFA5E5");
        cm.get("F8640D32E533");//from cache
        cm.get("D110C1E64A8B");
        cm.get("D110C1E64A8B");

        cm.clear();
        //after clearing cache
        assertEquals(0,cm.getCacheMemory().size());
        assertEquals(0,cm.getLru().size());

    }

}