package com.example.asynctest.dto;

import java.util.Map;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/4/27 10:56
 */
public class QueryRequestDTO {
    private QueryConditionDTO queryCondition;

    public QueryConditionDTO getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(QueryConditionDTO queryCondition) {
        this.queryCondition = queryCondition;
    }


}

class QueryConditionDTO {
    private Map<String, Object> argMap;
    private String prevId;

    public Map<String, Object> getArgMap() {
        return argMap;
    }

    public void setArgMap(Map<String, Object> argMap) {
        this.argMap = argMap;
    }

    public String getPrevId() {
        return prevId;
    }

    public void setPrevId(String prevId) {
        this.prevId = prevId;
    }
}
