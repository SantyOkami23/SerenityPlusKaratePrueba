package com.automation.karate.runners;

import com.intuit.karate.junit5.Karate;

class UsersRunner {

    @Karate.Test
    Karate testUsers() {
        return Karate.run("classpath:features/api/user_management.feature");
    }
}
