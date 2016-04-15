package com.mera.cs.eventprocessing.notification;

import java.util.List;
import java.util.Map;

import com.mera.cs.eventprocessing.exception.SubscriptionException;

public interface ISubsciptionTopicLookupHelper {

    Map<String, List<String>> lookupTopics(String sessionId) throws SubscriptionException;
}
