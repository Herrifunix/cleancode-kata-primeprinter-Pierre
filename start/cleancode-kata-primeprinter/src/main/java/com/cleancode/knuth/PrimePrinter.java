package com.cleancode.knuth;

class PrimePrinterHelper {
    private final int numberOfPrimes;
    private final int linesPerPage;
    private final int columns;
    private final int ordmax = 30;
    private final int[] primes;
    private final int[] multiples;
    private int pagenumber = 1;
    private int pageoffset = 1;
    private int rowoffset;
    private int column;
    private int candidate = 1;
    private int primeIndex = 1;
    private boolean possiblyPrime;
    private int ord = 2;
    private int square = 9;
    private int n;

    public PrimePrinterHelper(int numberOfPrimes, int linesPerPage, int columns) {
        this.numberOfPrimes = numberOfPrimes;
        this.linesPerPage = linesPerPage;
        this.columns = columns;
        this.primes = new int[numberOfPrimes + 1];
        this.multiples = new int[ordmax + 1];
    }

    public void generatePrimes() {
        primes[1] = 2;

        while (primeIndex < numberOfPrimes) {
            do {
                candidate += 2;
                if (candidate == square) {
                    ord++;
                    square = primes[ord] * primes[ord];
                    multiples[ord - 1] = candidate;
                }
                n = 2;
                possiblyPrime = true;
                while (n < ord && possiblyPrime) {
                    while (multiples[n] < candidate)
                        multiples[n] += primes[n] + primes[n];
                    if (multiples[n] == candidate)
                        possiblyPrime = false;
                    n++;
                }
            } while (!possiblyPrime);
            primeIndex++;
            primes[primeIndex] = candidate;
        }
    }

    public void printPrimes() {
        printPrimes(primes, numberOfPrimes);
    }

    private void printPrimes(int[] primes, int numberOfPrimes) {
        while (pageoffset <= numberOfPrimes) {
            System.out.print("The First ");
            System.out.print(numberOfPrimes);
            System.out.print(" Prime Numbers === Page ");
            System.out.print(pagenumber);
            System.out.println("\n");

            for (rowoffset = pageoffset; rowoffset <= pageoffset + linesPerPage - 1 && rowoffset <= numberOfPrimes; rowoffset++) {
                for (column = 0; column <= columns - 1; column++) {
                    if (rowoffset + column * linesPerPage <= numberOfPrimes)
                        System.out.printf("%10d", primes[rowoffset + column * linesPerPage]);
                }
                System.out.println();
            }
            System.out.println("\f");
            pagenumber++;
            pageoffset += linesPerPage * columns;
        }
    }
}

public class PrimePrinter {
    public static void main(String[] args) {
        final int numberOfPrimes = 1000;
        final int linesPerPage = 50;
        final int columns = 4;
        PrimePrinterHelper primePrinterHelper = new PrimePrinterHelper(numberOfPrimes, linesPerPage, columns);
        primePrinterHelper.generatePrimes();
        primePrinterHelper.printPrimes();
    }
}
