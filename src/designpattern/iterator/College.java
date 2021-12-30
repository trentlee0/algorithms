package designpattern.iterator;

import java.util.Iterator;

public class College implements Iterable<Department> {
    private Department[] departments;
    private int size;

    public College() {
        this.departments = new Department[8];
        this.size = 0;
    }

    public void add(Department department) {
        if (size < departments.length) {
            departments[size] = department;
        } else {
            Department[] newDepartments = new Department[size * 2];
            int i = 0;
            while (i < size) newDepartments[i] = departments[i++];
            newDepartments[i] = department;
            this.departments = newDepartments;
        }
        size++;
    }

    public Department remove(int index) {
        if (index > size) return null;
        Department department = departments[index];
        departments[index] = null;
        size--;
        return department;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Department> iterator() {
        return new CollegeIterator();
    }

    class CollegeIterator implements Iterator<Department> {
        private int index;

        public CollegeIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Department next() {
            if (hasNext()) return departments[index++];
            return null;
        }
    }
}

class Department {
    private String name;
    private String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

class Client {
    public static void main(String[] args) {
        College college = new College();
        college.add(new Department("软件工程", "软件工程"));
        college.add(new Department("网络工程", "网络工程"));
        college.add(new Department("大数据", "大数据"));

        Iterator<Department> iterator = college.iterator();
        while (iterator.hasNext()) {
            Department next = iterator.next();
            System.out.println(next.getName() + " ===> " + next.getDesc());
        }
    }
}