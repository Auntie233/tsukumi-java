package top.auntie.els.repository;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.AbstractElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import top.auntie.els.model.TestModel;

public class TestRepository extends AbstractElasticsearchRepository<TestModel, Integer> {


    public TestRepository(ElasticsearchEntityInformation<TestModel, Integer> metadata, ElasticsearchOperations operations) {
        super(metadata, operations);
    }

    @Override
    protected String stringIdRepresentation(Integer integer) {
        return String.valueOf(integer);
    }
}
