public class Client {

    public static void main(String [] args){

        Cache cm= new Cache(5,"/Users/shaila/Desktop/Algo_workspace/CacheMemory/src/data.txt",0);
        System.out.println(cm.get("F8640D32E533"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("7E910C13C8BD"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        //checking for item that was part of pages collected previously
        System.out.println(cm.get("12456F618908"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();


        System.out.println(cm.get("78BD3AAF893F"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("F8640D32E533"));//revisiting from cache
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("771F424F4718"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("73A63754B7C7"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("E6CA67F8F462"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("A186A73DACD9"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("02FC6201E0D3"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("430BEE6D6E7B"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());
        System.out.println();

        System.out.println(cm.get("7C9A38090D92"));
        System.out.println(cm.getLru());
        System.out.println("Cache miss:"+cm.getMissRatio());
        System.out.println("Cache hit:"+cm.getHitRatio());




    }
}
