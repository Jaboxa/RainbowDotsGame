package jessi.rainbowdots2;


public class User {

    private String name;
    private long score;
    private long databaseId;

    public User() {
    }

    public User(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }


    public void setDatabaseId(long databaseId) {
        this.databaseId = databaseId;
    }

    @Override
    public String toString() {
        return  name + ' '+ score;
    }
}
