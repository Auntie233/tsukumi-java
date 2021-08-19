package top.auntie.els.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "test")
public class TestModel {

    private Integer id;
    private String name;

}
