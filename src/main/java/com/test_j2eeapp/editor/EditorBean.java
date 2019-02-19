package com.test_j2eeapp.editor;

import java.awt.Font;
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


@ManagedBean(name = "editor")
@SessionScoped
public class EditorBean {
//	private String value = "public class Test{\r\n int getBalanceTest(){\r\n return 10000;\r\n}\r\n}";
	private String taskInstruction = "";
	private String sourceCode = "";
	private String task_image = "blank";
	private String message = "";
	private String testValue = "";
	private String asisImage = "blank";
	private String random;
	private String conOutput = "blank";
	private String testCode = "";
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public String getConOutput() {
		return conOutput;
	}
	
	public String getAsisImage() {
		return asisImage;
	}
	public String setAsisImage(String asisImage) {
		return asisImage;
	}
	public String getTestValue() {
		return testValue;
	}
	public String getTestCode() {
		return testCode;
	}
	public String getTask_image() {
		return task_image;
	}
	public String getSourceCode() {
		return sourceCode;
	}

	public List<String> getList() {
	    return this.list;
	}
	
	public String getTaskInstruction() {
		return taskInstruction;
	}
	

	public void setTaskInstruction(String taskInstruction) {
		this.taskInstruction = taskInstruction;
	}
	public String getMessage() {
		return message;
	}
	

	public void getMessage(String message) {
		this.message = message;
	}
	
	public void displayImg() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./teacher.xhtml");
	}
	public void displayTeacher() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./teacher.xhtml");
	}
	public void show() throws Exception {
		
		
		//test
		Result result = JUnitCore.runClasses(EditorBeanTest.class);
//		System.out.println(result.wasSuccessful());
		 
		if(result.wasSuccessful() == true) {
//			use(value);
			message = "Test is ok \r\n";
			System.out.println("Test is ok ");
			
			BufferedImage image = new CompilerBean().convertTextToGraphic(message, new Font("Arial", Font.PLAIN, 12));
	        //write BufferedImage to file
	        ImageIO.write(image, "png", new File("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\console-output.png"));
//			FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		}else {
//			use(value);
//			clearTask();
			System.out.println("Test is not ok");
			message = "Test is not ok \r\n"; 
//			value += "\n\r";
			for (Failure failure : result.getFailures()) {
		         message += failure.toString()+"\r\n";
		         
		      }
//			System.out.println(message);
			BufferedImage image = new CompilerBean().convertTextToGraphic(message, new Font("Arial", Font.PLAIN, 12));
	        //write BufferedImage to file
	        ImageIO.write(image, "png", new File("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\console-output.png"));
//			FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		}

               
	}
	public void clearTask() throws IOException {
		String blankMessage = " ";
		

		//ImageIO.write(image, "png", new File("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\image-tobe.png"));
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		BufferedImage image = new CompilerBean().convertTextToGraphic(blankMessage, new Font("Arial", Font.PLAIN, 12));
		ImageIO.write(image, "png", new File("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\console-output.png"));
		ImageIO.write(image, "png", new File("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\image.png"));
//		displayTask();
	}
	public void displayTask13() {	
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		random = request.getParameter("random");

//		try {
//			FileReader reader = new FileReader("C:\\dev\\Eclipse\\Oxygen\\tasks\\task"+random+".txt");
//			BufferedReader bufferedReader = new BufferedReader(reader);
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
////				System.out.println(line);
//				list.add(line);
//				
//			}
//				
//				reader.close();
//				FacesContext.getCurrentInstance().getExternalContext().redirect("./");
////				prepareFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		
		
			SAXBuilder builder = new SAXBuilder();
		  File xmlFile = new File("C:\\dev\\Eclipse\\Oxygen\\tasks\\task13.xml");
		  try {

				Document document = (Document) builder.build(xmlFile);
				Element rootNode = document.getRootElement();
				List list = rootNode.getChildren("Task");
				taskInstruction = rootNode.getChildText("taskInstruction");				
				testCode = rootNode.getChildText("testCode");				
				sourceCode = rootNode.getChildText("sourceCode");
				testValue = rootNode.getChildText("testValue");
				task_image = rootNode.getChildText("taskHeader");
				conOutput = "console-output";
				asisImage = "image";
				FacesContext.getCurrentInstance().getExternalContext().redirect("./");

			  } catch (IOException io) {
				System.out.println(io.getMessage());
			  } catch (JDOMException jdomex) {
				System.out.println(jdomex.getMessage());
			  }
		
	}	
	public void displayTask14() {	
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		random = request.getParameter("random");

//		try {
//			FileReader reader = new FileReader("C:\\dev\\Eclipse\\Oxygen\\tasks\\task"+random+".txt");
//			BufferedReader bufferedReader = new BufferedReader(reader);
//			String line;
//			while ((line = bufferedReader.readLine()) != null) {
////				System.out.println(line);
//				list.add(line);
//				
//			}
//				
//				reader.close();
//				FacesContext.getCurrentInstance().getExternalContext().redirect("./");
////				prepareFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		
		
			SAXBuilder builder = new SAXBuilder();
		  File xmlFile = new File("C:\\dev\\Eclipse\\Oxygen\\tasks\\task12.xml");
		  try {

				Document document = (Document) builder.build(xmlFile);
				Element rootNode = document.getRootElement();
				List list = rootNode.getChildren("Task");
				taskInstruction = rootNode.getChildText("taskInstruction");				
				testCode = rootNode.getChildText("testCode");				
				sourceCode = rootNode.getChildText("sourceCode");
				testValue = rootNode.getChildText("testValue");
				task_image = rootNode.getChildText("taskHeader");
				conOutput = "console-output";
				asisImage = "image";
				FacesContext.getCurrentInstance().getExternalContext().redirect("./");

			  } catch (IOException io) {
				System.out.println(io.getMessage());
			  } catch (JDOMException jdomex) {
				System.out.println(jdomex.getMessage());
			  }
		
	}	
	public void prepareFile() {
		byte[] exportContent = "Hy Buddys, thanks for the help!".getBytes();
        // here something bad happens that the user should know about
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("record 2 was flawed"));
    }
}