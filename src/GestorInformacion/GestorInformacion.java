/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorInformacion;

import Entidades.Asiento;
import Entidades.Avion;
import Entidades.Cliente;
import Entidades.Hora;
import Entidades.Registro;
import Listas.Lista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author robertoarmas
 */
public abstract class GestorInformacion {

    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    ArrayList<String> lineas = new ArrayList<String>();
    Lista datos = new Lista();
    String name;
    Lista auxLista;

    public GestorInformacion(String nombre) {
        this.name = nombre;
        this.auxLista = new Lista();
        leerArchivo(name + ".txt");
        
    }

    public void separadores(String linea) {
        StringTokenizer st = new StringTokenizer(linea, "#");
        // System.out.println("Datos: ");
        int i = 0;
        final String palabras[] = new String[st.countTokens()];
        while (st.hasMoreTokens()) {
            palabras[i] = st.nextToken();
            i++;
        }
        switch (this.name) {
            case "avion":
                datos.add(new Avion(palabras[0]));
                break;
            case "asiento":
                  final Lista asiento = new Lista();
                 new GestorInformacion("avion") {
                    @Override
                    public void didGetData(Lista datos) {
                        this.auxLista = datos;
                        asiento.add(new Asiento(Integer.parseInt(palabras[0]), palabras[1], Boolean.parseBoolean(palabras[2]),Avion.findAvionByName(palabras[3], this.auxLista)));
                    }
                    
                    @Override
                    public void didFailLoad() {
                        System.out.println("No funciono");
                        this.auxLista.clear();
                    }
                    
                };
                 this.datos.add(asiento.get(0));
              
                break;
            case "cliente":
                this.datos.add(new Cliente(palabras[0],palabras[1],palabras[2],palabras[3],palabras[4]));
                break;
            case "registro":
                 final Lista registro = new Lista();

                 new GestorInformacion("cliente") {
                    @Override
                    public void didGetData(Lista datos) {
                        this.auxLista = datos;
                        final Cliente cliente = Cliente.findClienteByCedula(palabras[1], this.auxLista);
                        new GestorInformacion("horas"){

                            @Override
                            public void didGetData(Lista datos) {
                              this.auxLista = datos;
                              final Hora hora = Hora.findHoraById(Integer.parseInt(palabras[2]), this.auxLista);
                              new GestorInformacion("asiento"){

                                  @Override
                                  public void didGetData(Lista datos) {
                                      this.auxLista = datos;
                                      registro.add(new Registro(Integer.parseInt(palabras[0]),cliente,hora,Asiento.findAsientoById(Integer.parseInt(palabras[3]),this.auxLista)));
                                  }

                                  @Override
                                  public void didFailLoad() {
                                       System.out.println("No funciono");
                                        this.auxLista.clear();
                                  }
                                  
                              };
                             
                            }

                             @Override
                            public void didFailLoad() {
                             System.out.println("No funciono");
                             this.auxLista.clear();
                             }
                            
                        };
                        
                    }
                    
                    @Override
                    public void didFailLoad() {
                        System.out.println("No funciono");
                        this.auxLista.clear();
                    }
                    
                };
                 this.datos.add(registro.get(0));
               
                break;
            case "horas":
                final Lista horas = new Lista();
                 new GestorInformacion("avion") {
                    @Override
                    public void didGetData(Lista datos) {
                        this.auxLista = datos;
                        horas.add(new Hora(Integer.parseInt(palabras[0]), palabras[1], Avion.findAvionByName(palabras[2], this.auxLista)));
                    }
                    
                    @Override
                    public void didFailLoad() {
                        System.out.println("No funciono");
                        this.auxLista.clear();
                    }
                    
                };
                 this.datos.add(horas.get(0));
          break;
            default:
                break;
        }

    }

    public abstract void didGetData(Lista datos);

    public abstract void didFailLoad();

    public void leerArchivo(String nombre) {
        try {
            archivo = new File("src/datos/" + nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;

            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }

        } catch (IOException ex) {
            didFailLoad();
            System.out.println("no funciono ");
        }

        for (String x : lineas) {
            separadores(x);
        }

        didGetData(this.datos);
    }

}
