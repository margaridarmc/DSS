package DAO;


import BLogic.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static DAO.Connect.connect;

public class MediaDAO implements DAO<Media> {

    private Connection con;

    private MediaDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Media get(String id) {
        return null;
    }

    public Media get(int id){
        try {
            con = connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Media where idMedia=?");
                pStm.setInt(1, id);
                ResultSet rs = pStm.executeQuery();
                int idMedia=0;
                String nome=null;
                String cat=null;
                String link=null;
                String artista=null;
                if (rs.next()) {
                    idMedia = rs.getInt("idMedia");
                    nome = rs.getString("nome");
                    cat = rs.getString("cat");
                    link = rs.getString("nome");
                    artista = rs.getString("artista");
                }
                return new Media(idMedia,nome,cat,link,artista);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            Connect.close(con);
        }

        return null;
    }

    public List<Media> getAll () {
        List<Media> medias = new ArrayList<>();
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("select * from Utilizador");
                ResultSet rs = pStm.executeQuery();
                while(rs.next()) {
                    medias.add(new Media(rs.getInt("idMedia"),rs.getString("nome"),rs.getString("cat"),rs.getString("link"),rs.getString("artista")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
        return medias;
    }

    public void save (Media t) {
        try {
            con = connect();
            if(con != null) {
                PreparedStatement pStm = con.prepareStatement("insert into Media(idMedia,nome,cat,link,artista) values (?,?,?,?,?) ");
                pStm.setInt(1, t.getIdMedia());
                pStm.setString(2, t.getNome());
                pStm.setString(3, t.getCat());
                pStm.setString(4, t.getLink());
                pStm.setString(5, t.getArtista());
                pStm.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }

    }

    public void update (Media t){

    }
    public void delete (Media t){
        try {
            con = Connect.connect();
            if (con != null) {
                PreparedStatement pStm = con.prepareStatement("delete from Media where idMedia=?");
                pStm.setInt(1,t.getIdMedia());
                pStm.execute();
            }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }
    }
}

