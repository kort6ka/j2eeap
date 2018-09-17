package com.test_j2eeapp.editor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;



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
	    
	    assertEquals(str1,str2);
	    
  
	    
	}
	
	@Test
	public void compileAll_WhenTypical() throws Exception {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String value = request.getParameter("komutdosyasi");
		String cls1 = "public class A{ "
				+ "public C b() {"
				+ " return new C(); "
				+ "	}"
				+ "}";
		String cls2 = "public class B{ "
				+ "public static void main (String[] args) {"
				+ " System.out.println(\"main\");"
				+ "	C cc = new C();"
				+ 	"}"
				+ "}";
//		System.out.println(value);
		String cls3 = value;
		
		CompilerBean bn = new CompilerBean();
//		String bb = CompilerBean.deriveFullClassNameFromSource(value);
		
		

		
		
		
		Map<String, Class<?>> compiled = new HashMap<String, Class<?>>();
//		for (String className : sourceCodes.keySet()) {
//			compiled.put(className, classLoader.loadClass(className));
//		}
//		Map<String, Class<?>> compiled = InMemoryJavaCompiler.newInstance().addSource("A", cls1).addSource("B", cls2).addSource("C", cls3).compileAll();

		InMemoryJavaCompiler test = InMemoryJavaCompiler.newInstance();
//		InMemoryJavaCompiler test1 = InMemoryJavaCompiler.newInstance().addSource("B", cls2);
//		InMemoryJavaCompiler test2 = InMemoryJavaCompiler.newInstance().addSource("C", cls3);
		
		
		//////////////////////////
		ArrayList<Integer> list = new ArrayList<Integer>();
		int counter2 = 0;
//		if (sourceCode == null) {
//	        return null;
//	    }		
		int index = value.indexOf("public class");
		while(index >= 0) {
		    index = value.indexOf("public class", index+1);
		    counter2++;
		}		
		int i = value.indexOf("public class");
		while(i >= 0) {
//		     System.out.println(i);
		     list.add(i);
		     i = value.indexOf("public class", i+1);
		     
		}		
		for(int ir = 0; ir < counter2 - 1; ir++) {
//			System.out.println(sourceCode.substring(list.get(ir), list.get(ir+1)));
//			System.out.println(bn.deriveFullClassNameFromSource(value.substring(list.get(ir), list.get(ir+1))) +" hi "+ value.substring(list.get(ir), list.get(ir+1)));
			test.addSource(bn.deriveFullClassNameFromSource(value.substring(list.get(ir), list.get(ir+1))), value.substring(list.get(ir), list.get(ir+1)));
		}
		test.addSource(bn.deriveFullClassNameFromSource(value.substring(list.get(counter2 - 1))), value.substring(list.get(counter2 - 1)));
//		System.out.println(bn.deriveFullClassNameFromSource(value.substring(list.get(counter2 - 1))));
		/////////////////////////
		
		
		
//		test.addSource("A", cls1);
//		test.addSource("B", cls2);
//		test.addSource("C", cls3);
		
		compiled = test.compileAll();
		
//		assertNotNull(compiled.get("A"));
//		assertNotNull(compiled.get("B"));
//		assertNotNull(compiled.get("C"));
//		
//		Class<?> aClass = compiled.get("A");
//		Class<?> bClass = compiled.get("B");
//		Object a = aClass.newInstance();
////		Object b = bClass.newInstance();
//		bClass.getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
////		assertEquals("B!", aClass.getMethod("b").invoke(a).toString());
//		assertEquals("B!", aClass.getMethod("b").invoke(a).toString());
		assertNotNull(compiled.get("A"));
		assertNotNull(compiled.get("B"));

		Class<?> aClass = compiled.get("A");
		Object a = aClass.newInstance();
		aClass.getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
		assertEquals("B!", aClass.getMethod("b").invoke(a).toString());
	}
	
//	@Test
//	public void compile_WhenTypical() throws Exception {
//		StringBuffer sourceCode = new StringBuffer();
//
////		sourceCode.append("package org.mdkt;\n");
//		sourceCode.append("public class HelloClass {\n");
//		sourceCode.append("   public static void fain (String[] args){ System.out.println( \"hello\"); }");
//		sourceCode.append("}");
////		sourceCode.append("public class B{}");
//
//		System.out.println(sourceCode.toString());
//		
//		Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("HelloClass", sourceCode.toString());
//		
//		helloClass.getDeclaredMethod("fain", new Class[] { String[].class }).invoke(null, new Object[] { null });
//		assertNotNull(helloClass);
//		Assert.assertEquals(1, helloClass.getDeclaredMethods().length);
//	}
	

	@Test
    public void testTransfer() {
        BankAccount a = new BankAccount("abc123", 15000);
        BankAccount b = new BankAccount("def456", 25000);
//        System.out.println(a.getBalance());	
        new TransferContext(a, b).transfer(5000);
        

        assertEquals((Integer)10000, a.getBalance());
        assertEquals((Integer)30000, b.getBalance());
    }
	
	@Test
    public void testTransfer_1() {
        BankAccount a = new BankAccount("abc123", 15000);
        BankAccount b = new BankAccount("def456", 25000);
//        System.out.println(a.getBalance());	
        new TransferContext(b, a).transfer(5000);
//        System.out.println(a.getBalance());	

        assertEquals((Integer)20000, a.getBalance());
        assertEquals((Integer)20000, b.getBalance());
    }

}


