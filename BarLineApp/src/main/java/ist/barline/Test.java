package ist.barline;

import ist.barline.config.DataSourceConfig;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*System.out.println("asdfsd");
		AbstractApplicationContext container = new AnnotationConfigApplicationContext(Config.class);
		String msg;
		msg = container.getBean("testBean").toString();
		System.out.println(msg);
		container.close();*/
		
		DataSourceConfig ds = new DataSourceConfig();
		ds.getConnection();
		System.out.println(ds.getConnection());
	}
}
