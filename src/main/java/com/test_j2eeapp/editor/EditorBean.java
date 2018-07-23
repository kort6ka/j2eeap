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

import net.sourceforge.plantuml.*;



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

        System.out.println(txtAnotherProperty1);
        
        use(txtAnotherProperty1);
        
        
        
	}
	
	public void use(String plantUmlMarkup) throws IOException{
		
//		FileOutputStream fileOS = null;
		
		
		try {
			ByteArrayOutputStream bous = new ByteArrayOutputStream();
			SourceStringReader reader = new SourceStringReader(plantUmlMarkup);
			String desc = reader.generateImage(bous);
			
			byte [] data = bous.toByteArray();
			InputStream in = new ByteArrayInputStream(data);
		    BufferedImage convImg = ImageIO.read(in);

		    ImageIO.write(convImg, "png", new File(".\\image.png"));

		    System.out.print(desc);
			System.out.println("Done");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("F not found");
			
		}
		
		
//		try {
//			BufferedWriter wr = new BufferedWriter(new FileWriter(".\\test1.txt"));
//			wr.write(plantUmlMarkup);
//			wr.close();
//			System.out.println("Done");
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//			System.out.println("F not found");
//			
//		}

		
		
		
		
		
		
		
//		StringBuilder plantUmlSource = new StringBuilder();
//
//        plantUmlSource.append("@startuml\n");
//
//        plantUmlSource.append("car --|> wheel");
//
//        plantUmlSource.append("@enduml");
//		
//        @startuml
//        !pragma graphviz_dot jdot
//        class Foo1
//
//        Foo1 --> Foo2
//        Foo1 --> Foo3
//        Foo1 ---> Foo4 : test 4
//        Foo1 ----> Foo5 : test 5
//
//        @enduml
//
//
//        
//        /*
//
//        * @startuml
//		!pragma graphviz_dot jdot
//        * car --|> wheel
//
//        * @enduml
//
//        */
////
////        SourceStringReader reader = new SourceStringReader(plantUmlSource.toString());
//
////        FileOutputStream output = new FileOutputStream(new File("C:/Users/u1dd_fsm/test.svg"));
////
////        reader.generateImage(output, new FileFormatOption(FileFormat.SVG, false));
//		Transcoder t = TranscoderUtil.getDefaultTranscoder();
//        String s = "Alice->Bob: hello1\nAlice->Bob: hello2\n";
//        String url = t.encode(s);
////        System.err.println(url);
//        System.out.println(s);
	}
	
}