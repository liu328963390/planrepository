package com.plan.newyear.util.linked;

import java.util.Scanner;

public class Hashs {
    public static void main(String[] args) {
        //创建一个has表
        Has has = new Has(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出系统");
            System.out.println("find:查找雇员");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Employee employee = new Employee(id, name);
                    has.add(employee);
                    break;
                case "list":
                    has.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    has.findEmployee(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//哈希表（散列）:管理多条链表
class Has{
    private EmployeeLinkedList[] listArray;
    private int size;



    public Has(int size){
        this.size = size;
        //初始化listArray
        listArray = new EmployeeLinkedList[size];//初始化EmployeeLinkedList
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            listArray[i] = new EmployeeLinkedList();
        }
    }

    //添加雇员
    public void add(Employee employee){
        //根据员工的id，得到该员工应当添加到那条链表
        int employeeListNo = hasFun(employee.id);
        //将employee添加到对应的链表中
        listArray[employeeListNo].add(employee);
    }

    //遍历所有链表:遍历has表
    public void list(){
        for (int i = 0; i < size; i++) {
            listArray[i].list(i);
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hasFun(int id){
        return id%size;
    }

    //根据输入的id查找雇员
    public void findEmployee(int id){
        //使用散列函数，确定到那条链表中查找
        int i = hasFun(id);
        Employee employee = listArray[i].findEmployee(id);
        if (employee != null){
            System.out.printf("在第%d条链表中找到雇员id=%d\n",i,id);
        }else {
            System.out.println("在哈希表中，没有找到");
        }
    }

}


//表示一个雇员的类
class Employee{
    public int id;
    public String name;
    public Employee next;//next默认为null

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmployeeLinkedList类，表示链表
class EmployeeLinkedList{
    //头指针，指向第一个Employee，因此我们这个链表的head是直接指向第一个Employee
    private Employee head;//默认为null

    //添加雇员到链表
    //说明：
    //1。假定当添加雇员时id是自增长的，即id的分配总是从小到大,因此，将该雇员直接加入到本链表的最后即可
    public void add(Employee employee){
        //如果是添加这条链表的第一个雇员
        if (head == null){
            head = employee;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Employee current = head;
        while (true){
            if (current.next == null){//说明到链表最后
                break;
            }
            current = current.next;
        }
        //退出时直接将employee加入到链表
        current.next = employee;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if (head == null){//说明链表为空
            System.out.println("第"+no+"链表为空");
            return;
        }
        System.out.println("第"+no+"链表的信息为");
        Employee current = head;
        while (true){
            System.out.printf("=>id=%d name=%s",current.id,current.name);
            if (current.next == null){//说明current已经是最后节点
                break;
            }
            current = current.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Employee,没有找到，就返回为null
    public Employee findEmployee(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Employee current = head;
        while (true){
            if (current.id == id){//找到
                break;//这时current就指向要查找的雇员
            }
            //退出条件
            if (current.next == null){//说明遍历当前链表没有找到该雇员
                current = null;
                break;
            }
            current = current.next;
        }
        return current;
    }
}
