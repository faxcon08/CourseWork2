import com.skyPro.courseWork.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static TaskService taskService=new TaskService();
    private static boolean simpleCheckString(String str) {
        if(str==null || str.isBlank())
            return false;
        str=str.trim().replaceAll("\\s+"," ");
        return true;
    }
    private static void printmenu() {
        System.out.println("1.Добавить задачу. 2. Удалить задачу 3.Получить задачу на указанный день. 4.Вывести все задачи 0. ВЫХОД");
    }

    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите название задачи");
        boolean isContinue = true;
        String title = scanner.next();
        System.out.println("Введите описание задачи");
        String description = scanner.next();

        String dateString;
        LocalDateTime localDateTime=LocalDateTime.now();

        ///////////////////////////////////////////////// блок ввода даты ////////////////////
        LocalDate localDate = getLocalDateByScanner(scanner);
        isContinue=true;
        ////////////////////////////////////////////////////// блок ввода времни ///////////////////////////
        do {
            System.out.println("Введите время в формате: hh.mm");
            dateString=scanner.next();
            dateString = dateString.trim().replaceAll("\\s+", "");
            if (!dateString.isBlank() && dateString.length() >= 3 && dateString.length() <= 5) {
                String parts[] = dateString.split("[.:-]");
                if (parts.length != 2) {
                    System.out.println("Неверный ввод времени");
                    continue;
                }
                if (parts[0].matches("([0-1][0-9])|([2][0-3])") && parts[1].matches("([0-5][0-9])")) {
                    localDateTime = LocalDateTime.of(localDate, LocalTime.of(Integer.valueOf(parts[0]), Integer.valueOf(parts[1])));
                    isContinue=false;
                    break;
                }else {
                    System.out.println("Неверный ввод времени");
                    continue;
                }
            }else {
                System.out.println("Неверный ввод времени");
                continue;
            }
        }while (isContinue);
        isContinue=true;
        //////////////////////////////////////////////// блок ввода типа заметки /////////////////////
        System.out.println("0-Личная заметка, 1-Рабочая заметка");
        Type type=Type.PERSONAL;
        System.out.println("Укажите тим рабочей заметки:");
        do {
            if (scanner.hasNextInt()) {
                int intType=scanner.nextInt();
                switch (intType) {
                    case 0:
                        type=Type.PERSONAL;
                        isContinue=false;
                        break;
                    case 1:
                        type= Type.WORK;
                        isContinue=false;
                        break;
                    default:
                        System.out.println("Error: ввдите команду от 0-1");
                        continue;
                }
            } else {
                System.out.println("Еще раз укажите номер заметки");
                continue;
            }
        } while (isContinue);
        isContinue=true;
        //////////////////////////////////////////////// блок ввода повторяемости ////////////////////
        System.out.println("0-однократно, 1-ежедневно, 2-еженедельно, 3-ежемесячно, 4- ежегодно");
        System.out.println("Введите признак повторяемости:");
        do {
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {

                    case 0:
                        taskService.add(new OneTimeTask(title,description,type,localDateTime));
                        isContinue=false;
                        break;
                    case 1:
                        taskService.add(new DailyTask(title,description,type,localDateTime));
                        isContinue=false;
                        break;
                    case 2:
                        taskService.add(new WeeklyTask(title,description,type,localDateTime));
                        isContinue=false;
                        break;
                    case 3:
                        taskService.add(new MonthlyTask(title,description,type,localDateTime));
                        isContinue=false;
                        break;
                    case 4:
                        taskService.add(new YearlyTask(title,description,type,localDateTime));
                        isContinue=false;
                        break;

                    default:
                        System.out.println("Ошибка: введите номер команды от 0-4");
                        break;
                }
            }else {
                scanner.next();
                System.out.println("Введите признак повторяемости ЕЩЕ раз");
            }
        }while (isContinue);

        System.out.println("Задача была добавлена");

    } //inputTask

    public static void removeTask(Scanner scanner) {
        System.out.println("Введите ID задачи для ее удаления:");
        Boolean isContinue=true;
        do{
            if (scanner.hasNextInt()) {
                int taskId=scanner.nextInt();
                try {
                    Task task=taskService.remove(taskId);
                    System.out.println("Задача успешно удалена: "+task);
                    isContinue=false;
                    break;
                } catch (TaskNotFoundException e) {
                    System.out.println("Задачи с таким ID не обнаружено");
                    break;
                }
            } else {
                System.out.println("Введите ID(число) для удаления задачи");
                scanner.hasNext();
                continue;
            }
        }while (isContinue);
    }

    public static void printAllByDate(Scanner scanner) {
        LocalDate localDate = getLocalDateByScanner(scanner);
        taskService.printAllTasksByDate(localDate);
    }
    public static void main(String[] args) {


        try (Scanner scanner = new Scanner(System.in)) {
            boolean isContinue = true;
            while (isContinue) {
                printmenu();
                System.out.println("Выберете пункт меню:");
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1: // Добавить
                            try {
                                inputTask(scanner);
                            } catch (IncorrectArgumentException e) {
                                System.out.println(e);
                            }
                            break;
                        case 2: // Удалить
                            removeTask(scanner);
                            break ;
                        case 3: // Получить задачи на указанный день
                            printAllByDate(scanner);
                            break ;
                        case 4: // Выести все задачи
                            taskService.printAllTasks();
                            break;
                        case 0:
                            isContinue=false;
                            break ;
                    }// switch
                } else {
                    scanner.nextInt();
                    System.out.println("Выберете пунтк меню из СПИСКА!");
                }
            }//while
        }//try

    }// main

    ///////////////////////////////// Utility //////////////////////////////////////////
    static private LocalDate getLocalDateByScanner(Scanner scanner) {
        boolean isContinue=true;
        String dateString;
        LocalDate localDate=LocalDate.now();

        Integer day,month,year;
        ///////////////////////////////////////////////// блок ввода даты ////////////////////
        do{
            System.out.println("Введите дату в формате dd.MM.yyyy:");
            dateString = scanner.next();
            dateString = dateString.trim().replaceAll("\\s+", "");
            if (dateString.length() >= 5 && dateString.length()<=10) {
                String parts[] = dateString.split("[.:-]");
                if (parts.length != 3) {
                    System.out.println("Неверный ввод данных");
                    continue;
                }
                if (parts[0].matches("([0]?[1-9])|([12][0-9])|([3][01])") && parts[1].matches("([0]?[1-9])|([1][12])") && parts[2].matches("[0-9]?[0-9]?[0-9]?[0-9]")) {
                    day = Integer.valueOf(parts[0]);
                    month = Integer.valueOf(parts[1]);
                    year = Integer.valueOf(parts[2]);

                    localDate = LocalDate.of(year, month, 1);
                    int dayMax=localDate.getMonth().length(localDate.isLeapYear());
                    if (dayMax < day) {
                        System.out.println("Введенное количество дней больше, чем есть в данном месяце");
                        continue;
                    }
                    localDate=LocalDate.of(year,month,day);
                    isContinue=false;
                    break;
                }else {
                    System.out.println("Некорретный ввод даты");
                    continue;
                }

            } else {
                System.out.println("Некорретный ввод даты");
                continue;
            }
        }while (isContinue);

        return localDate;
    }
}// Main