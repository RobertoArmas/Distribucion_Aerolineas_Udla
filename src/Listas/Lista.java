/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import GestorInformacion.DBManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author robertoarmas
 */
public class Lista {
    private Nodo inicio;
    private Nodo fin;
    
    public Lista(){
        this.inicio = null;
        this.fin = null;
      
    }
    
    public void add(Object element){
        Nodo nuevo = new Nodo(element);
        if(this.inicio == null){
            this.inicio = nuevo;
            this.fin = this.inicio;
            return;
        }
        this.fin.setSiguiente(nuevo);
        this.fin = nuevo;
    }
    
    public int size(){
        int size = 0;
        Nodo aux = this.inicio;
        while(aux != null){
            size++;
            aux = aux.getSiguiente();
        }
        return size;
    }
    
    public Object get(int index){
        Nodo aux = this.inicio;
        for(int i=0;i<=index;i++){
            if(i == index){
                return aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
     public Nodo getNode(int index){
        Nodo aux = this.inicio;
        for(int i=0;i<=index;i++){
            if(i == index){
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public void clear(){
        this.inicio = null;
        this.fin = null;
    }

}
