/**
 * 
 */
package com.vkomlev.diploma.stats.eventprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mera.cs.eventprocessing.exception.SubscriptionException;
import com.mera.cs.eventprocessing.notification.ISubsciptionTopicLookupHelper;

/**
 * @author vkomlev
 *
 */
public class TopicLookupHelper implements ISubsciptionTopicLookupHelper {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mera.cs.eventprocessing.notification.ISubsciptionTopicLookupHelper#
     * lookupTopics(java.lang.String)
     */
    @Override
    public Map<String, List<String>> lookupTopics(String sessionId) throws SubscriptionException {
        Map<String, List<String>> result = new HashMap<>();
        List<String> employeeTopics = new ArrayList<>();
        List<String> companyTopics = new ArrayList<>();
        result.put("EMPLOYEE", employeeTopics);
        result.put("COMPANY", companyTopics);
        employeeTopics.add("ALL");
        companyTopics.add("ALL");
        return result;
    }

}
