package com.example.demo.model;

public class CustomersFavoriteGenre {
    /**
     * This class is used to store & display the customer's favorite genre in the database.
     *
     */
    int id;

    String name;

    String genre;

    public CustomersFavoriteGenre(int id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Customer " +
                '\'' + name + '\'' +
                " with id " + '\'' +
                id + '\'' +
                " likes " + genre;
    }
}
