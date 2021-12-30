package designpattern.responsibility;

public abstract class Approver {
    protected Approver approver;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest request);
}

class PurchaseRequest {
    private int type = 0;
    private float price = 0.0f;
    private int id = 0;

    public PurchaseRequest(int type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}

class DepartmentApprover extends Approver {
    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() <= 5000) {
            System.out.println("请求编号 id=" + request.getId() + " 被" + this.name + "处理");
        } else {
            approver.processRequest(request);
        }
    }
}

class CollegeApprover extends Approver {
    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() > 5000 && request.getPrice() <= 10000) {
            System.out.println("请求编号 id=" + request.getId() + " 被" + this.name + "处理");
        } else {
            approver.processRequest(request);
        }
    }
}

class SchoolMasterApprover extends Approver {
    public SchoolMasterApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getPrice() > 10000 && request.getPrice() <= 30000) {
            System.out.println("请求编号 id=" + request.getId() + " 被" + this.name + "处理");
        } else {
            approver.processRequest(request);
        }
    }
}

class Client {
    public static void main(String[] args) {
        PurchaseRequest request = new PurchaseRequest(1, 25000, 1);
        DepartmentApprover department = new DepartmentApprover("张主任");
        CollegeApprover college = new CollegeApprover("李院长");
        SchoolMasterApprover schoolMaster = new SchoolMasterApprover("王校长");
        department.setApprover(college);
        college.setApprover(schoolMaster);

        department.processRequest(request);
    }
}