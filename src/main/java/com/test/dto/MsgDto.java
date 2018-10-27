package com.test.dto;

/**
 * Create by Guolianxing on 2018/10/27.
 */
public class MsgDto {
    private long id;
    private String msg;
    private String author;

    public MsgDto() {
    }

    public MsgDto(long id, String msg, String author) {
        this.id = id;
        this.msg = msg;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "MsgDto{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
