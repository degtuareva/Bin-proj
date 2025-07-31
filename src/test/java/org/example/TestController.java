package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ServiceA serviceA;
    private final ServiceB serviceB;
    private final ServiceC serviceC;

    public TestController(ServiceA serviceA, ServiceB serviceB, ServiceC serviceC) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
        this.serviceC = serviceC;
    }

    @GetMapping("/test")
    public String test() {
        return String.format(
                "ServiceA (singleton) hash: %d\nServiceB (prototype) hash: %d\nServiceC (request) hash: %d",
                serviceA.hashCode(), serviceB.hashCode(), serviceC.hashCode()
        );
    }
}