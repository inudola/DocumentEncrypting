package io.docencrypting;

import io.docencrypting.Controllers.ApplicationController;

/**
 * Main class
 */
public class DocumentEncrypting {

    /**
     * Entry point
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController(args);
        applicationController.run();
    }
}
