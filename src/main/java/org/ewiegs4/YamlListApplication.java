package org.ewiegs4;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableConfigurationProperties(YamlListApplication.ListProperties.class)
public class YamlListApplication {

    private static final Logger LOG = LoggerFactory.getLogger(YamlListApplication.class);

    public static void main(final String[] args) {
        SpringApplication.run(YamlListApplication.class, args);
    }

    @Component
    public static class PropertyLogger implements ApplicationListener<ApplicationReadyEvent> {

        private final ListProperties listProps;

        public PropertyLogger(final ListProperties listProps) {
            this.listProps = listProps;
        }

        public void onApplicationEvent(final ApplicationReadyEvent event) {
            LOG.info("List contents: {}", listProps.getList());
        }
    }

    @ConfigurationProperties("custom")
    public static class ListProperties {

        private List<String> list;

        public List<String> getList() {
            return list;
        }

        public void setList(final List<String> list) {
            this.list = list;
        }
    }
}
