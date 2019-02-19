package com.test_j2eeapp.editor;

import java.io.IOException;

public class BankAccount {

    private String id;
    private Integer balance;
    CompilerBean cb = new CompilerBean();

    public BankAccount(String id, Integer balance) {
        this.id = id;
        this.balance = balance;
    }


    public void increase(Integer money) {
        this.balance += money;
    }

    public void decrease(Integer money) {
        this.balance -= money;
    }

    public Integer getBalance() throws IOException {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
//  		    for (int i=0;i<elements.length;i++) {
        		cb.generateSeg(elements[1].toString().replace("com.test_j2eeapp.editor.", "").replace("(BankAccount.java:26)", ""), elements[2].toString().replace("com.test_j2eeapp.editor.", "").replace("(EditorBeanTest.java:50)", ""));
//  		    	System.out.println(elements[1].toString().replace("com.test_j2eeapp.editor.", ""));
//  		    	System.out.println(elements[2].toString().replace("com.test_j2eeapp.editor.", ""));
//  		    }
        return this.balance;
        
    }

    public String getId() {
        return this.id;
    }

}