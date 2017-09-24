package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * NonBlockCache.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 23.09.2017
 */
public class NonBlockCache {
    /**
     * Map for Cache.
     */
    private ConcurrentHashMap<Model, Integer> map = new ConcurrentHashMap<>();

    /**
     * Atomically adding model to map.
     * @param model for adding.
     */
    public void add(Model model) {
        map.computeIfAbsent(model, model1 -> model.getVersion());
    }

    /**
     * Remove model from cache.
     * @param model for removing.
     */
    public void delete(Model model) {
        map.remove(model);
    }

    /**
     * Atomically update model in cache.
     * @param model for updating.
     * @param newName value for update.
     */
    public void update(Model model, String newName) {
        map.computeIfPresent(model, (mod, ver) ->
        {
            if (model.getVersion() != ver) {
                throw new OptimisticException();
            }
            model.setName(newName);
            model.changeVersion();
            return ++ver;
        });
    }

    /**
     * ToString method.
     * @return
     */
    @Override
    public String toString() {
        return map.toString();
    }

    /**
     * Main.
     * Добавляются три модели, затем 100 потоков изменяют имя model3 на "Mike".
     * Так как оперция добавления атомарна, то блокировка не требуется.
     * OptimisticException вылетит, только если изменения будут происходить вне метода update().
     * В конце из мапы удаляется model1.
     * @param args from cmdLine.
     */
    public static void main(String[] args) {
        NonBlockCache cache = new NonBlockCache();
        Model model1 = new Model("Fedor");
        Model model2 = new Model("Jhon");
        Model model3 = new Model("Kosoi");
        cache.add(model1);
        cache.add(model2);
        cache.add(model3);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                cache.add(model1);
                cache.update(model3, "Mike");
            });
            thread.start();
        }

        //Если допустить изменение модели вне метода update() вылетит ОптимистикЭксепшен
        /*mod3.setName("Error");
        mod3.changeVersion();*/

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cache.delete(model1);
        System.out.println(cache);
    }
}