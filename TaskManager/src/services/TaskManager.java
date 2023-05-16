package services;

import models.Epic;
import models.Status;
import models.SubTask;
import models.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int id = 0;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public int setIds() {
        id = id + 1;
        return id;
    }

    /*****************************************************************
     * Task
     ******************************************************************/
    public void addTask(Task task) {
        int id = setIds();
        task.setId(id);
        tasks.put(id, task);
    }

    public HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    /*****************************************************************
     * Epic
     ******************************************************************/


    public void calculateEpicStatus(Epic epic) {
        if (epic.getSubTaskIDs() == null || epic.getSubTaskIDs().size() == 0) {
            epic.setStatus(String.valueOf(Status.NEW));
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (Integer subTaskID : epic.getSubTaskIDs()) {
            Status status = Status.valueOf(subTasks.get(subTaskID).getStatus());

            if (status != Status.NEW) {
                allNew = false;
            }

            if (status != Status.DONE) {
                allDone = false;
            }

        }

        if (allDone) {
            epic.setStatus(String.valueOf(Status.DONE));
        } else if (allNew) {
            epic.setStatus(String.valueOf(Status.NEW));
        } else {
            epic.setStatus(String.valueOf(Status.IN_PROGRESS));
        }

    }


    public void addEpic(Epic epic) {
        int id = setIds();
        epic.setId(id);
        epic.setStatus(String.valueOf(Status.NEW));
        epics.put(id, epic);
    }

    public HashMap<Integer, Epic> getAllEpics() {
        return epics;
    }

    public Task getEpicById(int id) {
        return epics.get(id);
    }

    public void updateEpic(Epic epic) {
        epic.setSubTaskIDs(epics.get(epic.getId()).getSubTaskIDs());
        epics.put(epic.getId(), epic);
        calculateEpicStatus(epic);
    }


    public void deleteEpic(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            if (epic.getSubTaskIDs() == null || epic.getSubTaskIDs().size() == 0) {
                epics.remove(id);
                return;
            }
            for (Integer subTaskID : epic.getSubTaskIDs()) {
                subTasks.remove(subTaskID);
            }
            epics.remove(id);
        }
    }

    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();

    }


    /*****************************************************************
     * SubTasks
     ******************************************************************/

    public void addSubTask(SubTask subTask) {
        int id = setIds();
        subTask.setId(id);
        subTask.setStatus(String.valueOf(Status.NEW));
        subTasks.put(id, subTask);

        Epic epic = epics.get(subTask.getEpicId());

        if (epic.getSubTaskIDs() == null || epic.getSubTaskIDs().size() == 0) {
            ArrayList<Integer> subs = new ArrayList<>();
            subs.add(id);
            epic.setSubTaskIDs(subs);
            calculateEpicStatus(epic);
        } else {
            epic.getSubTaskIDs().add(id);
            calculateEpicStatus(epic);
        }

    }

    public HashMap<Integer, SubTask> getAllSubTasks() {
        return subTasks;
    }

    public Task getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void updateSubTasks(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        calculateEpicStatus(epic);
    }


    public void deleteSubTask(int id) {
        if (subTasks.containsKey(id)) {
            Epic epic = epics.get(subTasks.get(id).getEpicId());
            epic.getSubTaskIDs().remove((Integer) id);
            subTasks.remove(id);
            calculateEpicStatus(epic);
        }
    }

    public void deleteAllSubTasks() {
        for (SubTask value : subTasks.values()) {
            Epic epic = epics.get(value.getEpicId());
            if (epic.getSubTaskIDs() == null || epic.getSubTaskIDs().size() == 0) {
                break;
            } else {
                epic.setSubTaskIDs(new ArrayList<>());
            }
            calculateEpicStatus(epic);
        }
        subTasks.clear();
    }
}
