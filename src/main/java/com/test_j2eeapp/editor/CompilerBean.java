package com.test_j2eeapp.editor;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.util.ClassPath.ClassFile;
import org.apache.bcel.util.SyntheticRepository;
import org.apache.bcel.verifier.exc.Utility;
import org.junit.experimental.categories.Categories;
import org.mdkt.compiler.InMemoryJavaCompiler;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.TraceClassVisitor;

import com.bluecatcode.junit.shaded.org.apache.commons.lang3.StringUtils;

import h.gv_argvlist_t;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ConstPool;
import net.openhft.compiler.CompilerUtils;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sourceforge.plantuml.SourceStringReader;
import weka.gui.visualize.plugins.GraphVizPanel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

@ManagedBean(name = "compile")
public class CompilerBean {
	
	
	final public static String PREFIX_CLASSNAME = "class ";
	final public static String PREFIX_PACKAGENAME = "package ";
	final public static String CHARSET_JAVAKEYWORDENDERS = " \n[](){}<>;,\"\\/*+-=%!&?@:";
	
	

	
	public void generateGraph(String plantUmlMarkup) throws IOException{
		
	
		
		String test = "@startuml\n"+plantUmlMarkup+"\n@enduml";
//		System.out.println(test);
		SourceStringReader s = new net.sourceforge.plantuml.SourceStringReader(test);
		FileOutputStream file = new FileOutputStream("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\images\\image.jpeg");
//		FileOutputStream file = new FileOutputStream("C:\\Users\\u1dd_fsm\\Desktop\\eclipse-workspace\\j2eeappnew\\src\\main\\webapp\\WEB-INF\\images\\image.jpeg");
		s.generateImage(file);
//		System.out.println(System.getProperty("./"));
		file.close();
	}
	public String deriveFullClassFromSource(String value) throws Exception {

		String result = "";
		int fieldsLengthForTestResults = 0;
		String classBuilder = "";
		String new_value = "";
		String[] values = null;
		StringBuilder valuesBuilder = new StringBuilder();
		StringBuilder methodForValuesBuilder = new StringBuilder();
		StringBuilder fieldsForValuesBuilder = new StringBuilder();
		StringBuilder fieldsForValuesBuilderWithConstructor = new StringBuilder();
		StringBuilder classForValuesBuilder = new StringBuilder();
		Map<String, Class<?>> compiled = new HashMap<String, Class<?>>();
		Map<String, ArrayList> saver = new HashMap<String, ArrayList>();
		InMemoryJavaCompiler test = InMemoryJavaCompiler.newInstance();		
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> testResults = new ArrayList<Integer>();
		List<String> matchList = new ArrayList<String>();
		ArrayList<Integer> list_values = new ArrayList<Integer>();
		ArrayList<Integer> list_index = new ArrayList<Integer>();
		ArrayList<String> list_values_temp = new ArrayList<String>();
		ArrayList<String> list_extensions = new ArrayList<String>();
		ArrayList<String> list_extensionsClass = new ArrayList<String>();
		ArrayList<String> valuesForDependenciesCut = new ArrayList<String>();
		
		ArrayList<Class<?>> listOfClass = new ArrayList<Class<?>>();
		
		ArrayList<String> class_field = new ArrayList<String>();
		ArrayList<String> value_name = new ArrayList<String>();
		ArrayList<String> method_name = new ArrayList<String>();
		ArrayList<Class<?>> listOfclassNames = new ArrayList<Class<?>>();
		ArrayList<Class<?>> listOfclassNamesForValues = new ArrayList<Class<?>>();
		ArrayList<Class<?>> listOfArrayListClassNames = new ArrayList<Class<?>>();
		ArrayList<String> listOfGenericClassNames = new ArrayList<String>();
		String[] splittedValues;
		
		
		//////////////////////////
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		String imports = "";
//		String sourceCode = "import java.util.ArrayList;";
		
		
		int counter2 = 0;		
		int index = value.indexOf("public class");// TODO check if only"class" is available
		
		while(index >= 0) {
			list.add(index);
		    index = value.indexOf("public class", index+1);
		    counter2++;
		}
		int index_lastSemilicon = value.indexOf(";");
		while(index_lastSemilicon >= 0) {
			list_values.add(index_lastSemilicon);
			index_lastSemilicon = value.indexOf(";", index_lastSemilicon+1);
		}

//		for(int i = 0; i < list_values.size(); i++) {
//			System.out.println(list_values.get(i));
//		}
		for(int ir = 0; ir < counter2 - 1; ir++) {
//			System.out.println(sourceCode.substring(list.get(ir), list.get(ir+1)));
			String tester = value.substring(list.get(ir), list.get(ir+1));
//			System.out.println(tester.replace("ArrayList", "java.util.ArrayList"));
//			int index_for_list = tester.indexOf("ArrayList");
//			while(index_for_list >= 0) {
//				index_for_arrayList.add(index_for_list);
//				index_for_list = tester.indexOf("ArrayList", index_for_list+1);
//				System.out.println(index_for_list);
//			}
//			
			
			list_values_temp.add((value.substring(list.get(ir), list.get(ir+1)).replace("ArrayList", "java.util.ArrayList")));
			
			test.addSource(deriveFullClassNameFromSource(value.substring(list.get(ir), list.get(ir+1))), (value.substring(list.get(ir), list.get(ir+1)).replace("ArrayList", "java.util.ArrayList")));
//			System.out.println(value.substring(list.get(ir), list.get(ir+1)));
//			String temp = value.substring(list.get(ir), list.get(ir+1));
//			System.out.println(temp);
//			System.out.println(temp.indexOf("="));
//			int counter3 = 0;
//			int index_firstEqualValue = temp.indexOf("=");
//			int index_lastSemilicon = temp.indexOf(";");
//			while(index_firstEqualValue >= 0) {
//				list_values.add(temp.substring(index_firstEqualValue, index_lastSemilicon));
//				index_firstEqualValue = temp.indexOf("=", index_firstEqualValue+1);
//				index_lastSemilicon = temp.indexOf(";", index_lastSemilicon+1);
//			    counter3++;
//			}
//			System.out.println(list);
//			System.out.println((value.substring(list.get(ir), list.get(ir+1))).replace("{", "").replace("}", ""));
//			

		}
//		System.out.println((value.substring(list.get(counter2 - 1))).replace("{", "").replace("}", ""));
		list_values_temp.add((value.substring(list.get(counter2 - 1)).replace("ArrayList", "java.util.ArrayList")));
//		System.out.println(list_values_temp);
		test.addSource(deriveFullClassNameFromSource(value.substring(list.get(counter2 - 1))), (value.substring(list.get(counter2 - 1)).replace("ArrayList", "java.util.ArrayList")));
		
//		System.out.println(bn.deriveFullClassNameFromSource(value.substring(list.get(counter2 - 1))));
//		System.out.println(value.substring(list.get(counter2 - 1)));
		/////////////////////////
		try {
			compiled = test.compileAll();
		}catch(IOException e) {
			e.printStackTrace();
		}
		

		

		
//		for(int i = 0; i < values.length; i++) {
//			list_values_temp.add(values[i]);
////			System.out.println(i);
////			listOfclassNamesForValues.add(listOfClass.get(ir));
//		}
		
		
//		System.out.println(compiled.values());
		for(int ir = 0; ir < compiled.size(); ir++) {
			listOfClass.add((Class<?>) compiled.values().toArray()[ir]);
				//building class from the scratch in the string
				
				
				
//				System.out.println(listOfClass.get(ir).getSuperclass().getName().replace("java.lang.Object", "").trim());
//				System.out.println(listOfClass.get(ir).getSuperclass().getName().replace("java.lang.Object", "").trim().isEmpty());
				if(listOfClass.get(ir).getSuperclass().getName().replace("java.lang.Object", "").trim().isEmpty()) {
					classBuilder += " \n\r"+listOfClass.get(ir).toString()+"{ ";
				}else {
					
					String extendsClassForGraph = "< extends "+listOfClass.get(ir).getSuperclass().getName().replace("java.lang.Object", "")+">";
					classBuilder += " \n\r"+listOfClass.get(ir).toString()+extendsClassForGraph+"{ ";
					list_extensions.add(listOfClass.get(ir).getSuperclass().getName().replace("java.lang.Object", ""));
					list_extensionsClass.add(listOfClass.get(ir).getSimpleName());
				}
				
				
				String cleanClass = listOfClass.get(ir).toString()+"\\{";
				//getting all the methodes of the class

				
				classForValuesBuilder.append("public "+cleanClass+"|");
				if(listOfClass.get(ir).getConstructors()[0].getParameterCount() > 0) {
					classBuilder += " \n\r"+listOfClass.get(ir).getConstructors()[0].toString().toString().replace("java.lang.", "");
				}
				
				for(int jr = 0; jr < listOfClass.get(ir).getDeclaredMethods().length; jr++) {
					String cleanClassMethod = listOfClass.get(ir).getDeclaredMethods()[jr].toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "");
//					String methodValues = value.substring(value.indexOf(cleanClassMethod+"{")+(cleanClassMethod+"{").length(), value.indexOf("}")); // TODO check if values are present
					method_name.add(cleanClassMethod);
					classBuilder += " \n"+cleanClassMethod;
					
					listOfclassNamesForValues.add(listOfClass.get(ir));
					methodForValuesBuilder.append(cleanClassMethod+"|");

					if((listOfClass.get(ir).getDeclaredMethods()[jr].getName()).contains("Test")) {
						
//						Method setNameMethod = listOfClass.get(ir).getDeclaredMethod("getBalanceTest");
//						setNameMethod.setAccessible(true);						
//						Integer x = (int) setNameMethod.invoke(listOfClass.get(ir).newInstance(), null);
//						result = x.toString();
						
//						System.out.println(result+"result");
						
						Method[] fields = listOfClass.get(ir).getDeclaredMethods();
						fieldsLengthForTestResults = fields.length;
//						System.out.println();
						for (Method field : fields) {
							  field.setAccessible(true); //Additional line
							  testResults.add((int) field.invoke(listOfClass.get(ir).newInstance(), null));//						        
						}
						
//						Method setNameMethod_1 = listOfClass.get(ir).getDeclaredMethod("getNewBalanceTest");
//						setNameMethod_1.setAccessible(true);						
//						Integer x1 = (int) setNameMethod_1.invoke(listOfClass.get(ir).newInstance(), null);
//						result1 = x1.toString();
//						System.out.println(result1+"result1");
						
					}
					
//					ClassParser cp = new ClassParser(listOfClass.get(ir).getName());
//					System.out.println(cp);
					
//					ClassPool cp = ClassPool.getDefault();
//					CtClass cc = cp.makeClass(compiled.get(0).toString());
//					
//					System.out.println(cc.getDeclaredMethods()[0]);
					
//					ClassFile cf = new ClassFile(new DataInputStream(fin));
//					CtClass evalClass = pool.makeClass(listOfClass.get(ir));

//					JavaClass javaClass = Repository.lookupClass("java.lang.String");
					
//					System.out.println(javaClass);
//					for(org.apache.bcel.classfile.Method method: javaClass.getMethods()){
//					    for(LocalVariable localVariable: method.getLocalVariableTable().getLocalVariableTable()){
//					        listOfClass.get(ir);
//					    }
//					}
//						  String b = cleanClassMethod;			
////						  System.out.println(new Throwable().getStackTrace()[i].getMethodName());
//						  for (int index1 = (new_value).indexOf(b);index1 >= 0; index1 = (new_value).indexOf(b, index1 + 1)) {
//							  System.out.println(index1);
//						       //////here you will get all the index of  "("
//						    }
//					String b = cleanClassMethod;		  
//					new_value = value.replace("public "+listOfClass.get(ir), "").replace(cleanClassMethod, "").replace("{", "").replace("}", "");
//					for (int index1 = (new_value).indexOf(b);index1 >= 0; index1 = (new_value).indexOf(b, index1 + 1)) {
//						  System.out.println(index1);
////					       //////here you will get all the index of  "("
//					    }
					
//					Pattern logEntry = Pattern.compile("\\{(.*?)\\}");
//					 String c = cleanClassMethod+"{";
//					  for (int index1 = value.indexOf(c);index1 >= 0; index1 = value.indexOf(c, index1 + 1)) {
//						  System.out.println(index1 + c);
//					       //////here you will get all the index of  "("
//					    }
						

////					System.out.println(firstCleanClassMethod+"}");
//						int firstCleanClassMethod = (value.replaceAll("\\s","")).indexOf(";}");
////						System.out.println(firstCleanClassMethod);
//					while(firstCleanClassMethod >= 0) {
//			          list_index.add(firstCleanClassMethod);
//			          firstCleanClassMethod = (value.replaceAll("\\s","")).indexOf(";}", firstCleanClassMethod+1);
//			          
//			         
//					}
//					System.out.println(firstCleanClassMethod);
//					 System.out.println(listOfArrayListClassNames);
//					 
//					 System.out.println(listOfclassNames);
					
//					Class<?> clazz = Class.forName("java.lang");	  
//					JavaClass clazz1 = Repository.lookupClass(clazz);
//					System.out.println(clazz1);
					
//					System.out.println(value.substring(value.indexOf(cleanClassMethod+"{")+(cleanClassMethod+"{").length(), value.indexOf("}")));
//					splittedValues = methodValues.split(";");
//					for(int i = 0; i < splittedValues.length; i++) {
//						System.out.println(splittedValues[i]);
//					}
						  
						  	
						  
					Method[] fields = listOfClass.get(ir).getDeclaredMethods();
					
//					System.out.println(listOfClass.get(ir).getDeclaredMethods()[jr].toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", ""));
//					System.out.println();
					fields[jr].setAccessible(true);
					for (Method field : fields) {
						  field.setAccessible(true); //Additional line
						  
//						  field.invoke(null, new Object[] { null });
//						  listOfClass.get(ir).getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
//						  System.out.println();
//						  System.out.println("Field Class: " + listOfClass.get(ir));
//						  System.out.println("Field Type: " + field.getGenericType());
//					        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
					        
//					        System.out.println();
						}
				}

				
				//getting all the fields of the class
				for(int jri = 0; jri < listOfClass.get(ir).getDeclaredFields().length; jri++) {
					Field[] fields = listOfClass.get(ir).getDeclaredFields();
					fields[jri].setAccessible(true);
					for (Field field : fields) {
						  field.setAccessible(true); //Additional line
//						  System.out.println("Field Name: " + field.getName());
//						  System.out.println("Field Type: " + field.getType());
//						  System.out.println(fields[jri].get(listOfClass.get(ir).newInstance()).toString());
//						  System.out.println(listOfClass.get(ir).getDeclaredField("a"));
//					        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
					        
//					        System.out.println();
						}

//					if(fields[jri].get(listOfClass.get(ir).newInstance()).toString().contains(listOfClass.get(ir).getDeclaredFields()[jri].getType().getName().toString()+"@")) {
////						System.out.println("new "+fields[jri].get(listOfClass.get(ir).newInstance()).toString().substring(0 ,(listOfClass.get(ir).getDeclaredFields()[jri].getType().getName().toString()).length())+"();");
//						String objectString = "new "+fields[jri].get(listOfClass.get(ir).newInstance()).toString().substring(0 ,(listOfClass.get(ir).getDeclaredFields()[jri].getType().getName().toString()).length())+"();";
//						classBuilder += " \n"+listOfClass.get(ir).getDeclaredFields()[jri].toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "") +" = " + "new "+fields[jri].get(listOfClass.get(ir).newInstance()).toString().substring(0 ,(listOfClass.get(ir).getDeclaredFields()[jri].getType().getName().toString()).length())+"();";
//					}else {
//						classBuilder += " \n"+listOfClass.get(ir).getDeclaredFields()[jri].toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "") +" = " + (fields[jri].get(listOfClass.get(ir).newInstance()).toString());
//					}
//					System.out.println((listOfClass.get(ir).getDeclaredFields()[jri]).getType().getName());
					if((listOfClass.get(ir).getDeclaredFields()[jri].getType().getName()).toString().equals("java.util.ArrayList")) {
						classBuilder += " \n"+listOfClass.get(ir).getDeclaredFields()[jri].getGenericType().toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "").replace("java.util.", "") +" "+ listOfClass.get(ir).getDeclaredFields()[jri].getName();
						
						
//						Field[] fields = listOfClass.get(ir).getDeclaredFields();
////						fields[jri].setAccessible(true);
//						for (Field field : fields) {
//							  field.setAccessible(true); //Additional line
//							  ParameterizedType stringListType =  (ParameterizedType) field.getGenericType();
//							  Class<?> stringListClass =  (Class<?>) stringListType.getActualTypeArguments()[0];
//							  System.out.println(stringListClass);
////							  System.out.println("Field Value: " + field.get(listOfClass.get(ir).newInstance()));
//							}
//						
//						System.out.println(((listOfClass.get(ir).getDeclaredFields()[jri].getGenericType().getTypeName().replace("java.util.ArrayList", ""))).substring(1,(listOfClass.get(ir).getDeclaredFields()[jri].getGenericType().getTypeName().replace("java.util.ArrayList", "").length()-1)));
						String extractLetterTypeWhereFieldIsArrayList = ((listOfClass.get(ir).getDeclaredFields()[jri].getGenericType().getTypeName().replace("java.util.ArrayList", ""))).substring(1,(listOfClass.get(ir).getDeclaredFields()[jri].getGenericType().getTypeName().replace("java.util.ArrayList", "").length()-1));
						listOfGenericClassNames.add(extractLetterTypeWhereFieldIsArrayList);
						listOfArrayListClassNames.add(listOfClass.get(ir));
//						listOfclassNames.add(extractLetterTypeWhereFieldIsArrayList);
					}else{
						classBuilder += " \n"+listOfClass.get(ir).getDeclaredFields()[jri].toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "").replace("java.util.", "").replace("[L", "").replace(";", "");
					}
					//+" = " + (fields[jri].get(listOfClass.get(ir).newInstance()).toString())
//					System.out.println("Type"+listOfClass.get(ir).getDeclaredFields()[jri].getType().getName().toString()+"Field Value: " + fields[jri].get(listOfClass.get(ir).newInstance()).toString());
					
