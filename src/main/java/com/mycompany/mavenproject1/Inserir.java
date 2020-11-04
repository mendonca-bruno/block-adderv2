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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno
 */
public class Inserir {
    Queue<Aluno> fila;
    Semaphore semaforo;
    int aux;
    
    public Inserir(Semaphore sem){
        this.fila = new LinkedList<>();
        this.semaforo = sem;
        aux = 0;
    }
    
   /* public void insereBloco(String nome){
        FacesContext context = FacesContext.getCurrentInstance();
    
        Aluno aluno = new Aluno(0, nome, 0);
        Counter contador = new Counter();        
        Client c = Client.create();
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/alunos/"+ contador.getContador() +  ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(aluno));

        context.addMessage(null, new FacesMessage("Sucesso",  "Seu bloco foi adicionado, " + response) );
    }
    
    public String insere(Aluno novoAluno, Counter contadorNovo){
        
        Aluno aluno = novoAluno;
        Counter contador = contadorNovo;
        Client c = Client.create();
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/alunos/"+ contador.getContador() +  ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(aluno));
        return response;
        
    }*/
    
    public Aluno checaContador(Aluno novoAluno, Counter contador){
        Aluno aluno = novoAluno;
        if(contador.getContador2()==0){
            aluno.setId(contador.getContador2());
        }else{
            aluno.setId(contador.getContador2()+1);
        }
        aluno.setAnterior(contador.getContador2());
        
        return aluno;
    }
    
    public synchronized void adicionaFila(Aluno aluno) throws InterruptedException{
        semaforo.acquire();
        fila.add(aluno);
        semaforo.release();
    }
    
    public synchronized int inserirBloco(Counter contadorNovo) throws InterruptedException{
        semaforo.acquire();
        Aluno aluno1 = fila.element();
        Counter contador = contadorNovo;
        Aluno aluno = checaContador(aluno1, contador);
        Client c = Client.create();
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/alunos/"+ contador.getContador() +  ".json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(aluno));
        fila.remove();
        semaforo.release();
        return 1;
    }
}
