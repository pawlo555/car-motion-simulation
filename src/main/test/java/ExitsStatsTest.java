import Utilities.ExitsStats;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;


public class ExitsStatsTest {

    @Test
    public void carsOnExitTest() {
        ExitsStats stats = new ExitsStats();
        stats.nextEpoch();
        Assert.assertEquals(stats.getLastEpochFlow(), 0);
        stats.carOnExit();
        Assert.assertEquals(stats.getLastEpochFlow(), 1);
        stats.carOnExit();
        stats.carOnExit();
        Assert.assertEquals(stats.getLastEpochFlow(), 3);
        stats.nextEpoch();
        Assert.assertEquals(stats.getLastEpochFlow(), 0);
    }

    @Test
    public void getNEpochTest() {
        ExitsStats stats = new ExitsStats();
        stats.nextEpoch();
        stats.nextEpoch();
        List<Integer> flowList = stats.getLastNEpoch(4);
        Assert.assertEquals(2, flowList.size());
        Assert.assertEquals(0, (int) flowList.get(0));
        Assert.assertEquals(0, (int) flowList.get(1));
        stats.nextEpoch();
        stats.carOnExit();
        List<Integer> flowList2 = stats.getLastNEpoch(2);
        Assert.assertEquals(2, flowList2.size());
        Assert.assertEquals(0, (int) flowList2.get(0));
        Assert.assertEquals(1, (int) flowList2.get(1));
    }
}
