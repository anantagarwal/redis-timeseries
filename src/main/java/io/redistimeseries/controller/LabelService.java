package io.redistimeseries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LabelService {
    @Autowired
    RedisTemplate redisTemplate;

    public Map<String, String> getLabelChanges(long sinceTimestamp) {
        Map<String, String> changedLabels = new HashMap<>();

        // Get the labels that have been updated since the given timestamp
        Set<String> labelKeys = redisTemplate.opsForZSet().rangeByScore("labels_timestamps", sinceTimestamp, Double.POSITIVE_INFINITY);

        // Batch retrieve label values
        List<String> labelValues = redisTemplate.opsForHash().multiGet("labels", labelKeys);

        // Populate changedLabels map
        int index = 0;
        for (String labelKey : labelKeys) {
            String labelValue = labelValues.get(index++);
            changedLabels.put(labelKey, labelValue);
        }

        return changedLabels;
    }
}
