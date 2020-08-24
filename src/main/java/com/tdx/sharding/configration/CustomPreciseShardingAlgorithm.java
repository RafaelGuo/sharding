package com.tdx.sharding.configration;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private static final Logger logger = LoggerFactory.getLogger(CustomPreciseShardingAlgorithm.class);

    public CustomPreciseShardingAlgorithm() {
        logger.info("CustomPreciseShardingAlgorithm 初始化");
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        int hashedKey = Math.abs(preciseShardingValue.getValue().hashCode());

        for (String dbName : collection) {
            String dbRange = dbName.substring(dbName.indexOf("-") + 1);
            String[] dbRangeArr = dbRange.split("-");
            Integer begin = Integer.valueOf(dbRangeArr[0]);
            Integer end = Integer.valueOf(dbRangeArr[1]);

            if (hashedKey >= begin && hashedKey <= end) {
                return dbName;
            }
        }
        return null;
    }
}
