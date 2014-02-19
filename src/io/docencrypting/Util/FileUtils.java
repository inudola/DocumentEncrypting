package io.docencrypting.Util;

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

}
