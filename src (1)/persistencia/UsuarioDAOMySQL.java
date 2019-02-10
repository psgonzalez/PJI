/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import src.Usuario;

/**
 *
 * @author Paula
 */
public class UsuarioDAOMySQL implements UsuarioDAOInterface{
    
    private static final String INSERT_USUARIO = "INSERT INTO usuario values(?, ?, ?, ?)";
    private static final String UPDATE_USUARIO = "UPDATE usuario SET nome = ?, email = ?, senha = ?";
    private static final String SELECT_USUARIO = "SELECT senha FROM usuario WHERE email = ?";
    private static final String SELECT_EMAIL = "SELECT email FROM usuario";
    
    private int numeroId = 1;

    @Override
    public void cadastrar(Usuario usuario, String confirmaSenha) {
        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement ps = connection.prepareStatement(INSERT_USUARIO);
        )
        {

            if(usuario.getSenha().equals(confirmaSenha)) {
                ps.setInt(1, numeroId);
                ps.setString(2, usuario.getNome());
                ps.setString(3, usuario.getEmail());
                ps.setString(4, usuario.getSenha());
                ps.execute();
                numeroId++;
                
                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Senhas não compatíveis");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Usuario usuario, String confirmaSenha) {
        
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USUARIO);
        ) {
            if (usuario.getSenha().equals(confirmaSenha)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean login(String email, String senha) {
        
        try (Connection connection = Conexao.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USUARIO);
             PreparedStatement ps2 = connection.prepareStatement(SELECT_EMAIL);
        ) {
            ResultSet emailConsulta = ps2.executeQuery();
            
            while(emailConsulta.next()) {
                
                if(emailConsulta.getString("email").equals(email))
                   ps.setString(1, email);
                  ResultSet rs = ps.executeQuery();
                   String senhaCorreta = "";
                    
                    while (rs.next()) {
                        senhaCorreta = rs.getString(1);
                    if(senhaCorreta.equals(senha))
                        return true;
                    }
            }
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
