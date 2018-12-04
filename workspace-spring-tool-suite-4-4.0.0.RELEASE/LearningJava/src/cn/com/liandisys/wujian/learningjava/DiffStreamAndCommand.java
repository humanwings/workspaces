package cn.com.liandisys.wujian.learningjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffStreamAndCommand {

	public static void main(String[] args) {
		
		/* *************   Stream ***************/
		
		// 1. Individual values
		Stream stream = Stream.of("a", "b", "c");

		// 2. 从 Collection 和数组生成Stream
		String [] strArray = new String[] {"a", "b", "c"};
		stream = Stream.of(strArray);
		stream = Arrays.stream(strArray);
		
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
		stream = list.parallelStream();
		
		// reduce
		System.out.println(Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min));
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.out.println(sumValue);
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.out.println(sumValue);
		
		/* *************   命令式和函数式实现的对比 ***************/
		
		
		// 对比1：  虚词过滤   
		filterNonWords();
		// 对比2：  取得属性列表
		getAttrList();
		
	
	} 
	
	/**   
	 * 虚词过滤   
	 */
	private static void filterNonWords() {
		String test = "Before the popularity of network communicative tools, such as Weibo and WeChat, people get used to know a person by face and face talk, but now the situation has changed, the young generation tend to know a person by network social communicative tools. If they are interested in making friends, then they will be friends online first and then keep trace with the former information on the record. It seems that we can learn a person so fast and convenient. There is a short video about a girl dated a guy, but the guy did not have any account on the Internet, then the girl felt not comfortable and started to question if the guy was a criminal. The video satirizes people to rely on the Internet too much. They rather to communicate with a person by the Internet instead of face and face talk, while the latter is much trustworthy";    
		// 命令式方式过滤实现
		System.out.println(wordFreq1(test).toString());   
		// 函数式方式过滤实现
		System.out.println(wordFreq2(test).toString());  
	}
	
	/**   
	 * 虚词过滤   
	 */ 
	private static Set<String> NON_WORDS = new HashSet<String>(){
		{	
			add("the");
			add("and");
			add("of");
			add("to");
			add("a"); 
			add("i");
			add("it");
			add("in");
			add("or");
			add("is");
			add("d"); 
			add("s");
			add("as");
			add("so");
			add("but");
			add("be");
		}
	}; 

	/**   * 命令式方式过滤实现   * @param words   * @return   */  
	public static Map<String,Integer> wordFreq1(String words){    
		TreeMap<String,Integer> wordMap = new TreeMap<>();  
		Matcher m = Pattern.compile("\\w+").matcher(words);    
		while (m.find()){      
			String word = m.group().toLowerCase();      
			if (! NON_WORDS.contains(word)){        
				if (wordMap.get(word) == null){          
					wordMap.put(word,1);        
				}else {          
					wordMap.put(word,wordMap.get(word)+1);        
				}
			}
		}
		return wordMap;  
	}

	/**   
	 * 将待处理对象转为列表集合   
	 * @param words   
	 * @param regex   
	 * @return   
	 */  
	private static List<String> regexToList(String words,String regex){    
		List wordList = new ArrayList<>();    
		Matcher m = Pattern.compile(regex).matcher(words);    
		while (m.find())
			wordList.add(m.group());
		return wordList;
	}  

	/**   
	 * 函数式方式过滤实现,对集合统一处理   
	 * @param words
	 * @return
	 */  
	public static Map wordFreq2(String words){
		TreeMap<String,Integer> wordMap = new TreeMap<>();
		regexToList(words,"\\w+").stream()
			.map(w -> w.toLowerCase())
			.filter(w -> !NON_WORDS.contains(w))
			.forEach(w -> wordMap.put(w,wordMap.getOrDefault(w,0)+1));
		return wordMap;  
	}  
	
	public static void getAttrList(){    
		//初始化数据   
		List<User> userList = new ArrayList<>();    
		for (int i = 0; i < 10; i++) {      
			User user = new User();      
			user.setUserName("humanwings" + i);      
			userList.add(user);
			}    
	//命令式方式实现    
		List<String> newUserNameList1 = new ArrayList<>();    
		for (int i=0;i<userList.size();i++){      
			User user = userList.get(i);      
			newUserNameList1.add(user.getUserName());
		}    
	//函数式方式实现 java 8 funcational    
		List<String> newUserNameList2 = userList.stream()                        
				.map(p -> p.getUserName())                        
				.collect(Collectors.toList());  
	}

}

/** 
 * Created by lilongsheng on 2017/11/8. 
 * 用户数据实体对象 
 */ 
class User {  
	/**   * 主键   */  
	private Integer id;  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**   * 用户名   */  
	private String userName;  
	/**   * 用户密码   */  
	private String userPassword;  
	/**   * 年龄   */  
	private Integer age;  
	/**   * 电话   */  
	private String phone;  
	/*其它属性*/ 
} 