					class_field.add(listOfClass.get(ir).getDeclaredFields()[jri].getType().toString().replace(listOfClass.get(ir).getName()+".", "").replace("java.lang.", "").replace("java.util.", ""));
					value_name.add(listOfClass.get(ir).getDeclaredFields()[jri].getName());
//					valuesForDependenciesCut.add();
					fieldsForValuesBuilder.append((listOfClass.get(ir).getDeclaredFields()[jri].toString().replace(listOfClass.get(ir).getName()+".", ""))+"|");
					fieldsForValuesBuilderWithConstructor.append(listOfClass.get(ir).getDeclaredFields()[jri].getType().getSimpleName()+"();"+"|");
					listOfclassNames.add(listOfClass.get(ir));
										
				}
			
		
			classBuilder += "\n}";
			
			
			

				

			
//			String[] values = (valuesCutterFullString).split(";");
				
			
			
		}
		
		
//		for(int i = 0; i < method_name.size(); i++) {
//			
//		}
//		
		
		classBuilder += "\n";
		
//		class_field
//		System.out.println(fieldsForValuesBuilderWithConstructor);//
		String valuesCutterFullString = value.trim().replace("\r\n", "").replaceAll(classForValuesBuilder.toString(), "").replaceAll(fieldsForValuesBuilder.toString(), "").replace("= new", "").replace("();", "");
