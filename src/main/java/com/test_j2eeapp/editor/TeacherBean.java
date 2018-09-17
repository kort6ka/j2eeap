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

import com.google.zxing.Writer;

@ManagedBean(name = "teacher")
public class TeacherBean {
	private String task;
	private String task_name;
	public void getTask() throws ScriptException, IOException {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		task = request.getParameter("task");
		task_name = request.getParameter("task_name"); 
        createTask(task, task_name);
	}
	public void goBack() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("./");
	}
	public void createTask(String task, String task_name) {
		System.out.println(task_name);	
		System.out.println(task);		
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
	
