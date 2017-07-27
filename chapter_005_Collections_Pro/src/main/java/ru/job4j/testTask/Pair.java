package ru.job4j.testTask;

/**
 * Class Pair.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 27.07.2017
 */
public class Pair {
    /**
     * Order price.
     */
    private Float price;

    /**
     * Order volume.
     */
    private Integer volume;

    /**
     * Constructor for Pair.
     * @param price for order.
     * @param volume for order.
     */
    public Pair(Float price, Integer volume) {
        this.price = price;
        this.volume = volume;
    }

    /**
     * Volume setter.
     * @param volume for setting.
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * Price getter.
     * @return price.
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Volume getter.
     * @return volume.
     */
    public Integer getVolume() {
        return volume;
    }
}