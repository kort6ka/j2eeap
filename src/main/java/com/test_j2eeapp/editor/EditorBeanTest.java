package com.test_j2eeapp.editor;

import static org.junit.Assert.*;
import java.lang.reflect.Method;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import java.lang.reflect.Method;

public class EditorBeanTest{
	
	@Test
	public void test() throws Exception {
//		fail("Not yet implemented");
		String line2 = "";
//		EditorBean beanTest = new EditorBean();
//		ArrayList<String> list = new ArrayList<String>();


		try {
			FileReader reader = new FileReader("C:\\dev\\Eclipse\\Oxygen\\tasks\\task"+1+".txt"); // hardcode
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
//				list.add(line);
				line2 += line;
			}
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		
		

		
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = request.getParameter("komutdosyasi"); 
		String value = "1";
//		String value = "class B{   public B(){System.out.println(\"Hi\");}}\r\n" + 
//				"class C{   public C(){System.out.println(\"Hi\");}}";		
//		CompilerBean cb = new CompilerBean();
//		System.out.println(value);
//		cb.compile1(value);
		
		String str1 = "class Bar1{Bar3 attribute_1;Bar2 attribute;String name;}class Bar2{}class Bar3{heloo}";
		String str2 = line2;
//	    String str1 = value.replaceAll("\r\n", "");
//	    str1.replaceAll("\r\n", "");
	    
//	    System.out.println(testEditor.getValue());
//	    System.out.println("Double\r\nspaced".replace("\r\n", ""));
	    
		org.junit.Assert.assertEquals(str1,str2);
	    
  
	    
	}
	
	@Test
	public void compileAll_WhenTypical() throws Exception {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String value = "";
		value += request.getParameter("komutdosyasi");
		String jsEditor_readOnly = request.getParameter("valueForTest");
		value += request.getParameter("codeTest");
//		value += codeTest;
//		System.out.println(value);
		String result ="";
		CompilerBean cb = new CompilerBean();
		result = cb.deriveFullClassFromSource(value);
//		result = "10000";
		System.out.println(result + " resultS");
		String[] parts = result.split(";");
		

//		
//		cb.generateGsraph(classBuild);
//		BankAccount a = new BankAccount("abc123", 15000);
//        BankAccount b = new BankAccount("def456", 25000);
		System.out.println(jsEditor_readOnly);
//        new TransferContext(a, b).transfer(5000);  
		for(int i = 0; i < parts.length; i++) {
			System.out.println(parts[i]);
			assertEquals(jsEditor_readOnly, parts[i]);
		}

		

	}
	

	@Test
    public void testTransfer() {
        
    }
	
	@Test
    public void testTransfer_1() {
        BankAccount a = new BankAccount("abc123", 15000);
        BankAccount b = new BankAccount("def456", 25000);
//        System.out.println(a.getBalance());	
        new TransferContext(b, a).transfer(5000);
//        System.out.println(a.getBalance());	

        
        assertEquals((Integer)20000, b.getBalance());
    }

}


