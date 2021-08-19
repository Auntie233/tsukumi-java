package top.auntie.els.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import top.auntie.els.model.TestModel;

public class TestRepository extends AbstractElasticsearchRepository<TestModel, Integer> {



}
