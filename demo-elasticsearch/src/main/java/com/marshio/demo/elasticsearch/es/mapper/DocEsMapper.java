package com.marshio.demo.elasticsearch.es.mapper;

import com.marshio.demo.elasticsearch.es.entity.Doc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author marshio
 * @desc @Component 可以被扫描替换如： @SpringBootApplication(scanBasePackages = "com.marshio.demo")
 * @create 2024/4/16 9:59
 */
@Component
public interface DocEsMapper extends ElasticsearchRepository<Doc, String> {
}
