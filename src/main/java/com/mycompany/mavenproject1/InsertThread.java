/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno
 */
public class InsertThread extends Thread{
    Semaphore sem;
    Queue<Aluno> fila;
    Counter contador;
    Aluno aluno;
    
    public InsertThread(Semaphore sem, Counter contador, Aluno aluno){
        this.sem = sem;
        fila = new LinkedList<>();
        this.contador = contador;
        this.aluno = aluno;
    }
    
    public Aluno checaContador(Aluno novoAluno, Counter contador){
        Aluno nAluno = novoAluno;
        if(contador.getContador2()==0){
            nAluno.setId(contador.getContador2()+1);
            nAluno.setAnterior(-1);
        }else{
            nAluno.setId(contador.getContador2()+1);
            nAluno.setAnterior(contador.getContador2());
        }
        
        return nAluno;
    }
    
    private void inserirBloco() throws InterruptedException{
        sem.acquire();
        Aluno aluno1 = fila.element();
        Client c = Client.create();
        Counter cont = new Counter();
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/alunos/"+ cont.getContador() +  ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(aluno1));
        fila.remove();
        sem.release();
    }
    
    private void insereFila() throws InterruptedException{
        sem.acquire();
        Aluno novoAluno = checaContador(aluno, contador);
        fila.add(novoAluno);
        sem.release();
                
    }
    
    @Override
    public void run(){
        try {
            insereFila();
            inserirBloco();
        } catch (InterruptedException ex) {
            Logger.getLogger(InsertThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
