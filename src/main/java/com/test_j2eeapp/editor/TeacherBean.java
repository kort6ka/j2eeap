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
import javax.xml.transform.OutputKeys;
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
//	private String taskInstruction;
//	private String testCode;
//	private String sourceCode;
//	private String plantUmlCode;
	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	public void getTask() throws ScriptException, IOException {
		
		String taskHeader = request.getParameter("task_name");
		String taskInstruction = request.getParameter("taskInstruction");
		String testCode = request.getParameter("testCode");
		String testValue = request.getParameter("testValue");
		String sourceCode = request.getParameter("sourceCode"); 
		String plantUmlCode = request.getParameter("plantUmlCode");
//		System.out.println(task + task_name + cheese_testTask);
//        createTask(task, task_name);
		
        createTaskXml(taskHeader ,taskInstruction, testCode,testValue, sourceCode, plantUmlCode);
	}
	public void goBack() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
	}
	public void generateImage() throws IOException{
		String plantUmlCode = request.getParameter("plantUmlCode");
		System.out.println(plantUmlCode);
		CompilerBean cb = new CompilerBean();
		cb.generateGraph(plantUmlCode, request.getParameter("task_name"));
		cb.generateGraph(plantUmlCode, "image-tobe");
	}
	public void createTaskXml(String taskHeader, String taskInstruction, String testCode, String testValue, String sourceCode, String plantUmlCode) {
		try {
				
			System.out.println(taskHeader);
			System.out.println(taskInstruction);
			System.out.println(testValue);
			System.out.println(testCode);
			System.out.println(sourceCode);
			System.out.println(plantUmlCode);
			
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
			Element taskHeaderXml = doc.createElement("taskHeader");
			taskHeaderXml.appendChild(doc.createTextNode(taskHeader));
			rootElement.appendChild(taskHeaderXml);
			
			Element firstname = doc.createElement("taskInstruction");
			firstname.appendChild(doc.createTextNode(taskInstruction));
			rootElement.appendChild(firstname);
			
			// lastname elements
						Element testValueXml = doc.createElement("testValue");
						testValueXml.appendChild(doc.createTextNode(testValue));
						rootElement.appendChild(testValueXml);

			// lastname elements
			Element lastname = doc.createElement("testCode");
			lastname.appendChild(doc.createTextNode(testCode));
			rootElement.appendChild(lastname);

			// nickname elements
			Element nickname = doc.createElement("sourceCode");
			nickname.appendChild(doc.createTextNode(sourceCode));
			rootElement.appendChild(nickname);

			// salary elements
			Element salary = doc.createElement("plantUmlCode");
			salary.appendChild(doc.createTextNode(plantUmlCode));
			rootElement.appendChild(salary);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

//			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("http://www.oracle.com/xml/is-standalone", "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(".\\tasks\\"+ taskHeader +".xml"));

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
	
