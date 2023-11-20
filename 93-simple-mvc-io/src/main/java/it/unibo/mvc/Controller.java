package it.unibo.mvc;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    File file = new File(System.getProperty("user.home")
            + File.separator
            + "output.txt");

    public void setFile(final File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public String getFilePath() throws IOException {
        if (Objects.isNull(file)) {
            throw new IOException("Invalid file");
        }
        return this.file.getAbsolutePath();
    }

    public void save(final String content) throws FileNotFoundException, IOException {
        try (final DataOutputStream fs = new DataOutputStream(
                new FileOutputStream(this.getFile()))) {
            fs.writeUTF(content);
        }
    }
}
