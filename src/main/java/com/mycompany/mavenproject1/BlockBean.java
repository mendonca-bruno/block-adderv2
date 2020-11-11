/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.Serializable;
import java.util.concurrent.Semaphore;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Bruno
 */
@Named
@ViewScoped
public class BlockBean implements Serializable{
    private String nome;
    private Gerenciador gerenciador = Gerenciador.getInstance();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    private Aluno criaAluno(String nome){
        Aluno aluno = new Aluno(0, nome, 0);
        return aluno;
    }
    
    public void colocaFila(){
        FacesContext context = FacesContext.getCurrentInstance();
        Aluno novoAluno = criaAluno(nome);
        InsertThread novaThread = gerenciador.criaThread(novoAluno);
        novaThread.start();
        context.addMessage(null, new FacesMessage("Sucesso",  "Seu bloco foi adicionado") );
    }
}
