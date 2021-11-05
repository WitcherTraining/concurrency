package task2;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        RandomWriter randomWriter = new RandomWriter();
        SumPrinter sumPrinter = new SumPrinter();
        SquareRootPrinter squareRootPrinter = new SquareRootPrinter();

        randomWriter.start();
        sumPrinter.start();
        squareRootPrinter.start();
    }
}
