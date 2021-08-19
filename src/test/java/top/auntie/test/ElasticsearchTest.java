package top.auntie.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.auntie.Application;
import top.auntie.els.model.TestModel;
import top.auntie.els.repository.Test2Repository;


@SpringBootTest(classes = Application.class)
public class ElasticsearchTest {

    @Autowired
    private Test2Repository test2Repository;

    @Test
    public void test() {
        System.out.println("1");
        TestModel testModel = new TestModel();
        testModel.setId(1);
        testModel.setName("邰昊");
        test2Repository.save(testModel);
        System.out.println(JSON.toJSONString(test2Repository.findAll(), SerializerFeature.DisableCircularReferenceDetect));
        System.out.println(JSON.toJSONString(test2Repository.findByName("邰"), SerializerFeature.DisableCircularReferenceDetect));
        System.out.println(JSON.toJSONString(test2Repository.findByName("邰昊"), SerializerFeature.DisableCircularReferenceDetect));
        System.out.println(JSON.toJSONString(test2Repository.findByName("张昊"), SerializerFeature.DisableCircularReferenceDetect));
    }

}
