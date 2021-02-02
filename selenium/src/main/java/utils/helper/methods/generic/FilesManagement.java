package utils.helper.methods.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code FilesManagement} class, as the name says, contains a set of useful tools for managing files.
 */
public class FilesManagement {
    private static final Logger log = LoggerFactory.getLogger(FilesManagement.class);

    /**
     * Creates a directory in the specified path if it does not exist already.
     * If necessary, intermediate folders are also created.
     *
     * @param destFolderPath the string representing the path to the requested directory.
     *                       Paths relative to the project directory are allowed.
     */
    public static void createDestinationFolder(String destFolderPath) {
        File destFolder = new File(destFolderPath);

        if (!destFolder.exists()) {

            if (destFolder.mkdirs()) {
                log.info("Folders: {} created", destFolder);
            } else {
                log.warn("Folders: {} couldn't be created", destFolder);
            }

        }
    }

}
