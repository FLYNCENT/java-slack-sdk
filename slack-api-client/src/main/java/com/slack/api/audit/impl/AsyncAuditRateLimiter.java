package com.slack.api.audit.impl;

import com.slack.api.audit.AuditConfig;
import com.slack.api.methods.MethodsRateLimitTier;
import com.slack.api.rate_limits.RateLimiter;
import com.slack.api.rate_limits.WaitTime;
import com.slack.api.rate_limits.WaitTimeCalculator;
import com.slack.api.rate_limits.metrics.LastMinuteRequests;
import com.slack.api.rate_limits.metrics.MetricsDatastore;
import lombok.extern.slf4j.Slf4j;

import static com.slack.api.methods.MethodsRateLimitTier.getAllowedRequestsPerMinute;

@Slf4j
public class AsyncAuditRateLimiter implements RateLimiter {

    private static final MethodsRateLimitTier AUDIT_LOGS_RATE_LIMIT_TIER =
            MethodsRateLimitTier.Tier3;

    private final MetricsDatastore metricsDatastore;
    private final AuditWaitTimeCalculator waitTimeCalculator;

    public MetricsDatastore getMetricsDatastore() {
        return metricsDatastore;
    }

    public AsyncAuditRateLimiter(AuditConfig config) {
        this.metricsDatastore = config.getMetricsDatastore();
        this.waitTimeCalculator = new AuditWaitTimeCalculator(config);
    }

    public static class AuditWaitTimeCalculator extends WaitTimeCalculator {
        private final AuditConfig config;

        public AuditWaitTimeCalculator(AuditConfig config) {
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
        public LastMinuteRequests getLastMinuteRequests(String executorName, String teamId, String key) {
            return config.getMetricsDatastore().getLastMinuteRequests(executorName, teamId, key);
        }
    }

    @Override
    public WaitTime acquireWaitTime(String teamId, String methodName) {
        return waitTimeCalculator.calculateWaitTime(
                teamId,
                methodName,
                getAllowedRequestsPerMinute(AUDIT_LOGS_RATE_LIMIT_TIER)
        );
    }

    @Override
    public WaitTime acquireWaitTimeForChatPostMessage(String teamId, String channel) {
        return waitTimeCalculator.calculateWaitTimeForChatPostMessage(teamId, channel);
    }

}
