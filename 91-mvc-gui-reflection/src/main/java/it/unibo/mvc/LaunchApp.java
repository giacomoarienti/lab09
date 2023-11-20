package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private static final int NUM_VIEWS = 3;

    private LaunchApp() {
    }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException    if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException     if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException    if the constructor throws exceptions
     * @throws IllegalAccessException    in case of reflection issues
     * @throws IllegalArgumentException  in case of reflection issues
     */
    public static void main(final String... args)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        // use reflections to instanciate classes
        final List<String> classes = List.of("Swing", "Cli");
        for (final String clsname : classes) {
            final Class<?> viewClass = Class.forName("it.unibo.mvc.view.DrawNumber" + clsname + "View");
            final Constructor<?> viewConst = viewClass.getConstructor();
            for (int i = 0; i < NUM_VIEWS; i++) {
                app.addView((DrawNumberView) viewConst.newInstance());
            }
        }

    }
}
