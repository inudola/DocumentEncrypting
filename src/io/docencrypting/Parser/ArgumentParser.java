package io.docencrypting.Parser;

import io.docencrypting.Config.AppConfig;

import java.io.File;

public class ArgumentParser {

    public static void parse(String[] args) {
        AppConfig appConfig = AppConfig.getInstance();

        System.out.println(args.length);

        for(int i = 0; i < args.length ; i++)
        {
            File file = new File(args[i]);
            if(file.exists())
            {
                //asd
            }

            if(args[i].equals(appConfig.getFileIn()))
            {

            }

           System.out.println(args[i]);

        }
    }
}
