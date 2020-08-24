package com.tdx.sharding.controller;

import com.tdx.sharding.domain.ResultModel;
import com.tdx.sharding.service.ShardingService;
import com.tdx.sharding.util.TdxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShardingDemo {
    @Resource
    private ShardingService shardingService;

    @RequestMapping(value = "/createArticle", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel createArticle(@RequestParam(value = "userId") String userId,
                                     @RequestParam(value = "title") String title,
                                     @RequestParam(value = "summary") String summary,
                                     @RequestParam(value = "content") String content) {
        Map<String, Object> map = new HashMap<>(1);

        map.put("title", title);
        map.put("summary", summary);
        map.put("content", content);
        map.put("userId", userId);

        String resId = shardingService.createArticle(map);
        return TdxUtil.getResultModel("resId", resId);
    }

    @RequestMapping(value = "/queryArticleDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel queryArticleDetail(@RequestParam(value = "resId") String resId) {
        Map<String, Object> article = shardingService.selectArticleDetail(resId);
        String[] items = {"resId", "title", "summary", "content", "userId", "viewCount"};
        return TdxUtil.getResultModel(items, article);
    }

    @RequestMapping(value = "/queryArticleList", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel queryArticleList(@RequestParam(value = "resId") String resId) {
        List<Map<String, Object>> article = shardingService.selectArticleList(resId);
        String[] items = {"resId", "title", "summary", "content", "userId", "viewCount"};
        int num = article == null ? 0 : article.size();
        return TdxUtil.getResultModel(num, items, article);
    }

    @RequestMapping(value = "/queryArticle", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel queryArticle(@RequestParam(value = "currentPage") Integer currentPage,
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @RequestParam(value = "userId") String userId) {
        List<Map<String, Object>> article = shardingService.selectArticle(currentPage, pageSize, userId);
        String[] items = {"resId", "title", "summary", "content", "userId", "viewCount"};
        int num = shardingService.selectArticleCount(userId);
        return TdxUtil.getResultModel(num, items, article);
    }
}
