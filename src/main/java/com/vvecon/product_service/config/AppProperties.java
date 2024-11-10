package com.vvecon.product_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Flag flag = new Flag();

    @Setter
    @Getter
    public static class Flag {
        private int newArrivalDays;
        private int trendingViewsThreshold;
        private int trendingSalesThreshold;
        private int popularSalesThreshold;
        private double popularRatingThreshold;
    }
}