//		System.out.println(valuesCutterFullString);
		Pattern regex_getEverythingInsideBrackets = Pattern.compile("\\{(.*?)\\}");		
		Matcher regexMatcher = regex_getEverythingInsideBrackets.matcher(valuesCutterFullString);
		while (regexMatcher.find()) {//Finds Matching Pattern in String
		       matchList.add(regexMatcher.group(1));//Fetching Group from String
		       valuesBuilder.append(regexMatcher.group(1));
		    }
		
//		Pattern regex_get = Pattern.compile("^[^\\(]+");
//		Matcher regexMatcher_get = regex_get.matcher(valuesBuilder);
//		while (regexMatcher_get.find()) {//Finds Matching Pattern in String
//		       matchList.add(regexMatcher_get.group(1));//Fetching Group from String
//		       valuesBuilder.append(regexMatcher_get.group(1));
//		    }
		
		
		System.out.println(valuesBuilder);
		
		values = valuesBuilder.toString().split(";");
		
//			for(int j = 0; j < (listOfClass.size()); j++) {
//				for(int i = 0; i < (values.length-1); i++) {
//					
////						listOfclassNamesForValues.add(listOfClass.get(j));
//						list_values_temp.add(values[i]);
//						System.out.println(i);
//					}
//		}
		
		
		for(int j = 0; j < listOfClass.size(); j++) {
			for(int i = 0; i < class_field.size(); i++) {	
//				System.out.println(listOfClass+"==="+class_field);
//				System.out.println(listOfClass.get(j).toString()).equals("class [L"+class_field.get(i)+";");
				if((listOfClass.get(j).toString()).equals(class_field.get(i))) {

						classBuilder += (listOfclassNames.get(i).getName().toString())+" --> "+'"'+value_name.get(i)+'"'+(listOfClass.get(j).getName().toString())+"\n";
					}
			}	
		}

		for(int j = 0; j < listOfGenericClassNames.size(); j++) {
			String star = "*";
			classBuilder += (listOfGenericClassNames.get(j))+" -->"+'"'+value_name.get(j)+star+'"'+((listOfArrayListClassNames.get(j).toString()).replace("class ", ""))+"\n";

		}
		for(int i = 0; i < listOfclassNames.size(); i++) {
			for(int j = 0; j < class_field.size(); j++) {
				if(class_field.get(i).equals("class [L"+listOfclassNames.get(j).getSimpleName()+";")) {
					String star = "*";
					classBuilder += (listOfclassNames.get(i).getName().toString())+" --> "+'"'+value_name.get(i)+star+'"'+(class_field.get(i)).replace("class [L", "").replace(";", "")+"\n";
				}
			}
		}
		
		
		
