package com.automation.karate.runners;

import com.intuit.karate.junit5.Karate;

class BrandsRunner {

    @Karate.Test
    Karate testBrands() {
        return Karate.run("classpath:features/api/brands.feature");
    }
}
