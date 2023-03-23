import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите команду для создания карты: ");
        RobotMap map = null;

        while (true) {
            String command = in.nextLine();
            if (command.startsWith("create-map")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map = new RobotMap(Integer.parseInt(arguments[0]),Integer.parseInt(arguments[1]));
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("при создании карты возникло исключение: " + e.getMessage() + ". Попробуйте еще раз");
                }
            } else {
                System.out.println("Команда не найдена. Попробуйте еще раз");
            }
        }

        System.out.println("Введите одну из доступных команд:");
        System.out.println(" create-robot\n move-robot\n change-direction\n exit");

        while (true) {
            String command = in.nextLine();

            if (command.startsWith("create-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);
            
                try {
                    RobotMap.Robot robot = map.createRobot(new Point(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])));
                    System.out.println(robot);
                } catch (PositionException e) {
                    System.out.println("При создании робота возникло исключение: " + e.getMessage() + "Попробуйте снова");
                }  

            } else if (command.startsWith("move-robot")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    map.getById(Long.parseLong(arguments[0])).move();
                } catch (PositionException e) {
                    System.out.println("При создании робота возникло исключение: " + e.getMessage() + "Попробуйте снова");
                }

            } else if (command.startsWith("change-direction")) {
                String[] split = command.split(" ");
                String[] arguments = Arrays.copyOfRange(split, 1, split.length);

                try {
                    Direction direction = Direction.ofString(args[1]);
                    map.getById(Long.parseLong(arguments[0])).changeDirection(direction);
                } catch (Exception e) {
                    System.out.println("При создании робота возникло исключение: " + e.getMessage() + "Попробуйте снова");
                }
                
            } else if (command.equals("exit")) {
                break;
            } 
        }
    }
}