//		for(int ir = 0; ir < listOfClass.size(); ir++) {
//			for(int ij = 0; ij < list_values_temp.size(); ij++) {
//				listOfclassNamesForValues.add(listOfClass.get(ir));
//			}
//		}
		
		
//		String valuesCutterFullString = value.replace("\r\n", "").replaceAll(classForValuesBuilder.toString(), "");
//		Pattern regex = Pattern.compile("\\{(.*?)\\}");
//		Matcher regexMatcher = regex.matcher(valuesCutterFullString);
//		while (regexMatcher.find()) {//Finds Matching Pattern in String
//		       matchList.add(regexMatcher.group(1));//Fetching Group from String
//		       valuesBuilder.append(regexMatcher.group(1));
//		    }
//		String[] values = valuesBuilder.toString().split(";");
//		
//		for(int i = 0; i < values.length; i++) {
//			list_values_temp.add(values[i]);
////			System.out.println(i);
////			listOfclassNamesForValues.add(listOfClass.get(ir));
//		}
		
		

		
		
		for(int j = 0; j < listOfClass.size(); j++) {
			for(int i = 0; i < values.length; i++) {		
//				System.out.println((values[i]).contains(listOfClass.get(j).getSimpleName()));
				
				if((values[i]).contains(listOfClass.get(j).getSimpleName())) {
//					System.out.println((values[i])+"----"+(listOfClass.get(j).getSimpleName()));
					for(int k = 0; k < list_values_temp.size(); k++) {
//						System.out.println((values[i])+"----"+(list_values_temp.get(k)));
//						System.out.println((list_values_temp.get(k)).indexOf(values[i]));
//						System.out.println((list_values_temp.get(k)).replaceAll("\n\r", "")+"----"+((values[i]+";").trim()));
						
						if((list_values_temp.get(k)).contains((values[i]+";").trim())) {
							System.out.println(values[i]);
//							classBuilder += ((list_values_temp.get(k).substring(0,list_values_temp.get(k).indexOf("{")).replace("public class ", "").replace("extends ", "")+" ..> "+(listOfClass.get(j).getSimpleName())))+"\n";
							if(list_extensions.size() > 0) {
								for(int m = 0; m <= list_extensions.size()/2; m++) {
									
									classBuilder += ((list_values_temp.get(k).substring(0,list_values_temp.get(k).indexOf("{")).replace("public class ", "").replace("extends "+list_extensions.get(m), "")+" ..> "+(listOfClass.get(j).getSimpleName())))+"\n";
								}
							}else {
								classBuilder += ((list_values_temp.get(k).substring(0,list_values_temp.get(k).indexOf("{")).replace("public class ", "")+" ..> "+(listOfClass.get(j).getSimpleName())))+"\n";
							}

						}
						
					}
					
//					
////					System.out.println((list_values_temp.get(i))+"-----"+(listOfClass.get(j).getSimpleName()));
////							System.out.println(listOfclassNamesForValues);
////							System.out.println(list_values_temp);
////							System.out.println(listOfClass);
////							System.out.println(listOfClass.get(j).getSimpleName());
////							classBuilder += list_values_temp.get(i)+" ..> "+((listOfClass.get(j))).toString().replace("class ", "")+"\n";
////						classBuilder += (listOfclassNames.get(i).getName().toString())+" ..> "+'"'+value_name.get(i)+'"'+(listOfClass.get(j).getName().toString())+"\n";
////						classBuilder += (listOfclassNames.get(i).getName().toString())+" --> "+'"'+value_name.get(i)+'"'+(listOfClass.get(j).getName().toString())+"\n";
					}
//				if(list_values_temp.get(j).contains(values[i])) {
//					System.out.println(j);
//					A a1a2 = new A();
//					System.out.println(list_values_temp.get(j));
//				}
			}	
		}
		
