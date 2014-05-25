package io.docencrypting.Config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Log {

    private static File logFile = new File("log.txt");

    public Log(){
        if(!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void write(String msg){
        writeInFile(createString(msg));
    }

    private static String createString(String msg) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(MyDate.currentDate()).append("\t| ").append(msg);
        return sb.append('\n').toString();
    }

    private static void writeInFile(String msg) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(logFile,
                true))) {
            out.write(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyDate {
        public static String currentDate() {
            DateFormat formatter = new SimpleDateFormat("HH:mm.ss");
            Date date = new Date();
            return formatter.format(date);
        }
    }

}
