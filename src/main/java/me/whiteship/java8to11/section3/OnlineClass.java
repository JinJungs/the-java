package me.whiteship.java8to11.section3;


public class OnlineClass {
    private int id;
    private String title;
    private boolean closed;

    public OnlineClass(int id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return closed;
    }
}
