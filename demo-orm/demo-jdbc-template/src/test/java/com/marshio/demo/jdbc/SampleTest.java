package com.marshio.demo.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/21 13:40
 */
@JdbcTest
public class SampleTest {

    private final Log logger = LogFactory.getLog(SampleTest.class);

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
        String sql = "select * from user";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "marshio");
        parameters.put("age", 18);
        List<Map<String, Object>> result =  jdbcTemplate.queryForList(sql);
        result.forEach(System.out::println);
        // revert commit test 01 commit
    }

    private static JdbcTemplate getJdbcTemplate() {
        // 手动注册数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://47.116.58.10:3306/pyspider_sit_resultdb?charset=utf8mb4&useSSL=false");
        hikariConfig.setUsername("pyspider_user");
        hikariConfig.setPassword("ND0328qSywfre");
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
}
