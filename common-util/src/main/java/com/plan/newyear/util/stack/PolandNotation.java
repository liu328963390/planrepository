package com.plan.newyear.util.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式（即后缀表达式）
 */
public class PolandNotation{
    public static void main(String[] args) {

        /**
         * 中缀表达式转后缀表达式：
         * 1。初始化两个栈，运算符栈s1和储存中间结果的栈s2
         * 2。从左至右扫描中缀表达式
         * 3。遇到操作数时，将其压入s2
         * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
         *  a.如果s1为空，或栈顶运算符为左括号“（”，则直接将此运算符入栈
         *  b.否则，若优先级比栈顶运算符的高，也将运算符压入s1;
         *  c.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.a与s1中新的栈顶运算符相比较
         *  5.遇到括号时：
         *  a.如果是左括号“（”，则直接压入s1栈
         *  b.如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
         *  6。重复步骤2至5，直到表达式的最右边
         *  7。将s1中剩余的运算符依次弹出并压入s2中
         *  8。依次弹出 s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
         */
        //说明：1+（（2+3）*5）-6=>1 2 3 + 5 * + 6 -
        //因为直接对string进行操作，不方便，因此，先将1+（（2+3）*5）-6=>中缀的表达式对应的List
        //即“1+（（2+3）*5）-6”=>Arraylist[1 2 3 + 5 * + 6 -]
        //将得到的中缀表达式对应的list=>后缀表达式对应的list
        //将[1, +, （, （, 2, +, 3, ）, *, 5, ）, -, 6]==>1 2 3 + 5 * + 6 -
        String expression = "1+((20+3)*60)/12-6";

        List<String> list = toInfixExpression(expression);
        System.out.println(list);//[1, +, （, （, 2, +, 3, ）, *, 5, ）, -, 6]
        List<String> strings = parseSuffixExpression(list);
        System.out.println("后缀表达式对应的strings"+strings);
        System.out.println("-------------------------------");
        int calculate = calculate(strings);
        System.out.println(calculate);

        //先定义一个后缀表达式
        //（3+4）*5-6=> 3 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 40 + 5 * 6 -";

        /*
        思路：
        1.先将suffixExpression=>放到ArrayList中
        2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
         */
        List<String> listString = getListString(suffixExpression);
        System.out.println("listString="+listString);

        int result = calculate(listString);
        System.out.println("计算的结果是="+result);
    }

    //将得到的中缀表达式对应的list=>后缀表达式对应的list
    public static List<String> parseSuffixExpression(List<String> list){
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //说明：因为number这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String>直接使用List<String> number
//        Stack<String> number = new Stack<>();//储存中间结果的数据栈
        List<String> s2 = new ArrayList<>();

        //遍历list
        for (String item : list) {
            //如果是一个数，加入number
            if (item.matches("\\d+")) {
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将“（”弹出s1栈，消除小括号
            }else {
                //考虑优先级
                //当item的优先级小于等于栈顶运算符的优先级，将s1栈顶的运算符弹出并加入到s2中，再次转到4.a与s1中新的栈顶运算符相比较
                //缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //把当前item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并压入s2中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到List,因此按顺序输出就是对应的后缀表达式对应的list
    }

    //方法：将中缀表达式转成对应的list
    public static List<String> toInfixExpression(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//做对多位数的拼接工作
        char c;//每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我们就需要加入到list里
            if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
                list.add(""+c);
                i++;//i需要后移
            }else {
                //如果是一个数，需要考虑多位数
                str = "";//先将str置成“”;'0'[48]  -> '9'[57]
                while (i <s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57 ){
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }
        }while (i<s.length());
        return list;
    }

    //将逆波兰表达式依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        //创建一个list
        List<String> list = new ArrayList<>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    /*
    完成对逆波兰表达式的运算：
    1.从左至右扫描，将3和4压堆栈
    2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈，将5入栈
      接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈；将6入栈；
      最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> lis){
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历lis
        for (String item:lis) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")){
                    result = num1 + num2;
                }else if (item.equals("-")){
                    result = num1 - num2;
                }else if (item.equals("*")){
                    result = num1 * num2;
                }else if (item.equals("/")){
                    result = num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把result入栈
                stack.push(""+result);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//缺少一个比较优先级高低的方法
//编写一个类Operation可以返回一个运算对应的优先级
class Operation{
    private static  int ADD = 1;
    private static  int SUB = 1;
    private static  int MUL = 2;
    private static int DIV = 2;
    //写一个方法，返回对应的优先级数字
    public static int getValue(String  operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
