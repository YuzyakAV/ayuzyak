package ru.job4j.testTask;

/**
 * Class Order.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 25.07.2017
 */
public class Order {
    /**
     * Order name.
     */
    private String name;

    /**
     * Operation type (buy or sell).
     */
    private String operation;

    /**
     * Order price.
     */
    private float price;

    /**
     * Order value.
     */
    private int volume;

    /**
     * Order ID.
     */
    private int id;

    /**
     * Constructor for Order.
     * @param name for order.
     * @param operation for order.
     * @param price for order.
     * @param volume for order.
     * @param id of order.
     */
    public Order(String name, String operation, float price, int volume, int id) {
        this.name = name;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Name setter.
     * @param name for setting.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Operation setter.
     * @param operation for setting.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Price setter.
     * @param price for setting.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Volume setter.
     * @param volume for setting.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * ID setter.
     * @param id for setting.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Operation getter.
     * @return operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Price getter.
     * @return price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Volume getter.
     * @return volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * ID getter.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Overriding method equals().
     * @param o - object for compare.
     * @return true if objects are same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    /**
     * Return hash code value for object.
     * @return int value.
     */
    @Override
    public int hashCode() {
        return id;
    }
}