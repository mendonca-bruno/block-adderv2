/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class Main implements Serializable {

   private String nome;

public String getNome() {
    return nome;
}

public void setNome(String message) {
    this.nome = message;
}

public void insereBloco() {
    FacesContext context = FacesContext.getCurrentInstance();
    
    Aluno aluno = new Aluno(0, nome, 0);
    Counter contador = new Counter();        
    Client c = Client.create();
    WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/alunos/"+ contador.getContador() +  ".json");
    Gson gson = new Gson();
    String response = wr.type("application/json").put(String.class, gson.toJson(aluno));
    
    context.addMessage(null, new FacesMessage("Sucesso",  "Seu bloco foi adicionado, " + response) );
}

}
