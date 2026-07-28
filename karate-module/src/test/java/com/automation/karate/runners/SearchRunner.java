package com.automation.karate.runners;

import com.intuit.karate.junit5.Karate;

class SearchRunner {

    @Karate.Test
    Karate testSearch() {
        return Karate.run("classpath:features/api/search.feature");
    }
}
