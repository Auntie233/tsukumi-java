package top.auntie.els.repository;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.auntie.els.model.TestModel;

@Repository
public interface Test2Repository extends ElasticsearchRepository<TestModel, Integer> {
}
