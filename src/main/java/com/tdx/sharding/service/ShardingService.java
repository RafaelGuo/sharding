package com.tdx.sharding.service;

import com.tdx.sharding.mapper.ShardingMapper;
import com.tdx.sharding.util.TdxUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShardingService {
    @Resource
    private ShardingMapper shardingMapper;

    public String createArticle(Map map) {
        String resId = TdxUtil.getUUID();
        String userId = TdxUtil.getUUID();

        try {
            map.put("res_id", resId);
            map.put("userId", userId);

            shardingMapper.insertResourceBasic(map);
            shardingMapper.insertEssay(map);
            shardingMapper.insertResourceCache(map);
            shardingMapper.insertUserResRelation(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }

    public Map<String, Object> selectArticleDetail(String resId) {
        return shardingMapper.selectArticleDetail(resId);
    }

    public List<Map<String, Object>> selectArticleList(String resId) {
        String[] resIds = resId.split(",");
        List<String> list = Arrays.asList(resIds);
        return shardingMapper.selectArticleList(list);
    }

    public List<Map<String,Object>> selectArticle(Integer currentPage, Integer pageSize, String userId) {
        Integer beginIndex = (currentPage - 1) * pageSize;
        List<String> resIds = shardingMapper.selectResIdByUserId(beginIndex, pageSize, userId);
        List<Map<String,Object>> data = shardingMapper.selectArticleList(resIds);
        return data;
    }

    public int selectArticleCount(String userId) {
        int num = shardingMapper.selectArticleCount(userId);
        return num;
    }
}
