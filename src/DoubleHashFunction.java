public class DoubleHashFunction {
    private static final int TABLE_SIZE = 16;
    private final Integer[] table = new Integer[TABLE_SIZE];

    private int hash1(int key) {
        return key % 16;
    }

    private int hash2(int key) {
        return 2 * (key % 4) + 1;
    }

    public void insert(int key) {
        int h1 = hash1(key);
        int h2 = hash2(key);
        int i = 0;
        System.out.println("\nInserting key: " + key);

        while (i < TABLE_SIZE) {
            int slot = (h1 + i * h2) % TABLE_SIZE;
            if (table[slot] == null) {
                table[slot] = key;
                print(key, i, slot, false);
                return;
            }
            print(key, i, slot, true);
            i++;
        }
        System.out.println("Table full, cannot insert key: " + key);
    }

    private void print(int key, int i, int slot, boolean collision) {
        if (collision)
            System.out.println("i=" + i + ": collision at slot " + slot + " (occupied by " + table[slot] + ")");
        else
            System.out.println("i=" + i + ": inserted key " + key + " at slot " + slot);

        System.out.print("Table: [");
        for (int j = 0; j < TABLE_SIZE; j++) {
            if (table[j] == null) {
                System.out.print("-");
            } else {
                System.out.print(table[j]);
            }

            // print comma only between elements, not after the last one
            if (j < TABLE_SIZE - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]\n");
    }

    public void printFinalTable() {
        System.out.print("\nFinal hash table:\n[");
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] == null) {
                System.out.print("-");
            } else {
                System.out.print(table[i]);
            }
            if (i < TABLE_SIZE - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]\n");

        System.out.println("Final hash table (slots):");
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] == null) {
                System.out.println("Slot " + i + ": -");
            } else {
                System.out.println("Slot " + i + ": " + table[i]);
            }
        }
    }

    public static void main(String[] args) {
        DoubleHashFunction dh = new DoubleHashFunction();
        int[] keys = {18, 34, 9, 50, 66, 10, 41, 57, 73};
        for (int key : keys) dh.insert(key);
        dh.printFinalTable();
    }
}