package com.marshio.demo.elasticsearch.es.service;

import cn.hutool.json.JSONUtil;
import com.marshio.demo.elasticsearch.es.config.ElasticsearchProperties;
import com.marshio.demo.elasticsearch.es.entity.Doc;
import com.marshio.demo.elasticsearch.es.request.DocQueryRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marshio
 * @desc ...
 * @create 2024/4/15 22:27
 */

@Service
public class DocEsService extends BaseEsService<Doc, String> {
    public DocEsService(ElasticsearchRepository<Doc, String> elasticsearchRepository,
                        RestHighLevelClient restHighLevelClient,
                        ElasticsearchProperties properties) {
        super(elasticsearchRepository, restHighLevelClient, properties);
    }

    public Page<Doc> search(DocQueryRequest query, PageRequest pageRequest) throws IOException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.matchQuery(Doc.Fields.globalId, query.getGlobalId()));

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder);
        searchSourceBuilder.from(pageRequest.getPageNumber()).size(pageRequest.getPageSize());

        SearchRequest searchRequest = new SearchRequest(properties.getIndexName()).source(searchSourceBuilder);
        SearchResponse response = this.search(searchRequest);
        long total = response.getHits().getTotalHits().value;
        SearchHit[] hits = response.getHits().getHits();
        List<Doc> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            // 转成实体类即可
            hit.getSourceAsString();
            Doc doc = JSONUtil.toBean(hit.getSourceAsString(), Doc.class);
            list.add(doc);
        }

        return new PageImpl<>(list, pageRequest, total);
    }
}
