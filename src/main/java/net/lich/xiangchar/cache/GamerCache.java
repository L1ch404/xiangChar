package net.lich.xiangchar.cache;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chenli
 * @ClassName GameCache
 * @Description
 * @Date 2022/5/27
 **/
@Service
public final class GamerCache<MainHandle> extends Cache{

    public GamerCache(){
        this.initCache();
    }

    @Override
    public void initCache() {
        date = new ConcurrentHashMap<>();
    }
}
