/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class Counter {
    private int contador = 0;
    
    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public int getContador(){     
        //cria um cliente com a configuração padrão do Jersey;
        Client c = Client.create();
        
        //criar um recurso web onde será configurado o 
        //endereço de destino no seu construtor, 
        //no atributo “type” é o tipo de arquivo de comunicação, 
        //no caso é um JSON e no atributo “post” está dizendo que 
        //a comunicação será afeito através de um HTTP POST passando 
        //o tipo e o valor no construtor do método que será enviado pelo webservice.
        
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/Counter.json");
        String response = wr.get(String.class);
        Gson gson = new Gson();
        if (response.equals("null")){
            inicializacontador();
        } else
        {
            this.contador = gson.fromJson(response, Integer.class);
        }
        contador++;        
        response = wr.type("application/json").put(String.class, gson.toJson(contador));
        System.out.println(response);  
        return contador;
    }
    
    public int getContador2(){
        Client c = Client.create();
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/Counter.json");
        String response = wr.get(String.class);
        Gson gson = new Gson();
        if (response.equals("null")){
            inicializacontador();
        } else
        {
            this.contador = gson.fromJson(response, Integer.class);
        }
        return contador;
    }
    
    public void inicializacontador()
    {
        Client c = Client.create();      
        
        WebResource wr = c.resource("https://sisd-3361a.firebaseio.com/Counter.json");
        Gson gson = new Gson();
        String response = wr.type("application/json").put(String.class, gson.toJson(contador));
        System.out.println(response);  
    }
    



}