//		System.out.println(list_extensions);
//		System.out.println(list_extensionsClass);
//		System.out.println(valuesBuilder);
//		for(int i = 0; i < values.length; i++) {
//			System.out.println(values[i]);
//		}
//		for(int j = 0; j < list_extensions.size(); j++) {
//		System.out.println(list_extensions);
//		System.out.println(listOfClass);
		if(list_extensions.size() > 0) {
			for(int i = 0; i < list_extensions.size(); i++) {
//				System.out.println(list_extensions.get(i)+" --|> "+(list_extensionsClass.get(i))+"\n");
				classBuilder += list_extensions.get(i)+" <|-- "+(list_extensionsClass.get(i))+"\n";
			}
		}
			
//		}
//		for(int i = 0; i < values.length; i++) {
//			System.out.println(values[i]+"----"+listOfClass.get);
//			if(values[i].contains(listOfClass.get(i).getName().toString())) {
//				System.out.println(values[i] +"--"+ (listOfClass.get(i).getName().toString()));
//			}
			
//		}
			
//			System.out.println(fieldsLengthForTestResults);
//			System.out.println(testResults.size());
			
//		if() {
			for(int i = 0; i < (testResults.size()/fieldsLengthForTestResults); i++) {
//			System.out.println();
				result += testResults.get(i)+";";
			}
