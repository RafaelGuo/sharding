package com.tdx.sharding.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ShardingMapper {
    int insertResourceBasic(Map map);

    int insertResourceCache(Map map);

    int insertEssay(Map map);

    int insertUserResRelation(Map map);

    Map<String, Object> selectArticleDetail(@Param("resId") String resId);

    List<Map<String, Object>> selectArticleList(List<String> list);

    List<String> selectResIdByUserId(@Param("beginIndex") Integer beginIndex,
                                                  @Param("pageSize") Integer pageSize,
                                                  @Param("userId") String userId);

    List<Map<String, Object>> selectArticle(@Param("beginIndex") Integer beginIndex,
                                            @Param("pageSize") Integer pageSize,
                                            @Param("userId") String userId);

    int selectArticleCount(@Param("userId") String userId);
}
