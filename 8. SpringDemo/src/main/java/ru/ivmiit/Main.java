package ru.ivmiit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("ru.ivmiit//context.xml");

        /*MessageRenderer renderer = (MessageRenderer) context.getBean("renderer");

        renderer.printMessage();*/

        IndependentMessageRenderer rendarer = context.getBean(IndependentMessageRenderer.class);

        rendarer.print();
    }
}
