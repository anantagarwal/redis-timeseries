package io.redistimeseries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class LabelService {
    @Autowired
    RedisTemplate redisTemplate;

    public Map<String, String> getLabelChanges(LocalDate sinceTimestamp) {
        Map<String, String> changedLabels = new HashMap<>();

        // Get the labels that have been updated since the given date (epoch date)
        Set<String> labelKeys = redisTemplate.opsForZSet().rangeByScore("labels_timestamps", sinceTimestamp.toEpochDay(), Double.POSITIVE_INFINITY);

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
