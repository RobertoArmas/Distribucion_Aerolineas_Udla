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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
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
    BufferedWriter bw = null;
    ArrayList<String> lineas = new ArrayList<String>();
    Lista datos = new Lista();
    String name;
    Lista auxLista;

    public GestorInformacion(String nombre) {
        this.name = nombre;
        this.auxLista = new Lista();
        //leerArchivo(name + ".txt");
        loadTable(name);

    }
    
    public void loadTable(String nombre){
        switch(name){
            case "cliente":
                try{
                    didGetData(Cliente.getAllClientes());
                }catch(SQLException ex){
                    didFailLoad(ex.toString());
                }
                break;
            case "horas":
                try{
                    didGetData(Hora.getAllHoras());
                }catch(SQLException ex){
                    didFailLoad(ex.toString());
                }
                  break;
        }
        
    }

    public GestorInformacion(String nombre, String option, Lista datos) {
        this.name = nombre;
        this.auxLista = new Lista();
        switch (option) {
            case "r":
                leerArchivo(name + ".txt");
                break;
            case "w":
                escribirArchivo(name + ".txt", datos);
                break;
            default:
                break;
        }
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
                        asiento.add(new Asiento(Integer.parseInt(palabras[0]), palabras[1], Boolean.parseBoolean(palabras[2]), Avion.findAvionByName(palabras[3], this.auxLista)));
                    }

                    @Override
                    public void didFailLoad(String message) {
                        System.out.println("No funciono");
                        this.auxLista.clear();
                    }

                };
                this.datos.add(asiento.get(0));

                break;
            case "cliente":
                this.datos.add(new Cliente(palabras[0], palabras[1], palabras[2], palabras[3], palabras[4]));
                break;
            case "registro":
                final Lista registro = new Lista();

                new GestorInformacion("cliente") {
                    @Override
                    public void didGetData(Lista datos) {
                        this.auxLista = datos;
                        final Cliente cliente = Cliente.findClienteByCedula(palabras[1], this.auxLista);
                        new GestorInformacion("horas") {

                            @Override
                            public void didGetData(Lista datos) {
                                this.auxLista = datos;
                                final Hora hora = Hora.findHoraById(Integer.parseInt(palabras[2]), this.auxLista);
                                new GestorInformacion("asiento") {

                                    @Override
                                    public void didGetData(Lista datos) {
                                        this.auxLista = datos;
                                        registro.add(new Registro(Integer.parseInt(palabras[0]), cliente, hora, Asiento.findAsientoById(Integer.parseInt(palabras[3]), this.auxLista),palabras[4]));
                                    }

                                    @Override
                                    public void didFailLoad(String message) {
                                        System.out.println("No funciono");
                                        this.auxLista.clear();
                                    }

                                };

                            }

                            @Override
                            public void didFailLoad(String message) {
                                System.out.println("No funciono");
                                this.auxLista.clear();
                            }

                        };

                    }

                    @Override
                    public void didFailLoad(String message) {
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
                    public void didFailLoad(String message) {
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

    public abstract void didFailLoad(String message);

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

            for (String x : lineas) {
                separadores(x);
            }
            br.close();
           

        } catch (IOException ex) {
            didFailLoad(ex.toString());
            System.out.println("no funciono ");
        }
        
         didGetData(this.datos);

    }

    public void escribirArchivo(String name, Lista datos) {
        try {
            this.archivo = new File("src/datos/" + name);
            switch (this.name) {
                case "asiento":
                    String datosAsiento = "";
                    for (int i = 0; i < datos.size(); i++) {
                        if (i != datos.size() - 1) {
                            datosAsiento += String.valueOf(((Asiento) datos.get(i)).getId()) + "#" + ((Asiento) datos.get(i)).getName() + "#" + String.valueOf(((Asiento) datos.get(i)).getDisponible()) + "#" + ((Asiento) datos.get(i)).getAvion().getNombre() + "\n";
                        } else {

                            datosAsiento += String.valueOf(((Asiento) datos.get(i)).getId())+ "#" + ((Asiento) datos.get(i)).getName() + "#" + String.valueOf(((Asiento) datos.get(i)).getDisponible()) + "#" + ((Asiento) datos.get(i)).getAvion().getNombre();

                        }
                    }
                    bw = new BufferedWriter(new FileWriter(this.archivo));
                    bw.write(datosAsiento);
                    bw.close();
                    didGetData(new Lista());
                    break;
                case "avion":
                    break;
                case "cliente":
                    String datosCliente = "";
                    for (int i = 0; i < datos.size(); i++) {
                        if (i != datos.size() - 1) {
                            datosCliente += ((Cliente) datos.get(i)).getCedula() + "#" + ((Cliente) datos.get(i)).getName() + "#" + ((Cliente) datos.get(i)).getApellido() + "#" + ((Cliente) datos.get(i)).getTelefono() + "#" + ((Cliente) datos.get(i)).getDireccion() + "\n";
                        } else {

                            datosCliente += ((Cliente) datos.get(i)).getCedula() + "#" + ((Cliente) datos.get(i)).getName() + "#" + ((Cliente) datos.get(i)).getApellido() + "#" + ((Cliente) datos.get(i)).getTelefono() + "#" + ((Cliente) datos.get(i)).getDireccion();

                        }
                    }
                    bw = new BufferedWriter(new FileWriter(this.archivo));
                    bw.write(datosCliente);
                    bw.close();
                    didGetData(null);
                    break;
                case "horas":
                    break;
                case "registro":
                    String datosRegistro = "";
                    for (int i = 0; i < datos.size(); i++) {
                        if (i != datos.size() - 1) {
                            datosRegistro += ((Registro) datos.get(i)).getId() + "#" + ((Registro) datos.get(i)).getCliente().getCedula() + "#" + ((Registro) datos.get(i)).getHora().getId() + "#" + ((Registro) datos.get(i)).getAsiento().getId() +"#"+ ((Registro)datos.get(i)).getStatus()+ "\n";
                        } else {

                            datosRegistro += ((Registro) datos.get(i)).getId() + "#" + ((Registro) datos.get(i)).getCliente().getCedula() + "#" + ((Registro) datos.get(i)).getHora().getId() + "#" + ((Registro) datos.get(i)).getAsiento().getId() +"#"+ ((Registro)datos.get(i)).getStatus();

                        }
                    }
                    bw = new BufferedWriter(new FileWriter(this.archivo));
                    bw.write(datosRegistro);
                    bw.close();
                    didGetData(null);
                    break;
                default:
                    break;
            }

        } catch (IOException ex) {
            didFailLoad(ex.toString());
        }
    }

}
