package com.test_j2eeapp.editor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
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

import com.bluecatcode.junit.shaded.org.apache.commons.lang3.StringUtils;

import net.openhft.compiler.CompilerUtils;

@ManagedBean(name = "compile")
public class CompilerBean {
	
	
	final public static String PREFIX_CLASSNAME = "class ";
	final public static String PREFIX_PACKAGENAME = "package ";
	final public static String CHARSET_JAVAKEYWORDENDERS = " \n[](){}<>;,\"\\/*+-=%!&?@:";

	
	
//	public static String deriveFullClassFromSource(final String sourceCode) {
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		int counter2 = 0;
//		if (sourceCode == null) {
//	        return null;
//	    }		
//		int index = sourceCode.indexOf("class");
//		while(index >= 0) {
//		    index = sourceCode.indexOf("class", index+1);
//		    counter2++;
//		}		
//		int i = sourceCode.indexOf("class");
//		while(i >= 0) {
////		     System.out.println(i);
//		     list.add(i);
//		     i = sourceCode.indexOf("class", i+1);
//		     
//		}		
//		for(int ir = 0; ir < counter2 - 1; ir++) {
////			System.out.println(sourceCode.substring(list.get(ir), list.get(ir+1)));
//			System.out.println(deriveFullClassNameFromSource(sourceCode.substring(list.get(ir), list.get(ir+1))));
//			
//		}
//		System.out.println(deriveFullClassNameFromSource(sourceCode.substring(list.get(counter2 - 1))));
////		System.out.println(sourceCode.substring(list.get(counter2 - 1)));
////		
//		
//		
////		System.out.println(StringUtils.ordinalIndexOf("Java Language", "a", 2));
//		
////		final int firstBr = sourceCode.indexOf('{');
////		final int lastBr = sourceCode.indexOf("}");
////		final int lastBrOfNestedClass = sourceCode.indexOf(PREFIX_CLASSNAME);
//		
//		
////		System.out.println(firstBr);
////		System.out.println(lastBr);
////		System.out.println(lastBrOfNestedClass);
////		System.out.println(sourceCode.substring(7, 46));
//
//		return sourceCode;
//		
//	}
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
	public int indexOfAnyOfThese(final String text, final String characters) {

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
	
	
	public void compile1(String value) throws Exception {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String value = request.getParameter("komutdosyasi"); 
//		String value = "";
		 String className = "mypackage.MyClass";
		 String javaCode = "package mypackage;\n" +
		                  "public class MyClass {\n" +
		                  "    public static void main(String[] args) {\n"+
		                  
		                  "        System.out.println("+value+");" +
		                  "    }\n"
		                  +"} ";
//		 System.out.println(javaCode);
		 Class<?> aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
		 aClass.newInstance();
//		 Class<?> aClass = classLoader.loadClass(className);
		 aClass.getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
		 
		}
	
	
	private static final String CLASS_NAME = "MyClass";
	
	public void compile(String value) throws Exception {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        String code = value;
        
        code.replaceAll("\r\n", "");
        System.out.println(code);
        SourceCode sourceCode1 = new SourceCode(CLASS_NAME, code);

        final CompiledCode compiledCode = new CompiledCode(CLASS_NAME);

        final ClassLoader classLoader = new ClassLoader(ClassLoader.getSystemClassLoader()) {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                byte[] byteCode = compiledCode.getByteCode();
                return defineClass(name, byteCode, 0, byteCode.length);
            }
        };

        JavaFileManager fileManager = new ForwardingJavaFileManager<StandardJavaFileManager>(compiler.getStandardFileManager(null, null, null)) {
            @Override
            public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
                return compiledCode;
            }

            @Override
            public ClassLoader getClassLoader(JavaFileManager.Location location) {
                return classLoader;
            }
        };

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singletonList(sourceCode1));

        if (task.call()) {
            Class<?> clazz = classLoader.loadClass(CLASS_NAME);
            clazz.getDeclaredMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { null });
        }
	    
	}

	
}




class SourceCode extends SimpleJavaFileObject {
    private String code;

    SourceCode(String className, String code) throws Exception {
        super(URI.create("string:///" + className.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }
}

/**
 * 
 */
class CompiledCode extends SimpleJavaFileObject {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    CompiledCode(String className) throws Exception {
        super(new URI(className), JavaFileObject.Kind.CLASS);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return baos;
    }

    byte[] getByteCode() {
        return baos.toByteArray();
    }
}

