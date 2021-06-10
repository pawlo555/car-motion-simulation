package Utilities;

import java.util.LinkedList;
import java.util.List;

public class ExitsStats {
    private final LinkedList<Integer> carsFlow = new LinkedList<>();

    /**
     * Increment last element in carsFlow - which is associated with the cars that pass exit at current epoch.
     */
    public void carOnExit() {
        Integer currentFlow = carsFlow.removeLast();
        carsFlow.addLast(currentFlow+1);
    }

    /**
     * When next epoch is generated creates a new element to contain cars that will flow in next epoch.
     */
    public void nextEpoch() {
        carsFlow.addLast(0);
    }

    /**
     * Get us a list of cars exited simulation in N epoch.
     * First element of list is the earliest epoch.
     * Last element is the current epoch.
     * If there are no enough epoch it returns all epoch data.
     * @param N - number of epoch to get flow from
     * @return List with info about cars flow
     */
    public List<Integer> getLastNEpoch(int N) {
        int last = carsFlow.size();
        if (last > N) {
            return carsFlow.subList(last-N, last);
        }
        else return carsFlow;
    }

    public int getLastEpochFlow() {
        return carsFlow.getLast();
    }
}
