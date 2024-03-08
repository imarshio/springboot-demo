package com.marshio.demo.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/21 13:40
 */
@JdbcTest
public class SampleTest {

    private final Log logger = LogFactory.getLog(SampleTest.class);

    private static final ExpressionParser SPEL_EXPRESSION_PARSER = new SpelExpressionParser();

    // MySQL

    /**
     * @Description JDBCTemplate usage about update(sql, parameters)
     */
    @Test
    public void test01() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String sql = "insert into user (name, age) values (?, ?)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "marshio");
        parameters.put("age", 18);
        jdbcTemplate.update(sql, parameters);
    }

    /**
     * @Description NamedParameterJdbcTemplate usage
     */
    @Test
    public void test02() {
        var jdbcTemplate = getJdbcTemplate();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        // map或者
        String sql = "insert into user (name, sex, age) values ( :name2, 1, :age1);";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name0", "marshio");
        parameters.put("name1", "marshio000");
        parameters.put("name2", "marshioooo");
        parameters.put("age0", 18);
        parameters.put("age1", 155);
        parameters.put("age2", 200);
        namedParameterJdbcTemplate.update(sql, parameters);
    }


    /**
     * @Description JDBCTemplate usage about update(sql, parameters)
     */
    @Test
    public void test03() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        String sql = "select group_concat(name) group_concat from user group by sex";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "marshio");
        parameters.put("age", 18);
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        System.out.println(SPEL_EXPRESSION_PARSER.parseExpression("size() > 0 ? get(0).get('group_concat'): ''").getValue(result));
        ;
    }

    /**
     * @Description NamedParameterJdbcTemplate usage about queryForList(sql, parameters)
     */
    @Test
    public void test04() {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "select * from user where name = :name and age = :age";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "marshio");
        parameters.put("age", 18);
        List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList(sql, parameters);
        result.forEach(System.out::println);
    }

    // postgresql

    /**
     * @Description NamedParameterJdbcTemplate usage about queryForList(sql, parameters)
     */
    @Test
    public void test05() {
        JdbcTemplate jdbcTemplate = getPostgreSqlJdbcTemplate();
        String sql = "select * from tmp";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        String s = "insert into stock_surge_telegram(global_id, title, content, publish_time, show_tags_name, auto_tags_name, auto_tags, category, tag_name, tag_code, related_stocks, weight, type, area, filter, ai_insight,aspects) values(:global_id, :title, :content, :publish_time_trans, :show_tags_name, :auto_tags_name, :auto_tags, :category, :tagName, :tagCode, :Activity_0sktqxy, :weight, :type, :area, :filter, :Activity_0cvlg43, :Activity_0k3a4se)";
        result.forEach(System.out::println);
    }

    @Test
    public void test06() {
        List<Boolean> success = List.of(true, false, true, false);
        AtomicBoolean pushSaved = new AtomicBoolean(true);
        Optional.of(success)
                .orElseThrow()
                .forEach(s -> {
                    pushSaved.set(s && pushSaved.get());
                });
        System.out.println(pushSaved.get());
    }


    private static JdbcTemplate getJdbcTemplate() {
        // 手动注册数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://mysql.marshio.com:3306/pyspider_sit_resultdb?charset=utf8mb4&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return new JdbcTemplate(dataSource);
    }

    private static JdbcTemplate getPostgreSqlJdbcTemplate() {
        // 手动注册数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://pg.marshio.com:5432/demo?currentSchema=public");
        hikariConfig.setUsername("demo");
        hikariConfig.setPassword("demo");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(1800000);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return new JdbcTemplate(dataSource);
    }
}
