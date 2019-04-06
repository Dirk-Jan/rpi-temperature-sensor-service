package nl.djja.rpi.temperaturesensorservice;

import nl.djja.rpi.temperaturesensorservice.rest.RESTEmbeddedJetty;

public class ServiceInitializer {
    public static void main(String[] args) {
        RESTEmbeddedJetty restEmbeddedJetty = new RESTEmbeddedJetty();
        restEmbeddedJetty.run();
    }
}
