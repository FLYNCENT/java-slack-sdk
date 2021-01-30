package com.slack.api.methods.impl;

import com.slack.api.methods.MethodsConfig;
import com.slack.api.methods.MethodsRateLimits;
import com.slack.api.rate_limits.RateLimiter;
import com.slack.api.rate_limits.WaitTime;
import com.slack.api.rate_limits.WaitTimeCalculator;
import com.slack.api.rate_limits.metrics.MetricsDatastore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncMethodsRateLimiter implements RateLimiter {

    private final MetricsDatastore metricsDatastore;
    private final WaitTimeCalculator waitTimeCalculator;

    public MetricsDatastore getMetricsDatastore() {
        return metricsDatastore;
    }

    public AsyncMethodsRateLimiter(MethodsConfig config) {
        this.metricsDatastore = config.getMetricsDatastore();
        this.waitTimeCalculator = new MethodsWaitTimeCalculator(config);
    }

    @Override
    public WaitTime acquireWaitTime(String teamId, String methodName) {
        return waitTimeCalculator.calculateWaitTime(
                teamId,
                methodName,
                waitTimeCalculator.getAllowedRequestsPerMinute(MethodsRateLimits.lookupRateLimitTier(methodName))
        );
    }

    @Override
    public WaitTime acquireWaitTimeForChatPostMessage(String teamId, String channel) {
        return waitTimeCalculator.calculateWaitTimeForChatPostMessage(
                teamId,
                channel
        );
    }

    public static class MethodsWaitTimeCalculator extends WaitTimeCalculator {
        private final MethodsConfig config;

        public MethodsWaitTimeCalculator(MethodsConfig config) {
            this.config = config;
        }

        @Override
        public Integer getNumberOfNodes() {
            return config.getMetricsDatastore().getNumberOfNodes();
        }

        @Override
        public String getExecutorName() {
            return config.getExecutorName();
        }

        @Override
        public Integer getNumberOfLastMinuteRequests(String executorName, String teamId, String key) {
            return config.getMetricsDatastore().getNumberOfLastMinuteRequests(executorName, teamId, key);
        }

        @Override
        public com.slack.api.rate_limits.metrics.LastMinuteRequests getLastMinuteRequests(String executorName, String teamId, String key) {
            return config.getMetricsDatastore().getLastMinuteRequests(executorName, teamId, key);
        }
    }
}
