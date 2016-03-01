package org.camel.playground.splitAndAggregate.aggregation;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dragos.triteanu on 3/1/16.
 */
public class SetAggregationStrategy implements AggregationStrategy {


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String body = newExchange.getIn().getBody(String.class);

        if(null == oldExchange){
            Set<String> someSet = new HashSet<>();
            someSet.add(body);
            newExchange.getIn().setBody(someSet);
            return newExchange;
        }else{
            Set<String> someSet = Collections.checkedSet(oldExchange.getIn().getBody(Set.class), String.class);
            someSet.add(body);
            return oldExchange;
        }
    }
}
