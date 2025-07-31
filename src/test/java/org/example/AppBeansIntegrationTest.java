package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppBeansIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ServiceA serviceA1;

    @Autowired
    private ServiceB serviceB1;

    @Autowired
    private ServiceC serviceC1;

    @Test
    void testSingletonBean() {
        ServiceA serviceA2 = context.getBean(ServiceA.class);
        assertNotNull(serviceA1);
        assertNotNull(serviceA2);
        assertThat(serviceA1).isSameAs(serviceA2);
    }

    @Test
    void testPrototypeBean() {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ServiceB serviceB1 = context.getBean(ServiceB.class);
            ServiceB serviceB2 = context.getBean(ServiceB.class);

            assertNotNull(serviceB1);
            assertNotNull(serviceB2);

        }
    }

    @Test
    void testRequestScopedBean_sameRequest() {
        ServiceC serviceC2 = context.getBean(ServiceC.class);
        assertNotNull(serviceC1);
        assertNotNull(serviceC2);
        assertThat(serviceC1).isSameAs(serviceC2);
    }
}
