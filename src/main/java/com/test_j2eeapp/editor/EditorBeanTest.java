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

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import java.lang.reflect.Method;

public class EditorBeanTest{
	

	
	@Test
	public void testForBank() throws Exception {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
 
	    
		String value = "";
		value += request.getParameter("komutdosyasi");
		String jsEditor_readOnly = request.getParameter("valueForTest");
		value += request.getParameter("aftermath");
//		value += request.getParameter("codeTest_1");	
		Map<String, Integer> result = new HashMap<String, Integer>();
		CompilerBean cb = new CompilerBean();
		result = cb.deriveFullClassFromSource(value);
		System.out.println("testForBank");
		System.out.println(jsEditor_readOnly);
		System.out.println(result);
		if(!jsEditor_readOnly.isEmpty()) {
			Integer new_value = Integer.parseInt(jsEditor_readOnly);	
			assertEquals(new_value, result.get("getATest"));
		}
		

	}
//	@Test
//	public void testForLimit() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
//		System.out.println("testForLimit");
//		if(!jsEditor_readOnly.isEmpty()) {
//			Integer new_value = Integer.parseInt(jsEditor_readOnly);	
//			assertEquals(new_value, result.get("getBTest"));
//		}
//	}
//
//
//	
//	@Test
//    public void testTransfer_1() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
////		value += codeTest;
////		System.out.println(value);
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
////		result = "10000";
////		System.out.println(result + " resultS");
////		String[] parts = result.split(";");
//		
////		 StackTraceElement[] elements = Thread.currentThread().getStackTrace();oh
//		
////		    for (int i=0;i<elements.length;i++) {
////		    	System.out.println(elements[i].toString());
////		    }
////		
////		cb.generateGsraph(classBuild);
////		BankAccount a = new BankAccount("abc123", 15000);
////        BankAccount b = new BankAccount("def456", 25000);
//		System.out.println("testTransfer_1");
////        new TransferContext(a, b).transfer(5000);  
////		for(int i = 0; i < parts.length; i++) {
////			System.out.println(parts[i]);
//		Integer new_value = Integer.parseInt(jsEditor_readOnly);
//		assertEquals(new_value, result.get("getCTest"));
////		}
//        
//    }
//	@Test
//    public void testAccount() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
////		value += codeTest;
////		System.out.println(value);
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
////		result = "10000";
////		System.out.println(result + " resultS");
////		String[] parts = result.split(";");
//		
////		 StackTraceElement[] elements = Thread.currentThread().getStackTrace();oh
//		
////		    for (int i=0;i<elements.length;i++) {
////		    	System.out.println(elements[i].toString());
////		    }
////		
////		cb.generateGsraph(classBuild);
////		BankAccount a = new BankAccount("abc123", 15000);
////        BankAccount b = new BankAccount("def456", 25000);
//		System.out.println("testAccount");
////        new TransferContext(a, b).transfer(5000);  
////		for(int i = 0; i < parts.length; i++) {
////			System.out.println(parts[i]);
//		Integer new_value = Integer.parseInt(jsEditor_readOnly);
//		assertEquals(new_value, result.get("getCTest"));
////		}
//        
//    }
//	@Test
//    public void testAccout_2() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
////		value += codeTest;
////		System.out.println(value);
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
////		result = "10000";
////		System.out.println(result + " resultS");
////		String[] parts = result.split(";");
//		
////		 StackTraceElement[] elements = Thread.currentThread().getStackTrace();oh
//		
////		    for (int i=0;i<elements.length;i++) {
////		    	System.out.println(elements[i].toString());
////		    }
////		
////		cb.generateGsraph(classBuild);
////		BankAccount a = new BankAccount("abc123", 15000);
////        BankAccount b = new BankAccount("def456", 25000);
//		System.out.println("testAccout_2");
////        new TransferContext(a, b).transfer(5000);  
////		for(int i = 0; i < parts.length; i++) {
////			System.out.println(parts[i]);
//		Integer new_value = Integer.parseInt(jsEditor_readOnly);
//		assertEquals(new_value, result.get("getCTest"));
////		}
//        
//    }
//	@Test
//    public void testAccout3() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
////		value += codeTest;
////		System.out.println(value);
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
////		result = "10000";
////		System.out.println(result + " resultS");
////		String[] parts = result.split(";");
//		
////		 StackTraceElement[] elements = Thread.currentThread().getStackTrace();oh
//		
////		    for (int i=0;i<elements.length;i++) {
////		    	System.out.println(elements[i].toString());
////		    }
////		
////		cb.generateGsraph(classBuild);
////		BankAccount a = new BankAccount("abc123", 15000);
////        BankAccount b = new BankAccount("def456", 25000);
//		System.out.println("testAccout3");
////        new TransferContext(a, b).transfer(5000);  
////		for(int i = 0; i < parts.length; i++) {
////			System.out.println(parts[i]);
//		Integer new_value = Integer.parseInt(jsEditor_readOnly);
//		assertEquals(new_value, result.get("getCTest"));
////		}
//        
//    }
//	@Test
//    public void testTransfer_4() throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = "";
//		value += request.getParameter("komutdosyasi");
//		String jsEditor_readOnly = request.getParameter("valueForTest");
//		value += request.getParameter("codeTest");
////		value += codeTest;
////		System.out.println(value);
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		CompilerBean cb = new CompilerBean();
//		result = cb.deriveFullClassFromSource(value);
////		result = "10000";
////		System.out.println(result + " resultS");
////		String[] parts = result.split(";");
//		
////		 StackTraceElement[] elements = Thread.currentThread().getStackTrace();oh
//		
////		    for (int i=0;i<elements.length;i++) {
////		    	System.out.println(elements[i].toString());
////		    }
////		
////		cb.generateGsraph(classBuild);
////		BankAccount a = new BankAccount("abc123", 15000);
////        BankAccount b = new BankAccount("def456", 25000);
//		System.out.println("testTransfer_4");
////        new TransferContext(a, b).transfer(5000);  
////		for(int i = 0; i < parts.length; i++) {
////			System.out.println(parts[i]);
//		Integer new_value = Integer.parseInt(jsEditor_readOnly);
//		assertEquals(new_value, result.get("getCTest"));
////		}
//        
//    }

}


