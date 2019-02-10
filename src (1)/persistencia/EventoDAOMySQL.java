/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.Evento;

/**
 *
 * @author Paula
 */
public class EventoDAOMySQL implements EventoDAOInterface{
    
    private static final String INSERT_EVENTO = "INSERT INTO evento VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_RESPONSAVEL = "SELECT ID_usuario FROM usuario WHERE email = ?";
    private static final String PESQUISA_EVENTO = "select evento.nome, evento.horario, evento.endereco, evento.tipo, evento.hora from evento inner join usuario on usuario.ID_usuario = evento.ID_usuario where usuario.nome like ? or usuario.email like ? or evento.nome like ? or evento.endereco like ? or evento.tipo like ?";
    private static final String SELECT_EVENTOS = "select * from evento";
    private static final String SELECT_EVENTO = "select nome, horario, endereco, tipo, hora from evento where nome = ?";
    
    private int numeroId;
    
    public void cadastrar(Evento evento, String email) {
        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_EVENTO);
                PreparedStatement ps2 = connection.prepareStatement(SELECT_RESPONSAVEL);
        )
        {
            ps2.setString(1, email);
            ResultSet rs = ps2.executeQuery();
            
            int id_usuario = 0;
            while(rs.next()) id_usuario = rs.getInt("ID_usuario");
            
            ps.setInt(1, numeroId);
            ps.setInt(2, id_usuario);
            ps.setString(3, evento.getNome());
            ps.setDate(4, Date.valueOf(evento.getHorario()));
            ps.setString(5, evento.getEndereco());
            ps.setString(6, evento.getTipo());
            ps.setString(7, evento.getHora());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Evento> pesquisarEventos(String pesquisa) {
        
        List<Evento> eventos = new ArrayList<>();
        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement(PESQUISA_EVENTO);
        )
        {
         
            ps.setString(1, pesquisa);
            ps.setString(2, pesquisa);
            ps.setString(3, pesquisa);
            ps.setString(4, pesquisa);
            ps.setString(5, pesquisa);
            
            ResultSet rs = ps.executeQuery();
            
            eventos = converterParaLista(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return eventos;
    }
    
    public List<Evento> selecionarEventos() {
        
        List<Evento> eventos = new ArrayList<>();
        
        try(
                Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_EVENTOS);)
        {
            
            ResultSet rs = ps.executeQuery();
            
            eventos = converterParaLista(rs);
        } catch(SQLException e) {
            
            e.printStackTrace();
        }
        
        return eventos;
    }
    
    private Evento converter(ResultSet rs) throws SQLException {
        
        return new Evento(
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("tipo"),
                rs.getDate("horario").toLocalDate(),
                rs.getString("hora")
        );
    }
    
    private List<Evento> converterParaLista(ResultSet rs) throws SQLException {
        
        List<Evento> eventos = new ArrayList<>();
        
        while(rs.next()) eventos.add(converter(rs));
        
        return eventos;
    }
    
    public Evento retornarEvento(String nome){
        
        Evento evento = new Evento();
        
        try(
                Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_EVENTO);) {
            
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) 
                evento = converter(rs);
        }catch(SQLException e){
            
            e.printStackTrace();
        }
        
        return evento;
    }
}
