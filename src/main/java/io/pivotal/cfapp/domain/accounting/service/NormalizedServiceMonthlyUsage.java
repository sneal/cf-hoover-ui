package io.pivotal.cfapp.domain.accounting.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NormalizedServiceMonthlyUsage {

    private Integer year;
    private Integer month;
    private String serviceName;
    private String serviceGuid;
    private String servicePlanName;
    private String servicePlanGuid;
    private Double durationInHours;
    private Integer averageInstances;
    private Integer maximumInstances;

    public static List<NormalizedServiceMonthlyUsage> listOf(ServiceUsageReport report) {
        List<NormalizedServiceMonthlyUsage> result = new ArrayList<>();
        List<ServiceUsageMonthlyAggregate> monthlyAggregates = report.getMonthlyServiceReports();
        for (ServiceUsageMonthlyAggregate suma: monthlyAggregates) {
            String serviceName = suma.getServiceName();
            String serviceGuid = suma.getServiceGuid();
            List<ServiceUsageMonthly> monthlyServiceUsageMetrics = suma.getUsages();
            for (ServiceUsageMonthly metrics: monthlyServiceUsageMetrics) {
                result.add(
                    NormalizedServiceMonthlyUsage.builder()
                        .year(metrics.getYear())
                        .month(metrics.getMonth())
                        .serviceGuid(serviceGuid)
                        .serviceName(serviceName)
                        .averageInstances(metrics.getAverageInstances())
                        .maximumInstances(metrics.getMaximumInstances())
                        .durationInHours(metrics.getDurationInHours())
                        .build()
                );
            }
        }
        return result;
    }
}