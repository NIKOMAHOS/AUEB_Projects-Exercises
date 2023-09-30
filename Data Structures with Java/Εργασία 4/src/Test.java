public class Test {
public static void main(String[] args) {
    CacheImpl<String, String> cache = new CacheImpl<String, String>(5);
    cache.store("5", "g");
    cache.store("6", "h");
    cache.store("7", "i");
    //cache.lookUp("5"); // duplicate key
    cache.store("8", "j");
    cache.store("9", "k");
    cache.store("10", "k");
    cache.lookUp("5"); // -> 5 has been removed from the cache
    cache.lookUp("6"); // -> A Hit ! and 6 is now the most recent entry
    cache.store("11", "l"); // -> 7 was removed from the cache
    cache.lookUp("8"); // -> A Hit ! and 8 is now the most recent entry
    System.out.println(cache.getHits()); // prints 1 => Correct !
    }
}