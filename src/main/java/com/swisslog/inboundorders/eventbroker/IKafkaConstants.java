package com.swisslog.inboundorders.eventbroker;

public interface IKafkaConstants 
{
    public static String KAFKA_BROKERS = "kafka-service.sl-system:9092";
    public static Integer MESSAGE_COUNT=10;
    public static String CLIENT_ID="inboundorders";
    public static String TOPIC_NAME="test";
    public static String GROUP_ID_CONFIG="demoConsumerGroup";
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
    public static Integer MAX_POLL_RECORDS=1;
}