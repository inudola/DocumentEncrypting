package io.docencrypting.Utils;

import java.io.File;
import java.util.Vector;

public class FileUtils {

    public static Vector<File> getFilesFromPath(File[] filesIn, boolean needHidden) {
        Vector<File> files = new Vector<>();
        if (filesIn.length == 0) {
            return files;
        }
        for (File path : filesIn) {
            if (path.isDirectory() && (!path.isHidden() || needHidden)) {
                File[] paths = path.listFiles();
                if (paths == null) {
                    return files;
                }
                for (File file : paths) {
                    files.addAll(getFilesFromPath(new File[] { file }, needHidden));
                }
            } else if (path.isFile() && (!path.isHidden() || needHidden)) {
                files.add(path);
            }
        }
        return files;
    }

    public static File getPath(File file) {
        File path = null;
        if (file == null) {
            return null;
        }
        return file;
    }

}
