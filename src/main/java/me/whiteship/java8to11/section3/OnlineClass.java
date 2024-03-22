package me.whiteship.java8to11.section3;


import me.whiteship.java8to11.section4.Progress;

import java.util.Optional;

public class OnlineClass {
    private int id;
    private String title;
    private boolean closed;

    private Progress progress;

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

    public Optional<Progress> getProgress() {
        /* 진짜로 필요할 때 에러를 써야지, 로직을 처리할 때 에러를 뱉는건 좋은 습관은 아니다. */
//        if(progress == null) {
//            throw new IllegalStateException();
//        }
//        return progress;

        /* 사용하지 말기 - Optional of 는 NPE 발생 가능 */
        // return Optional.of(progress);

        /* Optional ofNullable 사용하기 */
        return Optional.ofNullable(progress);

        /* 비어있는 값을 리턴하고 싶을 때는 null 대신 Optional empty 사용하기 */
        // return Optional.empty();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setProgress(Progress progress) {
        // Setter는 Optional을 쓰지마라
        // 왜? Optinal로 받아도 넘겨줄 때 null을 넘겨주면 null 체크를 또 해야한다
        this.progress = progress;
    }
}
