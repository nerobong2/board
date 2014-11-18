package com.board.listener;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class ApplicationContext {

	Hashtable<String,Object> objTable = new Hashtable<String,Object>();
	
	//프론트 컨트롤러에서 사용될 객체
	public Object getBean(String key){
		
		return objTable.get(key);
		
	}

	public ApplicationContext(String propertiesPath) throws Exception{
		
		
		try{
			Properties prop = new Properties();
			prop.load(new FileReader(propertiesPath));
			
			// TODO Auto-generated constructor stub
			
			prepareObject(prop);
			dependencyInjection();
			System.out.println("=========================================================================");
		}catch (Exception e){
			e.printStackTrace();
		}
		//프로퍼티 경로로부터 데이터 읽기 prop 은 내부적으로 key value 로 존재 한다  : 구분자
		
		
	}
	//프로퍼티 객체(map) 으로 부터 객체를 생성 하여 objTable 에 저장
	private void prepareObject(Properties prop) throws Exception{
		
		Context context = new InitialContext();
		
		String key = null;
		String value = null;
		
		
		for(Object item : prop.keySet()){
			
			key = (String)item;
			value = prop.getProperty(key);
			System.out.println(key+"="+value);
			
			if(key.startsWith("jndi.")){
				
				//lookup() 는 톰캣 서버객체로부터 value 에 해당 하는 객체를 찾아 인스턴스화 하여 반환한다.
				objTable.put(key, context.lookup(value));
				
			}else{
				objTable.put(key, Class.forName(value).newInstance());
				//value.getClass().newInstance();
			}
		}
	}
	//객체 주입이 필요한 경우 callSetter 를 호출 한다.
	private void dependencyInjection() throws Exception {
		//jndi 객체는 setter 메소드의 파라미터를 주입시킬 필요가 없기때문에 패스.
		for(String  key : objTable.keySet()){
			if(!key.startsWith("jndi.")){
				callSetter(objTable.get(key));
			}
		}
	}
	
	private void callSetter(Object object) throws Exception {
		
		//setter 메소드가 있는 클래스 객체를 담기 위한 변수
		System.out.println("objectTable 저장된 객체 : " + object.getClass().getName());
		//클래스 객체에 존재하는 파라미터 타입을 찾아 담을 변수
		Object dependencyObject = null;
		
		for(Method  m : object.getClass().getMethods()){
			
			if(m.getName().startsWith("set")){
				
				System.out.println("의존객체가 필요한 메서드 : " + m.getName());
				dependencyObject = objectByType(m.getParameterTypes()[0]);
				
				
				if(dependencyObject != null ){
					
					System.out.println("검색된 의존 객체 : "+ dependencyObject.getClass().getName());
					//setter 메소드 호출 의존객체를 주입한다
					m.invoke(object, dependencyObject);
					System.out.println("주입완료");
				}
				
			}
			
		}
		
	}
	
	//setter 메소드 에 파라미터 타입을 ojTable 로부터 검색해서 반환한다.
	private Object objectByType(Class<?> type) {
		
		for(Object object : objTable.values()){
			
			if(type.isInstance(object)){
				
				return object;
			}
		}
		
		return null;
	}
	
	
}
