package gold.milli.initialproject.configuration;

import io.hypersistence.utils.hibernate.query.QueryStackTraceLogger;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HibernateConfig implements HibernatePropertiesCustomizer {
    @Override
    public void customize(Map<String, Object> hibernateProperties){
        hibernateProperties.put(
                AvailableSettings.STATEMENT_INSPECTOR,
                new QueryStackTraceLogger(
                        "org.springframework.samples.petclinic"
                )
        );
    }
}
