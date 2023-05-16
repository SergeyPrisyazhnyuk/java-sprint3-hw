import models.Epic;
import models.Status;
import models.SubTask;
import models.Task;
import services.TaskManager;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        /*
            TASKS
         */

        System.out.println("Let's add 4 tasks");

        Task task1 = new Task();
        task1.setName("First_Task");
        task1.setDescription("My_First_Task");
        task1.setStatus(String.valueOf(Status.NEW));

        Task task2 = new Task();
        task2.setName("Second_Task");
        task2.setDescription("My_Second_Task");
        task2.setStatus(String.valueOf(Status.IN_PROGRESS));

        Task task3 = new Task();
        task3.setName("Big_Task");
        task3.setDescription("My_Big_Task");
        task3.setStatus(String.valueOf(Status.DONE));

        Task task4 = new Task();
        task4.setName("Small_Task");
        task4.setDescription("My_Small_Task");
        task4.setStatus(String.valueOf(Status.DONE));

        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(task3);
        manager.addTask(task4);
        HashMap<Integer, Task> mtasks = manager.getAllTasks();

        System.out.println("************************************************************************");
        System.out.println("All My tasks");
        for (Integer integer : mtasks.keySet()) {
            System.out.println(mtasks.get(integer));
        }
        System.out.println("************************************************************************");

        manager.deleteAllTasks();

        System.out.println("************************************************************************");
        System.out.println("All My tasks after deleting all:");
        for (Integer integer : mtasks.keySet()) {
            System.out.println(mtasks.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's add another tasks");

        Task task5 = new Task();
        task5.setName("Red_Task");
        task5.setDescription("My_Red_Task");
        task5.setStatus(String.valueOf(Status.NEW));

        Task task6 = new Task();
        task6.setName("Yellow_Task");
        task6.setDescription("My_Yellow_Task");
        task6.setStatus(String.valueOf(Status.IN_PROGRESS));

        Task task7 = new Task();
        task7.setName("Green_Task");
        task7.setDescription("My_Green_Task");
        task7.setStatus(String.valueOf(Status.DONE));

        Task task8 = new Task();
        task8.setName("White_Task");
        task8.setDescription("My_White_Task");
        task8.setStatus(String.valueOf(Status.DONE));

        manager.addTask(task5);
        manager.addTask(task6);
        manager.addTask(task7);
        manager.addTask(task8);
        HashMap<Integer, Task> mtasks2 = manager.getAllTasks();

        System.out.println("************************************************************************");
        System.out.println("All My tasks for this moment");
        for (Integer integer : mtasks2.keySet()) {
            System.out.println(mtasks2.get(integer));
        }
        System.out.println("************************************************************************");
        System.out.println("Let's take task by some id - 8");
        System.out.println(manager.getTaskById(8));
        System.out.println("************************************************************************");
        System.out.println("Now delete 6 task and update to Black 7 task");

        manager.deleteTask(6);

        task7.setName("Black_task");
        manager.updateTask(task7);

        System.out.println("************************************************************************");
        System.out.println("Finally:");
        for (Integer integer : mtasks2.keySet()) {
            System.out.println(mtasks2.get(integer));
        }
        System.out.println("************************************************************************");


         /*
            Epics
         */

        System.out.println("Let's add 4 epics");

        Epic epic1 = new Epic();
        epic1.setName("First_Epic");
        epic1.setDescription("My_First_Epic");

        Epic epic2 = new Epic();
        epic2.setName("Second_Epic");
        epic2.setDescription("My_Second_Epic");

        Epic epic3 = new Epic();
        epic3.setName("Third_Epic");
        epic3.setDescription("My_Third_Epic");


        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.addEpic(epic3);

        HashMap<Integer, Epic> mepic = manager.getAllEpics();

        System.out.println("************************************************************************");
        System.out.println("All My epics");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's take epic by some id - 10");
        System.out.println(manager.getEpicById(10));
        System.out.println("************************************************************************");
        System.out.println("Now delete 9 epic and update to Black 11 epic");

        manager.deleteEpic(9);

        epic3.setName("Black_epic");
        manager.updateEpic(epic3);

        System.out.println("************************************************************************");
        System.out.println("Finally:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

         /*
            SubTasks
         */

        SubTask subTask1 = new SubTask();
        subTask1.setName("1_For_11");
        subTask1.setDescription("1_Sub_for_epic_11");
        subTask1.setEpicId(11);

        SubTask subTask2 = new SubTask();
        subTask2.setName("2_For_11");
        subTask2.setDescription("2_Sub_for_epic_11");
        subTask2.setEpicId(11);

        SubTask subTask3 = new SubTask();
        subTask3.setName("1_For_10");
        subTask3.setDescription("1_Sub_for_epic_10");
        subTask3.setEpicId(10);

        manager.addSubTask(subTask1);
        manager.addSubTask(subTask2);
        manager.addSubTask(subTask3);

        HashMap<Integer, SubTask> msub = manager.getAllSubTasks();
        System.out.println("************************************************************************");
        System.out.println("All My subs:");
        for (Integer integer : msub.keySet()) {
            System.out.println(msub.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("************************************************************************");
        System.out.println("All my epics:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's get SubTask byId 13");
        System.out.println(manager.getSubTaskById(13));
        System.out.println("************************************************************************");

        System.out.println("Let's check status calculation by updating subTask 14 to DONE");
        System.out.println("And IN_PROGRESS for Epic 11");


        subTask3.setName("1_For_10_after_updating");
        subTask3.setStatus(String.valueOf(Status.DONE));
        manager.updateSubTasks(subTask3);

        subTask2.setStatus(String.valueOf(Status.IN_PROGRESS));
        manager.updateSubTasks(subTask2);

        System.out.println("************************************************************************");
        System.out.println("All My subs:");
        for (Integer integer : msub.keySet()) {
            System.out.println(msub.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("************************************************************************");
        System.out.println("All my epics:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's delete 14 subTask and see status for Epic 10");
        manager.deleteSubTask(14);

        System.out.println("************************************************************************");
        System.out.println("All My subs:");
        for (Integer integer : msub.keySet()) {
            System.out.println(msub.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("************************************************************************");
        System.out.println("All my epics:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's delete all subTasks");
        manager.deleteAllSubTasks();

        System.out.println("************************************************************************");
        System.out.println("All My subs:");
        for (Integer integer : msub.keySet()) {
            System.out.println(msub.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("************************************************************************");
        System.out.println("All my epics:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");

        System.out.println("Let's delete all epics");

        manager.deleteAllEpics();
        System.out.println("************************************************************************");
        System.out.println("All my epics:");
        for (Integer integer : mepic.keySet()) {
            System.out.println(mepic.get(integer));
        }
        System.out.println("************************************************************************");


    }
}
