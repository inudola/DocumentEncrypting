package io.docencrypting;

import io.docencrypting.Controllers.ApplicationController;

public class DocumentEncrypting {

    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController(args);
        applicationController.run();
    }
}
