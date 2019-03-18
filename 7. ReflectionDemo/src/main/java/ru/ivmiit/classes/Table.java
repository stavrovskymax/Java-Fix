package ru.ivmiit.classes;

public class Table {
    public int size;
    public String color;

    public Table(int size, String color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Table{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }
}
