/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import src.Usuario;

/**
 *
 * @author Paula
 */
public interface UsuarioDAOInterface {
    
    public void cadastrar(Usuario usuario, String confirmaSenha);
    public void editar(Usuario usuario, String confirmaSenha);
    public boolean login(String email, String senha);
}
