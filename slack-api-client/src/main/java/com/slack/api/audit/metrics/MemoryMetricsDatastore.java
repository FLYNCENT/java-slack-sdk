package com.slack.api.audit.metrics;

import com.slack.api.audit.AuditApiResponse;
import com.slack.api.audit.impl.AsyncExecutionSupplier;
import com.slack.api.audit.impl.AsyncRateLimitQueue;
import com.slack.api.rate_limits.RateLimiter;
import com.slack.api.rate_limits.metrics.impl.BaseMemoryMetricsDatastore;
import com.slack.api.util.thread.DaemonThreadExecutorServiceProvider;
import com.slack.api.util.thread.ExecutorServiceProvider;

public class MemoryMetricsDatastore extends BaseMemoryMetricsDatastore<
        AsyncExecutionSupplier<? extends AuditApiResponse>, AsyncRateLimitQueue.AuditMessage> {

    public MemoryMetricsDatastore(int numberOfNodes) {
        super(numberOfNodes);
    }

    public MemoryMetricsDatastore(
            int numberOfNodes,
            boolean backgroundJobEnabled
    ) {
        super(numberOfNodes, DaemonThreadExecutorServiceProvider.getInstance(), backgroundJobEnabled, RateLimiter.DEFAULT_BACKGROUND_JOB_INTERVAL_MILLIS);
    }

    public MemoryMetricsDatastore(
            int numberOfNodes,
            boolean backgroundJobEnabled,
            long cleanerExecutionIntervalMilliseconds
    ) {
        super(numberOfNodes, DaemonThreadExecutorServiceProvider.getInstance(), backgroundJobEnabled, cleanerExecutionIntervalMilliseconds);
    }

    public MemoryMetricsDatastore(
            int numberOfNodes,
            ExecutorServiceProvider executorServiceProvider,
            boolean backgroundJobEnabled,
            long cleanerExecutionIntervalMilliseconds
    ) {
        super(numberOfNodes, executorServiceProvider, backgroundJobEnabled, cleanerExecutionIntervalMilliseconds);
    }

    @Override
    protected String getMetricsType() {
        return "AUDIT_LOGS";
    }

    @Override
    public AsyncRateLimitQueue getRateLimitQueue(String executorName, String teamId) {
        return AsyncRateLimitQueue.get(executorName, teamId);
    }

}
