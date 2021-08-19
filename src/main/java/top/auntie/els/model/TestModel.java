package top.auntie.els.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "test")
public class TestModel {

    @Id
    private Integer id;
    @Field
    private String name;

}
