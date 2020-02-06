package com.plan.newyear.util.stack;

/**
 * 栈实现计算式
 * 1.通过一个index值（对字符串进行扫描的索引），来遍历我们的表达式
 * 2.如果我们发现是一个数字，就直接入数栈
 * 3.如果发现扫描到是一个符号，分如下情况解决
 *   a.如果发现当前的符号栈为空，就直接入栈
 *   b.如果发现符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到结果入数栈，然后把当前的操作符入符号栈；如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
 *  4.当表达式扫描完毕就顺序的从数栈和符号栈pop出相应的数和符号，并运行
 *  5.最后在数栈只有一个数字，就是表达式的结果
 */
public class CalculaderStack {
    public static void main(String[] args) {

        String  expreesion = "12123+20*6-2";
        //创建两个栈，数栈，一个符号栈
        ArrayStacks number = new ArrayStacks(10);
        ArrayStacks operation = new ArrayStacks(20);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        String keepNum = "";//用于拼接多位数
        char ch= ' ';//将每次扫描得到char保存到ch中
        //开始while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expreesion.substring(index,index+1).charAt(0);
            //判断ch是什么，做相应的处理
            if (operation.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operation.isEmpty()){
                    // b.如果发现符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，将得到结果入数栈，然后把当前的操作符入符号栈；
                    if (operation.prioity(ch)<=operation.prioity(operation.peek())){
                        //就重数栈取出两个数
                        num1 = number.ppop();
                        num2 = number.ppop();
                        oper = operation.ppop();
                        result = number.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        number.ppush(result);
                        //当前的操作符入符号栈
                        operation.ppush(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operation.ppush(ch);
                    }
                }else {
                    //如果为空，直接入栈
                    operation.ppush(ch);
                }
            }else {//如果是数，直接入数栈
//                number.ppush(ch-48);
                //思路：当处理多位数是，不能发现是一个数就立即入栈，可能是多位数
                //在处理数时，需要expression的表达式index后再看一位，如果是数就进行扫描，如果是符号就入栈
                //因些我们需要定义一个变量字符串，用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expreesion.length()-1){
                    number.ppush(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，若是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if (operation.isOper(expreesion.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈keepNum = "1"或有“123”
                        number.ppush(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
            }
            //让index+1,并判断是否扫描到expression最后
            index++;
            if (index>=expreesion.length()){
                break;
            }
        }
        //当表达式扫描完毕就顺序的从数栈和符号栈pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字[结果]
            if (operation.isEmpty()){
                break;
            }
            num1 = number.ppop();
            num2 = number.ppop();
            oper = operation.ppop();
            result = number.cal(num1,num2,oper);
            number.ppush(result);//入栈
        }
        //将数栈的结果输出
        int resut=number.ppop();
        System.out.printf("表达式%s=%d",expreesion,resut);
    }
}

//先创建一个栈
class ArrayStacks{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStacks(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //返回当前栈顶的值，但不是出栈
    public int peek(){
        return stack[top];
    }

    //入栈-push
    public void ppush(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] =value;
    }

    //出栈 -ppop,将栈所有数据返回
    public int ppop(){
        //先判断栈是否为空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没有数据。。。");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈（显示栈的情况）;遍历时需要从栈顶开始写数据
    public void llist(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数字表示，数字越大，优先级越高
    public int prioity(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char value){
        return value == '+' || value == '-' || value == '*' || value=='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int result = 0;//用于存放计算的结果
        switch (oper){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result=num2/num1;
                break;
            default:
                break;
        }
        return result;
    }
}