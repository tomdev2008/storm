package storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by luohui on 15/11/18.
 */
public class TestWordBolt extends BaseRichBolt {
    OutputCollector collector;
    @Override public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override public void execute(Tuple tuple) {
        collector.emit(tuple,new Values(tuple.getString(0)+"!!!"));
        collector.ack(tuple);
    }

    @Override public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
