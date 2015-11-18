package storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import storm.bolt.TestWordBolt;
import storm.spout.TestWordSpout;
import storm.util.Utils;

/**
 * Created by luohui on 15/11/18.
 */
public class TestWordTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        System.out.println("=========================");
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word", new TestWordSpout(), 10);//发射一个字符如 tom
        builder.setBolt("test1",new TestWordBolt(),3).shuffleGrouping("word");//tom!!!
        builder.setBolt("test2",new TestWordBolt(),2).shuffleGrouping("test1");//tom!!!!!!

        Config config = new Config();
        config.setDebug(true);
        if(args!=null&&args.length>0){
            config.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0],config,builder.createTopology());
        }else{
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("testWord",config,builder.createTopology());
            Utils.sleep(10000);
            cluster.killTopology("testWord");
            cluster.shutdown();
        }
    }
}
