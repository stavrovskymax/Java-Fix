package ru.ivmiit;

public class MessageRendererErrorImpl implements MessageRenderer {
    private Message message;

    public MessageRendererErrorImpl(Message message) {
        this.message = message;
    }

    public void printMessage() {
        System.err.println(message.getText());
    }
}
