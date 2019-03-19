package ru.ivmiit;

public class MessageRendererStandardImpl implements MessageRenderer {
    private Message message;

    public MessageRendererStandardImpl(Message message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message.getText());
    }
}
