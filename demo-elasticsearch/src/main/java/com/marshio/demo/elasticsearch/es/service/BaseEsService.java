package com.marshio.demo.elasticsearch.es.service;

import com.marshio.demo.elasticsearch.es.config.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/15 18:22
 */
@Slf4j
@RequiredArgsConstructor
public class BaseEsService<T, TD> {

    // 以下代码存在个人使用习惯的差异，请酌情参考
    // TODO 疑问：elasticsearchRepository 与 restHighLevelClient 的区别是什么？

    protected final ElasticsearchRepository<T, TD> elasticsearchRepository;
    protected final RestHighLevelClient restHighLevelClient;
    protected final ElasticsearchProperties properties;


    @PostConstruct
    public void init() {
        log.info("elasticsearch init nodes is " + Arrays.toString(
                restHighLevelClient.getLowLevelClient().getNodes().toArray()));
    }

    /**
     * 新增或更新实体
     * 当实体id在索引命名空间下不存在时会新增
     * 当实体id在索引命名空间下存在时会更新
     *
     * @param entity 实体
     * @return 保存的实体
     */
    public T save(T entity) {
        return elasticsearchRepository.save(entity);
    }

    /**
     * 批量保存实体
     *
     * @param entities 实体
     * @return 保存的实体集合
     */
    public Iterable<T> saveAll(Iterable<T> entities) {
        return elasticsearchRepository.saveAll(entities);
    }

    public T update(T entity) {
        return this.save(entity);
    }

    public SearchResponse search(SearchRequest searchRequest) throws IOException {
        return this.search(searchRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse search(SearchRequest searchRequest, RequestOptions options) throws IOException {
        return restHighLevelClient.search(searchRequest, options);
    }


    /**
     * 删除实体
     *
     * @param id 实体id
     */
    public void delete(TD id) {
        elasticsearchRepository.deleteById(id);
    }
}
