package com.test_j2eeapp.editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.zxing.Writer;

@ManagedBean(name = "teacher")
public class TeacherBean {
	private String task;
	private String task_name;
	private String cheese_testTask;
	public void getTask() throws ScriptException, IOException {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		task = request.getParameter("task");
		task_name = request.getParameter("task_name"); 
		cheese_testTask = request.getParameter("cheese_testTask"); 
        createTask(task, task_name);
        createTaskXml();
	}
	public void goBack() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
	}
	public void createTaskXml() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Task");
			doc.appendChild(rootElement);

			// staff elements
//			Element staff = doc.createElement("Staff");
//			rootElement.appendChild(staff);
//
//			// set attribute to staff element
//			Attr attr = doc.createAttribute("id");
//			attr.setValue("1");
//			staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element firstname = doc.createElement("taskname");
			firstname.appendChild(doc.createTextNode(task_name));
			rootElement.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("task");
			lastname.appendChild(doc.createTextNode(task));
			rootElement.appendChild(lastname);

			// nickname elements
			Element nickname = doc.createElement("testTask");
			nickname.appendChild(doc.createTextNode(cheese_testTask));
			rootElement.appendChild(nickname);

			// salary elements
//			Element salary = doc.createElement("salary");
//			salary.appendChild(doc.createTextNode("100000"));
//			staff.appendChild(salary);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(".\\tasks\\"+ task_name +".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
	public void createTask(String task, String task_name) {
		System.out.println(task_name);	
		System.out.println(task);	
		System.out.println(cheese_testTask);	
		try{
            // Create new file
            File file = new File(".\\tasks\\"+ task_name +".txt");
            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            // Write in file
            bw.write(task);
            // Close connection
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

	}
		
	
}
	
