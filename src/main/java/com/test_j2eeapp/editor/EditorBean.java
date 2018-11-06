package com.test_j2eeapp.editor;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import net.openhft.compiler.CompilerUtils;
import net.sourceforge.plantuml.SourceStringReader;

//import net.sourceforge.plantuml.SourceStringReader;
//import net.sourceforge.plantuml.core.DiagramDescription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;




@ManagedBean(name = "editor")
@SessionScoped
//@RequestScoped
//@ViewScoped

public class EditorBean {
	private String value = "public class Test{\r\n" + 
			"    int getBalanceTest(){\r\n" + 
			"        return 10000;\r\n" + 
			"    }\r\n" + 
			"}";
//	private String testResultValue = "1";
	private String random;
	private String txtAnotherProperty1;
	private ArrayList<String> list = new ArrayList<String>();
	

	public List<String> getList() {
	    return this.list;
	}
	
	public String getValue() {
		return value;
	}
	

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	public void show() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		value = request.getParameter("komutdosyasi"); 
//		CompilerBean cb1 = new CompilerBean();
//		cb1.compile(value);	
		
		
		//test
		Result result = JUnitCore.runClasses(EditorBeanTest.class);
//		System.out.println(result.wasSuccessful());
	
		if(result.wasSuccessful() == true) {
//			use(value);
//			value = "Test is ok";
			System.out.println("Test is ok ");
//			FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		}else {
//			use(value);
//			clearTask();
			System.out.println("Test is not ok");
//			value += "Test is not ok "; 
//			value += "\n\r";
			for (Failure failure : result.getFailures()) {
		         System.out.println(failure.toString());
		         //value += failure.toString();
		         
		      }
//			FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		}
//		System.out.println(getValue()+"getvalue");
		//test
//        use(value);
               
	}
	public void clearTask() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		displayTask();
	}
	public void displayTask() {	
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		random = request.getParameter("random");
//		random = "1";
//		System.out.println(random);
//		File folder = new File("C:\\dev\\Eclipse\\Oxygen\\tasks");
//		
//		for (File pdf : folder.listFiles()) { 
//			System.out.println(pdf.getName());
//		}
		try {
			FileReader reader = new FileReader("C:\\dev\\Eclipse\\Oxygen\\tasks\\task"+random+".txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
				list.add(line);
				
			}
				
				reader.close();
				FacesContext.getCurrentInstance().getExternalContext().redirect("./");
//				prepareFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	
	public void use(String plantUmlMarkup) throws IOException{
		
//		System.out.println(plantUmlMarkup.contains("class"));
//		for(int i = 0; i<5; i++) {
//			if(plantUmlMarkup.contains("class") == true) {
//				System.out.println(i);
//			}
//		}
//		System.out.println(plantUmlMarkup.replaceAll("\r\n", ""));
//		String tst = plantUmlMarkup.replaceAll("\r", "");
		String test = "@startuml\n!pragma graphviz_dot jdot\n"+plantUmlMarkup+"\n@enduml";
//		System.out.println(test);
		SourceStringReader s = new net.sourceforge.plantuml.SourceStringReader(test);
		FileOutputStream file = new FileOutputStream("C:\\dev\\image.png");
		s.generateImage(file);
//		System.out.println(System.getProperty("./"));
		file.close();
	}
	public void prepareFile() {
		byte[] exportContent = "Hy Buddys, thanks for the help!".getBytes();
        // here something bad happens that the user should know about
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("record 2 was flawed"));
    }
}