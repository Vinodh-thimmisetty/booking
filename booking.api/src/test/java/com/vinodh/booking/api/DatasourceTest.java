package com.vinodh.booking.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class DatasourceTest {

    @Test
    public void test() {
        Assert.assertTrue(true);
    }
}
