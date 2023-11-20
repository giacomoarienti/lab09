package it.unibo.mvc;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public interface Controller {
    public void output() throws IOException;

    public void setNextOutput(final String output);

    public String getNextOutput();

    public List<String> getPrintedStrings();
}
