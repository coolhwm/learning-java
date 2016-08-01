package learning.java.reflect.dynamicload;

public class OfficeBetter{
	public static void main(String[] args){
		try{
			//dynamic load
			Class  c = Class.forName("learning.java.reflect." + args[0]);

			//create instance
			OfficeAble oa = (OfficeAble) c.newInstance();
			oa.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
