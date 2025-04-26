import java.util.*;

class Bank {
    String name;
    int netAmount;
    TreeSet<String> types = new TreeSet<>();
}

public class CashFlowMinimizer {

    static int getMinIndex(Bank[] list, int numBanks) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < numBanks; i++) {
            if (list[i].netAmount == 0) continue;
            if (list[i].netAmount < min) {
                min = list[i].netAmount;
                minIndex = i;
            }
        }
        return minIndex;
    }

    static int getSimpleMaxIndex(Bank[] list, int numBanks) {
        int max = Integer.MIN_VALUE, maxIndex = -1;
        for (int i = 0; i < numBanks; i++) {
            if (list[i].netAmount == 0) continue;
            if (list[i].netAmount > max) {
                max = list[i].netAmount;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    static Pair<Integer, String> getMaxIndex(Bank[] list, int numBanks, int minIndex, Bank[] input) {
        int max = Integer.MIN_VALUE, maxIndex = -1;
        String matchingType = "";

        for (int i = 0; i < numBanks; i++) {
            if (list[i].netAmount <= 0) continue;

            Set<String> intersection = new TreeSet<>(list[minIndex].types);
            intersection.retainAll(list[i].types);

            if (!intersection.isEmpty() && list[i].netAmount > max) {
                max = list[i].netAmount;
                maxIndex = i;
                matchingType = intersection.iterator().next();
            }
        }
        return new Pair<>(maxIndex, matchingType);
    }

    static void printAnswer(List<List<Pair<Integer, String>>> ansGraph, int numBanks, Bank[] input) {
        System.out.println("\nThe transactions for minimum cash flow are as follows:\n");
        for (int i = 0; i < numBanks; i++) {
            for (int j = 0; j < numBanks; j++) {
                if (i == j) continue;

                Pair<Integer, String> ij = ansGraph.get(i).get(j);
                Pair<Integer, String> ji = ansGraph.get(j).get(i);

                if (ij.getKey() != 0 && ji.getKey() != 0) {
                    if (Objects.equals(ij.getKey(), ji.getKey())) {
                        ij = new Pair<>(0, "");
                        ji = new Pair<>(0, "");
                    } else if (ij.getKey() > ji.getKey()) {
                        int diff = ij.getKey() - ji.getKey();
                        ansGraph.get(i).set(j, new Pair<>(diff, ij.getValue()));
                        ansGraph.get(j).set(i, new Pair<>(0, ""));
                        System.out.println(input[i].name + " pays Rs " + diff + " to " + input[j].name + " via " + ij.getValue());
                    } else {
                        int diff = ji.getKey() - ij.getKey();
                        ansGraph.get(j).set(i, new Pair<>(diff, ji.getValue()));
                        ansGraph.get(i).set(j, new Pair<>(0, ""));
                        System.out.println(input[j].name + " pays Rs " + diff + " to " + input[i].name + " via " + ji.getValue());
                    }
                } else if (ij.getKey() != 0) {
                    System.out.println(input[i].name + " pays Rs " + ij.getKey() + " to " + input[j].name + " via " + ij.getValue());
                } else if (ji.getKey() != 0) {
                    System.out.println(input[j].name + " pays Rs " + ji.getKey() + " to " + input[i].name + " via " + ji.getValue());
                }

                ansGraph.get(i).set(j, new Pair<>(0, ""));
                ansGraph.get(j).set(i, new Pair<>(0, ""));
            }
        }
        System.out.println();
    }

    static void minimizeCashFlow(int numBanks, Bank[] input, Map<String, Integer> indexOf, int[][] graph) {
        Bank[] list = new Bank[numBanks];
        for (int i = 0; i < numBanks; i++) {
            list[i] = new Bank();
            list[i].name = input[i].name;
            list[i].types = new TreeSet<>(input[i].types);
            int amount = 0;

            for (int j = 0; j < numBanks; j++) {
                amount += graph[j][i]; // incoming
                amount -= graph[i][j]; // outgoing
            }
            list[i].netAmount = amount;
        }

        List<List<Pair<Integer, String>>> ansGraph = new ArrayList<>();
        for (int i = 0; i < numBanks; i++) {
            List<Pair<Integer, String>> row = new ArrayList<>();
            for (int j = 0; j < numBanks; j++) {
                row.add(new Pair<>(0, ""));
            }
            ansGraph.add(row);
        }

        int settled = 0;
        for (Bank b : list) if (b.netAmount == 0) settled++;

        while (settled != numBanks) {
            int minIndex = getMinIndex(list, numBanks);
            Pair<Integer, String> maxResult = getMaxIndex(list, numBanks, minIndex, input);
            int maxIndex = maxResult.getKey();

            if (maxIndex == -1) {
                int simpleMax = getSimpleMaxIndex(list, numBanks);
                int amount = Math.abs(list[minIndex].netAmount);

                ansGraph.get(minIndex).set(0, new Pair<>(amount, input[minIndex].types.iterator().next()));
                ansGraph.get(0).set(simpleMax, new Pair<>(amount, input[simpleMax].types.iterator().next()));

                list[simpleMax].netAmount += list[minIndex].netAmount;
                list[minIndex].netAmount = 0;

                if (list[minIndex].netAmount == 0) settled++;
                if (list[simpleMax].netAmount == 0) settled++;
            } else {
                int amt = Math.min(Math.abs(list[minIndex].netAmount), list[maxIndex].netAmount);
                ansGraph.get(minIndex).set(maxIndex, new Pair<>(amt, maxResult.getValue()));

                list[minIndex].netAmount += amt;
                list[maxIndex].netAmount -= amt;

                if (list[minIndex].netAmount == 0) settled++;
                if (list[maxIndex].netAmount == 0) settled++;
            }
        }

        printAnswer(ansGraph, numBanks, input);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t********************* Welcome to CASH FLOW MINIMIZER SYSTEM ***********************\n");
        System.out.println("This system minimizes the number of transactions among multiple banks using different modes of payment.\n");

        System.out.println("Enter the number of banks participating in the transactions:");
        int numBanks = sc.nextInt();
        System.out.println("Enter the details as stated:\n");
        System.out.println("Bank_name, number_of_payment_modes, name_of_payment_modes (should not contain comma)\n");
        Bank[] input = new Bank[numBanks];
        Map<String, Integer> indexOf = new HashMap<>();

        int maxNumTypes = 0;
        for (int i = 0; i < numBanks; i++) {
            System.out.print((i == 0 ? "World Bank : " : "Bank " + i + " : "));
            input[i] = new Bank();
            input[i].name = sc.next();
            indexOf.put(input[i].name, i);

            int typesCount = sc.nextInt();
            if (i == 0) maxNumTypes = typesCount;
            for (int j = 0; j < typesCount; j++) {
                input[i].types.add(sc.next());
            }
        }

        System.out.println("Enter number of transactions:");
        int numTransactions = sc.nextInt();

        int[][] graph = new int[numBanks][numBanks];

        System.out.println("Enter the details of each transaction:");
        for (int i = 0; i < numTransactions; i++) {
            System.out.print(i + "th transaction: ");
            String from = sc.next();
            String to = sc.next();
            int amount = sc.nextInt();
            graph[indexOf.get(from)][indexOf.get(to)] = amount;
        }

        minimizeCashFlow(numBanks, input, indexOf, graph);
        sc.close();
    }
}

// Helper class for Pair since Java doesn't have built-in like C++
class Pair<K, V> {
    private K key;
    private V value;
    public Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    public void setKey(K key) { this.key = key; }
    public void setValue(V value) { this.value = value; }
}
