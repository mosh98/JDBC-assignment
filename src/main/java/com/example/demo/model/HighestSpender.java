package com.example.demo.model;

public class HighestSpender {
    /**
     * This class is used to store & display the highest spender in the database.
     *
     */

    int id;
    String name;

    /**
     * Constructor for HighestSpender
     * @param id
     * @param name
     */
    public HighestSpender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getters & Setters
     * @return different fields
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "The highest spender is " +
                 name +
                " with id '" + id + '\'';
    }
}
