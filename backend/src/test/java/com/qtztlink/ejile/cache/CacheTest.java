package com.qtztlink.ejile.cache;

import com.qtztlink.ejile.EjileApplication;
import com.qtztlink.ejile.system.model.user.Consumer;
import com.qtztlink.ejile.system.service.user.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EjileApplication.class)
public class CacheTest {

    @Autowired
    private ConsumerService consumerService;

    @Test
    public void test() throws Exception {
        Consumer consumer1 = this.consumerService.queryConsumerById(1);
        System.out.println(consumer1);

        Consumer consumer2 = this.consumerService.queryConsumerById(1);
        System.out.println(consumer2);
    }
}
