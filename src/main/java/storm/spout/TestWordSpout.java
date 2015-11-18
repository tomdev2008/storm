package storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.util.Utils;

import java.util.Map;
import java.util.Random;

/**
 * Created by luohui on 15/11/18.
 */
public class TestWordSpout extends BaseRichSpout {
    SpoutOutputCollector collector;

    @Override public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    @Override public void nextTuple() {
        Utils.sleep(100);
        final String[] words = {"tom","jack","lucy","hello"};
        Random r = new Random();
        collector.emit(new Values(words[r.nextInt(words.length)]));
    }
}
