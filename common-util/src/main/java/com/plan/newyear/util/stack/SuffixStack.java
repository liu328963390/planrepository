package com.plan.newyear.util.stack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

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
public class SuffixStack {
    /*
    匹配+ - * / （） 运算符
     */
    static final String SYMBOL = "\\+|-|\\*|/|\\(|\\)";
    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    /*
    加减 + -
     */
    static final int LEVEL_01 = 1;

    /*
    乘除 * /
     */
    static  final int LEVEL_02 = 2;
    /*
    括号
     */
    static final int LEVEL_HIGH = Integer.MAX_VALUE;

    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<>());

    /*
    去除所有空白符
     */
    public static String replaceAllBlank(String s){
        //\\s+匹配任何空白字符，包括空格、制表符、换页符等等
        return s.replaceAll("\\s+","");
    }

    /**
     * 判断是不是数字int double long float
     * @param s
     * @return
     */
    public static boolean isNumber(String s){
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(s).matches();
    }

    /**
     * 判断不是运算符
     * @param s
     * @return
     */
    public static boolean isSymbol(String s){
        return s.matches(SYMBOL);
    }

    /**
     * 匹配等级
     * @param s
     * @return
     */
    public static int calcLeve(String s){
        if ("+".equals(s) || "-".equals(s)){
            return LEVEL_01;
        }else if ("*".equals(s) || "/".equals(s)){
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }

    public static  List<String> doMatch(String s) throws  Exception{
        if (s == null || "".equals(s.trim())){
            throw new RuntimeException("data is empty");
        }
        if(!isNumber(s.charAt(0)+"")){
            throw  new RuntimeException("data illeagle,start not with a number");
        }
        s = replaceAllBlank(s);
        String each;
        int start = 0;
        for (int i = 0; i <s.length() ; i++) {
            if (isSymbol(s.charAt(i)+"")){
                each = s.charAt(i)+"";
                //栈为空，（操作符，或者操作符优先级大于栈顶优先级 && 操作符优先级不是（）的优先级 及是 ） 不能直接入栈
                if (stack.isEmpty() || LEFT.equals(each)
                        || ((calcLeve(each) > calcLeve(stack.peek()))) && calcLeve(each) < LEVEL_HIGH){
                    stack.push(each);
                }else if (!stack.isEmpty() && calcLeve(each) <= calcLeve(stack.peek())){
                    //栈非空，操作符优先级小于等于栈顶优先级时出栈入列，直到栈为空，或者遇到了（，最后捉拿符入栈
                    while (!stack.isEmpty() && calcLeve(each) <= calcLeve(stack.peek())){
                        if(calcLeve(stack.peek()) == LEVEL_HIGH){
                            break;
                        }
                        data.add(stack.pop());
                    }
                    stack.push(each);
                }else if(RIGHT.equals(each)){
                    // ) 操作符，依次出栈入列直到空栈或者遇到了第一个）操作符，此时）出栈
                    while (!stack.isEmpty() && LEVEL_HIGH >= calcLeve(stack.peek())){
                        if (LEVEL_HIGH == calcLeve(stack.peek())) {
                            stack.pop();
                            break;
                        }
                        data.add(stack.pop());
                    }
                }
                start = i;//前一个运算符的位置
            }else if (i == s.length()-1 || isSymbol(s.charAt(i+1)+"")){
                each = start == 0?s.substring(start,i+1):s.substring(start+1,i+1);
                if (isNumber(each)){
                    data.add(each);
                    continue;
                }
                throw new RuntimeException("data not match number");
            }
        }
        //如果栈里还有元素，此时元素需要依次出栈入列，可以想象栈里剩下栈顶为/，栈底为+，应该依次出栈入列，可以直接翻转整个stack 添加到队列
        Collections.reverse(stack);
        data.addAll(new ArrayList<>(stack));
        System.out.println("data="+data);
        return data;
    }

    /**
     * 算出结果
     * @param list
     * @return
     */
    public static Double doCalc(List<String> list){
        Double d = 0d;
        if (list == null || list.isEmpty()){
            return null;
        }
        if (list.size() == 1){
            System.out.println(list);
            d = Double.valueOf(list.get(0));
            return d;
        }
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
            if (isSymbol(list.get(i))){
                Double d1 = doTheMath(list.get(i-2),list.get(i-1),list.get(i));
                list1.remove(i);
                list1.remove(i-1);
                list1.set(i-2,d1+"");
                list1.addAll(list.subList(i+1,list.size()));
                break;
            }
        }
        doCalc(list1);
        return d;
    }

    /**
     * 运算
     * @param s1
     * @param s2
     * @param symbol
     * @return
     */
    public static double doTheMath(String s1, String s2, String symbol){
        Double result;

        switch (symbol){
            case ADD:
                result = Double.valueOf(s1)+Double.valueOf(s2);
                break;
            case MINUS:
                result = Double.valueOf(s1)-Double.valueOf(s2);
                break;
            case TIMES:
                result = Double.valueOf(s1) * Double.valueOf(s2);
                break;
            case DIVISION:
                result = Double.valueOf(s1) / Double.valueOf(s2);
                break;
            default:
                result = null;
        }
        BigDecimal bg = new BigDecimal(result);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("计算的结果是"+f1);
        return f1;
    }

    public static void main(String[] args) {
        String math = "12.0988+(2 -3.55)*4+10/5.0";
        try {
            doCalc(doMatch(math));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
