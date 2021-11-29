package com.example.mybatis.cache;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.cache.Cache;
import redis.clients.jedis.Jedis;

import java.io.*;

//自定义二级缓存
public class RedisCache implements Cache {
    private final String id;
    private final Jedis jedis = new Jedis("localhost", 6379);

    public RedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        jedis.set(serialize(key), serialize(value));
    }

    @Override
    public Object getObject(Object key) {
        return unSerialize(jedis.get(serialize(key)));
    }

    @Override
    public Object removeObject(Object key) {
        return jedis.del(serialize(key));
    }

    @Override
    public void clear() {
        jedis.flushAll();
    }

    @Override
    public int getSize() {
        return jedis.dbSize().intValue();
    }

    private byte[] serialize(Object obj) {
        byte[] bytes = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private Object unSerialize(byte[] bytes) {
        if (bytes == null) return  null;
        Object object = null;
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            object = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
