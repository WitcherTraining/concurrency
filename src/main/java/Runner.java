public class Runner {
    public static void main(String[] args) {

        Pusher pusher = new Pusher();
        Operator operator = new Operator();
        System.out.println("Java version is " + System.getProperty("java.version"));

        pusher.start();
        operator.start();
    }
}


