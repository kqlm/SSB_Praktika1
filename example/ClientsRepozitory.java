package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepozitory implements IRepo<Clients> {
    @Override
    public void Update(Clients client) throws SQLException {
        String str = String.format("UPDATE client SET idClient = %s, name = '%s', idPhotorgafs = %s WHERE idClient = %s" ,
                client.getId(),
                client.getName(),
                client.getIdPhotorgafs().getId(),
                client.getId());
        this.executeRequest(str);

        /// add stylists
    }
    @Override
    public void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }

    @Override
    public Integer Insert(Clients client) throws SQLException {
        String str = String.format("INSERT INTO clients (name, idPhotorgafs, isDeleted) VALUES ('%s', '%s', %s)",
                client.getName(),
                client.getIdPhotorgafs().getId(),
                client.isDeleted());
        this.executeRequest(str);
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(idClient) FROM Client")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        }
    }
    @Override
    public void Delete(Clients client) throws SQLException {
        String str = String.format("UPDATE Clients SET isDeleted = true WHERE id = %s" , client.getId());
        this.executeRequest(str);
    }

    @Override
    public List<Clients> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT cl.idClient, cl.name, cl.isDeleted, cl.idPhotorgafs, st.idPhotorgafs, st.S_name, st.s_isDeleted FROM client AS cl JOIN Photorgafs AS st ON cl.IDPHOTOGRAFS = st.idPhotorgafs");

        List<Clients> clients = new ArrayList<>();
        while(rs.next()) {
            clients.add(new Clients(rs.getInt("idClient"),
                    rs.getString("name"),
                    rs.getBoolean("isDeleted"),
                    new Photorgafs(rs.getInt("idPhotorgafs"), rs.getString("F_name"), rs.getBoolean("F_isDeleted"))));
        }
        this.closeConnection(stmt);

        return clients;
    }
    @Override
    public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Sqlite\\Photosalonnn");
        if (conn==null) {
            System.out.println("Error with connection with DataBase!");
            System.exit(0);
        }
        return conn;
    }
    @Override
    public Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }
    public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
    }
}