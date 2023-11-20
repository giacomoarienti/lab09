package it.unibo.mvc.view;

import java.util.Objects;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberCliView implements DrawNumberView {

    private static final String NEW_GAME = ": a new game starts!";

    private DrawNumberController controller;

    @Override
    public void setController(DrawNumberController observer) {
        Objects.requireNonNull(observer);
        this.controller = observer;
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> plainMessage(res.getDescription());
            case YOU_WON -> {
                plainMessage(res.getDescription() + NEW_GAME);
                controller.resetGame();
            }
            case YOU_LOST -> {
                plainMessage(res.getDescription() + NEW_GAME);
                controller.resetGame();
            }
            default -> throw new IllegalStateException("Unknown game state");
        }
    }

    @Override
    public void start() {
        plainMessage("Start" + NEW_GAME);
    }

    private void plainMessage(final String message) {
        System.out.println(message);
    }
}