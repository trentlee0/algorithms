package designpattern.prototype.shallow;

import java.util.Date;

public class Video implements Cloneable {
    private String name;
    private Date createTime;

    public Video() {
    }

    public Video(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Client {
    public static void main(String[] args) {
        Date date = new Date();
        Video v1 = new Video("Java", date);
        System.out.println(v1);
        System.out.println("v1 => time: " + v1.getCreateTime());
        try {
            Video v2 = (Video) v1.clone();
            System.out.println(v2);
            date.setTime(1000);
            System.out.println("v1 => time: " + v1.getCreateTime());
            System.out.println("v2 => time: " + v2.getCreateTime());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
