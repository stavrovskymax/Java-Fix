package ru.ivmiit.models;

public class Car {
    private int id;
    private User owner_id;
    private String model;

    public Car(int id, User owner_id, String model) {
        this.id = id;
        this.owner_id = owner_id;
        this.model = model;
    }

    public Car(User owner_id, String model) {
        this.owner_id = owner_id;
        this.model = model;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
