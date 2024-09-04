package com.marshio.demo.caffeine;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.support.AsyncConnectionPoolSupport;
import io.lettuce.core.support.AsyncPool;
import io.lettuce.core.support.BoundedAsyncPool;
import io.lettuce.core.support.BoundedPoolConfig;

import java.util.concurrent.CompletionStage;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 13:00
 */
public class LettuceApplication {

    // public static void main(String[] args) {
    //     RedisClient redisClient = RedisClient.create("redis://localhost:6379");
    //
    //     try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
    //         RedisAsyncCommands<String, String> asyncCommands = connection.async();
    //
    //         // Asynchronously store & retrieve a simple string
    //         asyncCommands.set("foo", "bar").get();
    //         System.out.println(asyncCommands.get("foo").get()); // prints bar
    //
    //         // Asynchronously store key-value pairs in a hash directly
    //         Map<String, String> hash = new HashMap<>();
    //         hash.put("name", "John");
    //         hash.put("surname", "Smith");
    //         hash.put("company", "Redis");
    //         hash.put("age", "29");
    //         asyncCommands.hset("user-session:123", hash).get();
    //
    //         System.out.println(asyncCommands.hgetall("user-session:123").get());
    //         // Prints: {name=John, surname=Smith, company=Redis, age=29}
    //     } catch (ExecutionException | InterruptedException e) {
    //         throw new RuntimeException(e);
    //     } finally {
    //         redisClient.shutdown();
    //     }
    // }

    public static void main(String[] args) {
        RedisClient client = RedisClient.create();
        String host = "localhost";
        int port = 6379;

        CompletionStage<BoundedAsyncPool<StatefulRedisConnection<String, String>>> poolFuture
                = AsyncConnectionPoolSupport.createBoundedObjectPoolAsync(
                () -> client.connectAsync(StringCodec.UTF8, RedisURI.create(host, port)),
                BoundedPoolConfig.create());

        AsyncPool<StatefulRedisConnection<String, String>> pool = poolFuture.toCompletableFuture().join();
    }

}
