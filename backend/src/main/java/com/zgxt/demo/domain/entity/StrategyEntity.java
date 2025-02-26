package com.zgxt.demo.domain.entity;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {

    private Integer id;

    private String ruleModels;

    public List<String> ruleModels() {
        if (StringUtils.isBlank(ruleModels)) return null;
        return Arrays.stream(ruleModels.split(","))
                .collect(Collectors.toList());
    }

}
