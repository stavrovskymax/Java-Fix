package ru.ivmiit;

public class ByeMessage implements Message {
    private String text;

    public ByeMessage(String text) {
        this.text = "Bye " + text;
    }

    public String getText() {
        return text;
    }
}
