package cn.com.liandisys.wujian.learningjava;

import java.awt.event.ActionListener;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

public class TestLambda {

	public static void main(String[] args) {
		
		/* **************************  Lambda 表达式的5种形式 ************************** */
		// Pattern 1
		// 该 Lambda 表达式使用空括号 () 表示没有参数,并且->后的expression没有返回值。
		// Runnable接口也只有一个 run 方法,没有参数,且返回类型为 void。
		Runnable noArguments = () -> System.out.println("Hello World");
		
		// Pattern 2
		// Lambda 表达式包含且只包含一个参数,可省略参数的括号,并且->后的expression没有返回值。
		// ActionListener接口也只有一个 方法,一个参数,且返回类型为 void。
		ActionListener oneArgument = event -> System.out.println("button clicked");
		
		// Pattern 3
		// 复数行代码
		Runnable multiStatement = () -> {
		    System.out.print("Hello");
		    System.out.println(" World");
		};
		
		// Pattern 4
		// 包含两个参数的方法。
		BinaryOperator<Long> functionAdd = (x, y) -> x + y;  	// 创建一个函数
		Long result4 = functionAdd.apply(1L, 2L);				// 应用函数，给函数传值，得到计算结果
		
		// Pattern 5
		// 和Pattern 4的不同之处在于，多了对参数类型的显示声明。

		BinaryOperator<Long> function = (Long x, Long y) -> x + y;		// 创建一个函数
		Long result5 = function.apply(1L, 2L);							// 应用函数，给函数传值，得到计算结果
		
		// 测试 java.util.fuction
		BiConsumer bcs = (a, b) -> System.out.println(a + "===" + b);
		bcs.accept("1","2");
		
		// 测试函数式接口
		Action action = System.out :: println;
        action.execute("Hello World!");
        test(System.out :: println, "Hello World!");
	}

	static void test(Action action, String str) {
        action.execute(str);
    }
}

@FunctionalInterface
interface Action<T> {
    public void execute(T t);
}