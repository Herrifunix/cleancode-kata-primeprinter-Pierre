package com.cleancode.knuth;

class PrimePrinterHelper {
    private final int linesPerPage;
    private final int columns;
    private final int ordmax = 30;
    private final int[] primes;
    private final int[] multiples;
    private int candidate = 1;
    private int primeIndex = 1;
    private boolean possiblyPrime;
    private int ord = 2;
    private int square = 9;
    private int n;

    public PrimePrinterHelper(int numberOfNumbers, int linesPerPage, int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
        this.primes = new int[numberOfNumbers + 1];
        this.multiples = new int[ordmax + 1];
    }

    public int[] generatePrimes() {
        primes[1] = 2;

        while (primeIndex < primes.length - 1) {
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
        return primes;
    }
}

class NumberPrinter {
    private final int linesPerPage;
    private final int columns;

    public NumberPrinter(int linesPerPage, int columns) {
        this.linesPerPage = linesPerPage;
        this.columns = columns;
    }

    public void printNumbers(int[] numbers, int numberOfNumbers) {
        int pagenumber = 1;
        int pageoffset = 1;
        while (pageoffset <= numberOfNumbers) {
            System.out.print("The First ");
            System.out.print(numberOfNumbers);
            System.out.print(" Numbers === Page ");
            System.out.print(pagenumber);
            System.out.println("\n");

            for (int rowoffset = pageoffset; rowoffset <= pageoffset + linesPerPage - 1 && rowoffset <= numberOfNumbers; rowoffset++) {
                for (int column = 0; column <= columns - 1; column++) {
                    if (rowoffset + column * linesPerPage <= numberOfNumbers)
                        System.out.printf("%10d", numbers[rowoffset + column * linesPerPage]);
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
    private static final int numberOfPrimes = 1000;

    public static void main(String[] args) {
        final int linesPerPage = 50;
        final int columns = 4;
        PrimePrinterHelper primePrinterHelper = new PrimePrinterHelper(numberOfPrimes, linesPerPage, columns);
        int[] primes = primePrinterHelper.generatePrimes();
        NumberPrinter numberPrinter = new NumberPrinter(linesPerPage, columns);
        numberPrinter.printNumbers(primes, numberOfPrimes);
    }
}