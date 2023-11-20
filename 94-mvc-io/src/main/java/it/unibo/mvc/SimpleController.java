package it.unibo.mvc;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SimpleController implements Controller {

    private final List<String> printedStrings = new ArrayList<>();
    private OutputStream os = System.out;
    private String nextOutput;

    @Override
    public void output() throws IOException {
        this.requireNextStringNotNull();
        /* print string */
        final DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF(nextOutput);
        /* save printed string */
        printedStrings.add(nextOutput);
        nextOutput = null;
    }

    @Override
    public void setNextOutput(final String output) {
        Objects.requireNonNull(output);
        this.nextOutput = output;
    }

    @Override
    public String getNextOutput() {
        this.requireNextStringNotNull();
        return nextOutput;
    }

    @Override
    public List<String> getPrintedStrings() {
        return List.copyOf(printedStrings);
    }

    private void requireNextStringNotNull() {
        if (Objects.isNull(nextOutput)) {
            throw new IllegalStateException("Next string to output is null");
        }
    }
}
