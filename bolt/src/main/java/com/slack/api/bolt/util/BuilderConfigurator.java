package com.slack.api.bolt.util;

@FunctionalInterface
public interface BuilderConfigurator<Builder> {

    Builder configure(Builder builder);

}
