package ru.ivmiit;

public class HelloMessage implements Message {
    private String text;

    public HelloMessage(String text) {
        this.text = "Hello " + text;
    }

    public String getText() {
        return text;
    }
}
