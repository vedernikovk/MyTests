package recursion;

public class Tests {

    private int N = 100;
    private boolean[] primes = new boolean[N+1];
    private int[] array = new int[] {1, 4, 6, 9, 15, 21, 32, 50};

    public static void main(String[] args) throws Exception {

        try {
            Tests t = new Tests();
            t.primeNumbers();
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    private int factorial(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * factorial(n-1);
        }
    }

    private void delete(int i) {
        int j = 2;
        while (i * j <= N) {
            primes[i *j] = false;
            j++;
        }
    }

    private void primeNumbers() {

        for (int i=2; i <= N; i++) {
            primes[i] = true;
        }

        for (int i=2; i <= N; i++) {
            delete(i);
        }

        for (int i=2; i <= N; i++) {
            if (primes[i]) {
                System.out.println(i);
            }
        }
    }

    private int binarySearch(int n, int i, int j) {

        if (i == j) {
            if (array[i] == n) {
                return i;
            } else {
                return -1;
            }
        }

        if (j-i == 1) {
            if (array[i] == n) return i;
            if (array[j] == n) return j;
            return -1;
        }

        int center = Math.round((j - i) / 2);
        if (array[i + center] == n) {
            return center;
        }

        if (n > array[center]) {
            return binarySearch(n, i + center, j);
        } else {
            return binarySearch(n, i, j - center);
        }
    }

    private void quickSort(int lo, int hi) {

        //  lo is the lower index, hi is the upper index
        //  of the region of array a that is to be sorted
        int i=lo, j=hi, h;
        int x=array[(lo+hi)/2];

        //  partition
        do
        {
            while (array[i]<x) i++;
            while (array[j]>x) j--;
            if (i<=j)
            {
                h=array[i]; array[i]=array[j]; array[j]=h;
                i++; j--;
            }
        } while (i<=j);

        //  recursion
        if (lo<j) quickSort(lo, j);
        if (i<hi) quickSort(i, hi);
    }

    /*
    private Node findNode(Node n, int val) {
        if (n == null) return NOT_FOUND;
        if (n.val == cal) return n;

        if (val > n.val)
           return findNode(n.right, val);
        else
           return findNode(n.left, val);
    }
    */
}
