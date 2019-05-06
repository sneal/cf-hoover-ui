package io.pivotal.cfapp.ui;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import org.springframework.beans.factory.annotation.Autowired;

import io.pivotal.cfapp.domain.accounting.service.NormalizedServicePlanMonthlyUsage;
import io.pivotal.cfapp.repository.MetricCache;


@Route(value = "accounting/service/plans")
@Theme(Material.class)
public class ServicePlanUsageReportView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    public ServicePlanUsageReportView(
        MetricCache cache) {
        // TODO Resource bundle for title and tile labels
        H2 title = new H2("Accounting » Service » Plans");
        HorizontalLayout firstRow = new HorizontalLayout();
        GridTile<NormalizedServicePlanMonthlyUsage> tile = new GridTile<>("Service » Plans » Monthly", NormalizedServicePlanMonthlyUsage.class, NormalizedServicePlanMonthlyUsage.listOf(cache.getServiceUsage()), new String[] { "year", "month", "serviceName", "serviceGuid", "servicePlanName", "servicePlanGuid", "averageInstances", "maximumInstances", "durationInHours" });
        firstRow.add(tile);
        firstRow.setSizeFull();
        add(title, firstRow);
        setSizeFull();
    }

}