package net.lich.xiangchar.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author chenli
 * @ClassName Cache
 * @Description
 * @Date 2022/5/27
 **/
public abstract class Cache<T> {


    public volatile Map<String,T> date;

    public T get(String key){
        return date.get(key);
    }

    public T remove(String key){
        return date.remove(key);
    }

    public T put(String key,T value){
        return date.put(key,value);
    }

    public boolean containsKey(String key){
        return date.containsKey(key);
    }
    /**
     * 初始化date
     */
    public abstract void initCache();
}
