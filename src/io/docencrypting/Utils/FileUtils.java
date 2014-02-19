package io.docencrypting.Utils;

import java.io.File;
import java.util.Vector;

public class FileUtils {

    public static Vector<File> getFilesFromPath(File path, boolean needHidden) {
        Vector<File> files = new Vector<>();
        if (!path.exists()) {
            return files;
        }
        if (path.isDirectory() && (!path.isHidden() || needHidden)) {
            File[] paths = path.listFiles();
            if (paths == null) {
                return files;
            }
            for (File file : paths) {
                files.addAll(getFilesFromPath(file, needHidden));
            }
        } else if (path.isFile() && (!path.isHidden() || needHidden)) {
            files.add(path);
        }
        return files;
    }

    public static File getFile(String path) {
        File file = null;
        if (path == null || path.isEmpty()) {
            return null;
        }
        try {
            file = new File(path);
        } catch (Exception ex) {
            //TODO add log message
        }
        if (file != null && !file.exists()) {
            return null;
        }
        return file;
    }

}
