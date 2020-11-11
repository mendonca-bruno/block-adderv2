/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Bruno
 */
public class Gerenciador {
    public Semaphore semaforo;
    public Counter contador;
    private static Gerenciador instancia = null;
    
    private Gerenciador(){
        semaforo = new Semaphore(1);
        contador = new Counter();
    }
    
    public static Gerenciador getInstance(){
        if(instancia == null) instancia = new Gerenciador();
        return instancia;
    }
    
    public InsertThread criaThread(Aluno aluno){
        InsertThread thread = new InsertThread(semaforo, contador,aluno);
        return thread;
    }
}
