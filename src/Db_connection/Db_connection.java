package Db_connection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Marco Aguglia on 07/12/2016.
 */
public class Db_connection {

    private static Connection db;       // La connessione col Database
    private static boolean connesso;    // Flag che indica se la connessione e' attiva o meno
    private static Db_connection instance; //istanza statica della classe

    public static Db_connection getInstance() {
        if (instance == null)
            instance = new Db_connection();
        if (!connesso)
            connetti("fba2019", "root", "17041978");
        return instance;
    }

    // Apre la connessione con il Database
    public static boolean connetti(String nomeDB, String nomeUtente, String pwdUtente) {

        connesso = false;
        try {

            // Carico il driver JDBC per la connessione con il database MySQL

            //   Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + nomeDB + "?user=" + nomeUtente + "&password=" + pwdUtente + "&serverTimezone=Europe/Amsterdam&allowLoadLocalInfile=true");
            //  db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + nomeDB + "?user=" + nomeUtente + "&password=" + pwdUtente+"&useSSL=false");

            connesso = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connesso;

    }
    // Esegue una query di selezione dati sul Database
    // query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
    // colonne: il numero di colonne di cui sara' composta la tupla del risultato
    // ritorna un Vector contenente tutte le tuple del risultato

    public ArrayList<String[]> eseguiQuery(String query) {
        ArrayList<String[]> v = null;
        String[] record;
        int colonne = 0;
        try {
            Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
            ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
            v = new ArrayList<String[]>();


            ResultSetMetaData rsmd = rs.getMetaData();
            colonne = rsmd.getColumnCount();

            while (rs.next()) {   // Creo il vettore risultato scorrendo tutto il ResultSet
                record = new String[colonne];
                for (int i = 0; i < colonne; i++) record[i] = rs.getString(i + 1);
                v.add((String[]) record.clone());
            }
            rs.close();     // Chiudo il ResultSet
            stmt.close();   // Chiudo lo Statement
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }


    // Esegue una query di aggiornamento sul Database
    // query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
    // ritorna TRUE se l'esecuzione e' adata a buon fine, FALSE se c'e' stata un'eccezione


    public int eseguiAggiornamento(String query) {

        int risultato = 1;
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(query);
            risultato = 0;
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            risultato = 1;
        }
        return risultato;
    }


    // Chiude la connessione con il Database
    public void disconnetti() {
        try {
            db.close();
            connesso = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnesso() {
        return connesso;
    }   // Ritorna TRUE se la connessione con il Database e' attiva

}
