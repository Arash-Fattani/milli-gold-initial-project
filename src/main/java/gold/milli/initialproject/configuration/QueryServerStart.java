package gold.milli.initialproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component

public class QueryServerStart {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(org.springframework.context.event.ContextRefreshedEvent event) {
        // Define your SQL query here
        String sql = "YOUR SQL QUERY HERE";

        // Execute the query
        jdbcTemplate.execute(sql);
    }
}

