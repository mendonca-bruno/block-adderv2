/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
/**
 *
 * @author Bruno
 */
public class Aluno {
    private int id;
    private String nome;
    private int anterior;
    
    public Aluno(int id, String nome, int anterior){
        this.id = id;
        this.nome = nome;
        this.anterior = anterior;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnterior() {
        return anterior;
    }

    public void setAnterior(int anterior) {
        this.anterior = anterior;
    }
    
    
}
