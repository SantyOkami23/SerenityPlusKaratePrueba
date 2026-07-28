package com.automation.karate.runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Runner principal que ejecuta todas las features de Karate en paralelo.
 */
class KarateTestRunner {

    @Test
    void testAll() {
        // Ejecuta todas las features en la carpeta features/api con 5 hilos en paralelo
        Results results = Runner.path("classpath:features/api")
                .parallel(5);
        
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
