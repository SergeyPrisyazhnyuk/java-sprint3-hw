package models;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subTaskIDs;

    public Epic() {
    }

    public Epic(int id, String name, String description, String status) {
        super(id, name, description, status);
        subTaskIDs = new ArrayList<>();
    }

    public ArrayList<Integer> getSubTaskIDs() {
        return subTaskIDs;
    }

    public void setSubTaskIDs(ArrayList<Integer> subTaskIDs) {
        this.subTaskIDs = subTaskIDs;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", subTaskIDs='" + subTaskIDs + '\'' +
                '}';
    }
}
