import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {


    //First few tests below demonstrate how get() and put() method work efficiently

    /*
    This test checks our Cache's simple get method. With the following initial conditions:
    -Cache is empty
    -Fetch first data, grabs desired data plus, 2 other data after it
    -Repeat twice to show how cache looks like via Recently Used Linked List, note the positions of each data that was requested for it before
    the extra grabbed data
     */
    @Test
    void emptyCacheFetch() {

        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        //gets the correct data that was requested for
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));
        //requested data is stored as the "recently" access data
        assertEquals(0,cm.getLru().indexOf(new Data("F8640D32E533"," Brian Collier")));
        System.out.println(cm.getLru());


        //gets the correct data that was requested
        assertEquals(" Hyacinth Sanchez",cm.get("7E910C13C8BD"));
        //requested data is stored as the "recently" access data
        assertEquals(0,cm.getLru().indexOf(new Data("7E910C13C8BD"," Hyacinth Sanchez")));
        //on adding 3 new data, the previous 3 move towards the end of list
        assertEquals(3,cm.getLru().indexOf(new Data("F8640D32E533"," Brian Collier")));

        System.out.println(cm.getLru());


    }

    /*
    This test handles the case of when we get an item that was grabbed in the previous look up, this behaviour of our cache
    allows us to have a high hit rate.
    -Initially empty cache,
    -Get one data, grabs 2 extra..1 miss
    -Get another data, grabs 2 extra.. 1 miss
    -Get third data, data is already in cache, even though it was never asked for, 1 hit
     */
    @Test
    void cachePagesFetched1(){
        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        //we get data related to Brian
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));

        //next, get data related to Hyacinth, thats 2 look ups, 2 misses, 0 hits
        assertEquals(" Hyacinth Sanchez",cm.get("7E910C13C8BD"));

        //checking for item that was part of pages collected previously
        //3 look ups, 1 hits, 2 miss
        assertEquals(" Fulton Hooper",cm.get("12456F618908"));
        System.out.println(cm.getLru());
        assertEquals(2/3,cm.getMissRatio());
        //no cache hits
        assertEquals(1/3,cm.getHitRatio());

    }

    /*
    -This test demonstrates how cache keeps an update of the most recently visited data
    - How this cache implementation is efficient by preventing source reading for every lookup
     */
    @Test
    void cachePagesFetched2(){
        Cache cm= new Cache(13,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        //we get data related to Brian
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));

        //next, get data related to Hyacinth, that's 2 look ups, 2 misses, 0 hits
        assertEquals(" Hyacinth Sanchez",cm.get("7E910C13C8BD"));

        //checking for item that was part of pages collected previously
        //3 look ups, 1 hits, 2 miss
        assertEquals(" Fulton Hooper",cm.get("12456F618908"));
        System.out.println(cm.getLru());
        assertEquals(2/3,cm.getMissRatio());
        //no cache hits
        assertEquals(1/3,cm.getHitRatio());

        //fetch's correct data , miss= 3, hit=1 , lookups=4
        assertEquals(" Aline Howard",cm.get("78BD3AAF893F"));


        //requested for data already in cache, lookups=5, hits=2, miss=3
        assertEquals(7,cm.getLru().indexOf(new Data("F8640D32E533"," Brian Collier")));
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));//revisiting from cache

        assertEquals(3/5,cm.getMissRatio());
        assertEquals(2/5,cm.getHitRatio());

        //Brian's data is recorded as most recently used data even though it existed towards to least recently used before this search
        assertEquals(0,cm.getLru().indexOf(new Data("F8640D32E533"," Brian Collier")));
        System.out.println(cm.getLru());



    }

    @Test
    void cacheSizeMaintenance(){

        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);
        //we get some bunch of data, some new from source, some already existing in cache
        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source, Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        assertEquals(9,cm.getCacheMemory().size()); //size is 9 after 5 look ups
        System.out.println(cm.getLru());

        //space needed for data of this look up
        cm.get("771F424F4718");
        //some old data gets overridden from call above. Brian, Fulton stay although there were obtained in the first most fetch
        assertEquals(true,cm.contains("F8640D32E533"));
        assertEquals(true,cm.contains("12456F618908"));
        assertEquals(true,cm.contains("771F424F4718"));//new available in cache

        System.out.println(cm.getLru());

        //cache at full capacity, therefor some data was overridden
        assertEquals(10,cm.getCacheMemory().size());

    }

    /*
    Case when data doesn't exists
     */

    @Test
    void get_dataDoesntExist(){
        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);
        //provides message iin console
        assertEquals(null,cm.get("123123fdgh546"));
    }


    /*
    -Overall fetching a bunch of data, from source or cache, keeping track of hits and miss
    - Evicting from memory as needed
     */
    @Test
    void get_generalProcess(){

        Cache cm= new Cache(13,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source,Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        cm.get("771F424F4718");

        assertEquals(" Evelyn Poole",cm.get("73A63754B7C7"));
        assertEquals(" Denton Gilliam",cm.get("E6CA67F8F462"));//already in cache
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));
        assertEquals(" Marsden Vazquez",cm.get("FDCD72C7942A"));
        assertEquals(" Shaeleigh Howard",cm.get("A186A73DACD9"));
        assertEquals(" Kieran Walton",cm.get("02FC6201E0D3"));
        assertEquals(" Ross Craig",cm.get("430BEE6D6E7B"));
        assertEquals(" Lareina Craft",cm.get("7C9A38090D92"));



        //we make a total of 13 get calls, 10 new , 3 repeated. Cache memory still at full cap of 10, yet we can get all data above
        assertEquals(10/13,cm.getMissRatio());
        assertEquals(3/13,cm.getHitRatio());
    }

    @Test
    void clear() {
        Cache cm= new Cache(10,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);
        //add some data into an empty cache, then clear cache
        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source,Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        cm.get("771F424F4718");
        cm.clear();
        assertEquals(0,cm.getCacheMemory().size());

    }

    @Test
    void getMissRatio() {
        Cache cm= new Cache(13,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source,Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        cm.get("771F424F4718");

        assertEquals(" Evelyn Poole",cm.get("73A63754B7C7"));
        assertEquals(" Denton Gilliam",cm.get("E6CA67F8F462"));//already in cache
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));
        assertEquals(" Marsden Vazquez",cm.get("FDCD72C7942A"));
        assertEquals(" Shaeleigh Howard",cm.get("A186A73DACD9"));
        assertEquals(" Kieran Walton",cm.get("02FC6201E0D3"));
        assertEquals(" Ross Craig",cm.get("430BEE6D6E7B"));
        assertEquals(" Lareina Craft",cm.get("7C9A38090D92"));

        //we make a total of 13 get calls, 10 new , 3 repeated. Cache memory still at full cap of 10, yet we can get all data above
        assertEquals(10/13,cm.getMissRatio());


    }

    @Test
    void getHitRatio() {

        Cache cm= new Cache(13,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source,Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        cm.get("771F424F4718");

        assertEquals(" Evelyn Poole",cm.get("73A63754B7C7"));
        assertEquals(" Denton Gilliam",cm.get("E6CA67F8F462"));//already in cache
        assertEquals(" Brian Collier",cm.get("F8640D32E533"));
        assertEquals(" Marsden Vazquez",cm.get("FDCD72C7942A"));
        assertEquals(" Shaeleigh Howard",cm.get("A186A73DACD9"));
        assertEquals(" Kieran Walton",cm.get("02FC6201E0D3"));
        assertEquals(" Ross Craig",cm.get("430BEE6D6E7B"));
        assertEquals(" Lareina Craft",cm.get("7C9A38090D92"));

        assertEquals(3/13,cm.getHitRatio());
    }

    @Test
    void contains() {
        Cache cm= new Cache(13,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",2);

        cm.get("F8640D32E533");//from source,Brian
        cm.get("7E910C13C8BD");//from source, Hyacinth
        cm.get("12456F618908");//from cache, cache not yet full, Fulton
        cm.get("78BD3AAF893F");//from source,Aline
        cm.get("F8640D32E533");//from cache,Brian no new space needed
        cm.get("771F424F4718");

        assertEquals(true,cm.contains("F8640D32E533"));
        assertEquals(true,cm.contains("12456F618908"));
        assertEquals(false,cm.contains("247hj8947389"));

    }


}