//		}

		System.out.println(classBuilder);

		generateGraph(classBuilder);
		return result;
	}
	/**
	 * @return e.g. "com.dreamspacepresident.TestClass" if the source's first root level "class" (I'm talking about {}
	 * hierarchy.) is named "TestClass", and if the "package" name is "com.dreamspacepresident". Null is returned if
	 * sourceCode is null or does not provide a class name. (Mind that the parsing is done in a quite crappy way.)
	 */
	public String deriveFullClassNameFromSource(String sourceCode) {

	    if (sourceCode == null) {
	        return null;
	    }
	    final int firstBr = sourceCode.indexOf('{');
	    if (firstBr >= 0) {
	        // DETERMINE CLASS NAME
	        final int firstClass = sourceCode.indexOf(PREFIX_CLASSNAME);
	        if (firstClass < firstBr) {
	            String className = sourceCode.substring(firstClass + PREFIX_CLASSNAME.length(), firstBr).trim();
	            final int classNameEnd = indexOfAnyOfThese(className, CHARSET_JAVAKEYWORDENDERS);
	            if (classNameEnd >= 0) {
	                className = className.substring(0, classNameEnd);
	                
	            }
	            if (!className.isEmpty()) {
	                // DETERMINE PACKAGE NAME
	                String packageName = null;
	                final int firstPackage = sourceCode.indexOf(PREFIX_PACKAGENAME);
	                if (firstPackage >= 0 && firstPackage < firstBr && firstPackage < firstClass) {
	                    packageName = sourceCode.substring(firstPackage + PREFIX_PACKAGENAME.length(), firstBr).trim();
	                    final int packageNameEnd = indexOfAnyOfThese(packageName, CHARSET_JAVAKEYWORDENDERS);
	                    if (packageNameEnd >= 0) {
	                        packageName = packageName.substring(0, packageNameEnd);
	                    }
	                }
	                return (packageName != null && !packageName.isEmpty() ? packageName + "." : "") + className;
	            }
	        }
	    }
	    return null;
	}


	/**
	 * Looks for the first occurrence of ANY of the given characters, which is easier than using a bunch of
	 * String.indexOf() calls.
	 *
	 * @return -1 if not found, otherwise the String index of the first hit
	 */
	public static int indexOfAnyOfThese(final String text, final String characters) {

	    if (text != null && !text.isEmpty() && characters != null && !characters.isEmpty()) {
	        final int lenT = text.length();
	        final int lenC = characters.length();
	        for (int i = 0; i < lenT; i++) {
	            final char c = text.charAt(i);
	            for (int ii = 0; ii < lenC; ii++) {
	                if (c == characters.charAt(ii)) {
	                    return i;
	                }
	            }
	        }
	    }
	    return -1;
	}
}
