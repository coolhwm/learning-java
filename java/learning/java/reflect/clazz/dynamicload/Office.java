package learning.java.reflect.clazz.dynamicload;

public class Office{
	public static void main(String[] args){
		if("Word".equals(args[0])){
			Word w = new Word();
			w.start();
		}
		if("Excel".equals(args[0])){
			Excel e = new Excel();
			e.start();
		}
	}
}
