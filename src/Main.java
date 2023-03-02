import com.skyPro.courseWork.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2023, 03, 2);

        TaskService taskService = new TaskService();
        try {
            taskService.add(new OneTimeTask("title","description",Type.PERSONAL,LocalDateTime.of(2023,03,02, 16,31)));
            System.out.println(taskService.getAllByDate(localDate));
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getArgument());
        }

        System.out.println("Kepp Working !");

    }
}