package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhotograsfRepozitory implements IRepo<Photorgafs> {
    @Override
    public void Update(Photorgafs photorgafs) throws SQLException {
        String str = String.format("UPDATE stylists SET idStylists = %s, S_name = '%s' WHERE idClient = %s" ,
                photorgafs.getId(),
                photorgafs.getName(),
                photorgafs.getId());
        this.executeRequest(str);
    }
    @Override
    public Integer Insert(Photorgafs photorgaf) throws SQLException {
        String str = String.format("INSERT INTO stylists (name, isDeleted) VALUES ('%s', %s)",
                photorgaf.getName(),
                photorgaf.getIsDeleted());
        this.executeRequest(str);
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(idPhotorgafs) FROM Stylists")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        }
    }
    @Override
    public void Delete(Photorgafs photorgafs) throws SQLException {
        String str = String.format("UPDATE Photorgafs SET isDeleted = true WHERE id = %s" , photorgafs.getId());
        this.executeRequest(str);
    }
    @Override
    public void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }

    @Override
    public List<Photorgafs> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT st.idPhotorgafs, st.F_name, st.f_isDeleted  FROM photorgafs AS st");

        List<Photorgafs> photorgafs = new ArrayList<>();
        while(rs.next()) {
            Photorgafs tmpStylist = new Photorgafs(rs.getInt("idPhotorgafs"),
                    rs.getString("F_name"), rs.getBoolean("f_isDeleted"));
            photorgafs.add(tmpStylist);
        }
        this.closeConnection(stmt);

        return photorgafs;
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