package com.automation.karate.runners;

import com.intuit.karate.junit5.Karate;

class ProductsRunner {

    @Karate.Test
    Karate testProducts() {
        return Karate.run("classpath:features/api/products.feature");
    }
}
