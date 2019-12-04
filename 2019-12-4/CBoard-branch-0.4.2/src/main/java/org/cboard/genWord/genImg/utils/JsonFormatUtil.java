package org.cboard.genWord.genImg.utils;

import java.util.Stack;

/**
 * 该类提供格式化JSON字符串的方法。
 * 该类的方法formatJson将JSON字符串格式化，方便查看JSON数据。
 * <p>例如：
 * <p>JSON字符串：["yht","xzj","zwy"]
 * <p>格式化为：
 * <p>[
 * <p>     "yht",
 * <p>     "xzj",
 * <p>     "zwy"
 * <p>]
 *
 * <p>使用算法如下：
 * <p>对输入字符串，追个字符的遍历
 * <p>1、获取当前字符。
 * <p>2、如果当前字符是前方括号、前花括号做如下处理：
 * <p>（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
 * <p>（2）打印：当前字符。
 * <p>（3）前方括号、前花括号，的后面必须换行。打印：换行。
 * <p>（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
 * <p>（5）进行下一次循环。
 * <p>3、如果当前字符是后方括号、后花括号做如下处理：
 * <p>（1）后方括号、后花括号，的前面必须换行。打印：换行。
 * <p>（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
 * <p>（3）打印：当前字符。
 * <p>（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
 * <p>（5）继续下一次循环。
 * <p>4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
 * <p>5、打印：当前字符。
 *
 */
public class JsonFormatUtil {
    /**
     * 单位缩进字符串。
     */
    private static String SPACE = "  ";

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json)
    {
        StringBuffer result = new StringBuffer();
        int length = json.length();
        int number = 0;
        char key = 0;

        //遍历输入字符串。
        for (int i = 0; i < length; i++)
        {
            //1、获取当前字符。
            key = json.charAt(i);

            if(key=='\"'){
                //如果前面的这些有相应的字符formatter,则跳过相应的扫描的阶段，直接获取相应的值
                if(((i-12)>0)&&(json.substring(i-12,i-3).equals("formatter"))){
  //                  System.out.println("\" json.substring(i-13,i-4)="+json.substring(i-13,i-4));
                    int index1=i+JsonFormatUtil.getColumEndIndex(i,json);
                    result.append(json.substring(i,index1));
                    i=index1-1;
                    continue;
                }
            }
            //2、如果当前字符是前方括号、前花括号做如下处理：
            if((key == '[') || (key == '{') )
            {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if((i - 1 > 0) && (json.charAt(i - 1) == ':'))
                {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);
                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');
                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }
            //3、如果当前字符是后方括号、后花括号做如下处理：
            if((key == ']') || (key == '}') )
            {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');
                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));
                //（3）打印：当前字符。
                result.append(key);
                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if(((i + 1) < length) && (json.charAt(i + 1) != ','))
                {
                   // result.append('\n');
                }
                //（5）继续下一次循环。
                continue;
            }
            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            // (修改了，还要逗号字符后面为双引号",或者是前括号{的情况再执行换行的操作)
            if((key == ',')&&(json.length()>i+1)&&(json.charAt(i+1)=='\"'||json.charAt(i+1)=='{'))
            {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            if((key == ';'))
            {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            //5、打印：当前字符。
            result.append(key);
        }
        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number)
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < number; i++)
        {
            result.append(SPACE);
        }
        return result.toString();
    }

    /**
     * 获得第一次匹配的 双引号就退出程序
     * */
    public static int getColumEndIndex(int startIndex,String json){
        int index=-1;
        Stack<Character> sc = new Stack<Character>();
        char[] c = json.substring(startIndex+1,json.length()-1).toCharArray();
        sc.push('0');
        for (int i = 0; i < c.length; i++) {
            if (c[i]=='\"') {
                sc.peek();
                index=i+1;
                break;//第一次匹配到字符就退出
            }
        }
        return index;
    }
}
