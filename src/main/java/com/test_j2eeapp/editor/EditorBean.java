package com.test_j2eeapp.editor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.plantuml.SourceStringReader;

//import net.sourceforge.plantuml.SourceStringReader;
//import net.sourceforge.plantuml.core.DiagramDescription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;




@ManagedBean(name = "editor")
public class EditorBean {
	

	

	private String value = "a";
	private String txtAnotherProperty1;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	


	public void show() throws ScriptException, IOException {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        txtAnotherProperty1= request.getParameter("komutdosyasi");        
        use(txtAnotherProperty1);
        display();        
	}
	
	public void display() {
		
		String sRootPath = new File("/").getAbsolutePath();
		System.out.println(sRootPath);
	}
	
	public void use(String plantUmlMarkup) throws IOException{
		String test = "@startuml\n!pragma graphviz_dot jdot\n"+plantUmlMarkup+"\n@enduml";
		System.out.println(test);
		SourceStringReader s = new net.sourceforge.plantuml.SourceStringReader(test);
		FileOutputStream file = new FileOutputStream("C:\\dev\\image.png");
		s.generateImage(file);
//		System.out.println(System.getProperty("./"));
		file.close();		

	}
	
}