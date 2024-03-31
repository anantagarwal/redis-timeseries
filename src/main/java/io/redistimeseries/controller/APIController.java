package io.redistimeseries.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("v1/api")
@AllArgsConstructor
public class APIController {

    private RedisTemplate<String, Object> template;

    private LabelService labelService;

    @GetMapping("/labels/changes")
    public Map<String, String> getLabelChanges(@RequestParam long since) {
        return labelService.getLabelChanges(since);
    }

}
