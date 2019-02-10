/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import src.Evento;

/**
 *
 * @author Paula
 */
public interface EventoDAOInterface {
    
    public void cadastrar(Evento evento, String email);
    public List<Evento> pesquisarEventos(String pesquisa);
    public List<Evento> selecionarEventos();
}
