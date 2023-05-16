package models;

public class SubTask extends Task {
    private int epicId;

    public SubTask() {
    }

    public SubTask(int id, String name, String description, String status, int epicId) {
        super(id, name, description, status);
        this.epicId = epicId;
    }


    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", epicID='" + getEpicId() +'\'' +
                '}';
    }
}
