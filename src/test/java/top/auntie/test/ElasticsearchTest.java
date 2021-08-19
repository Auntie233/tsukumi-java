package top.auntie.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.auntie.Application;


@SpringBootTest(classes = Application.class)
public class ElasticsearchTest {

    @Test
    public void test() {
        System.out.println("1");
    }

}
