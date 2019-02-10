
package src;

import java.time.LocalDate;

public class Evento {
    
    private String nome;
    private String endereco;
    private String tipo;
    private LocalDate horario;
    private String hora;
    private Usuario responsavel;

    /**
     *
     * @param nome
     * @param endereco
     * @param tipo
     * @param horario
     * @param responsavel
     */
    public Evento(String nome, String endereco, String tipo, LocalDate horario,String hora, Usuario responsavel) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.horario = horario;
        this.hora = hora;
        this.responsavel = responsavel;
    }

    public Evento(String nome, String endereco, String tipo, LocalDate horario, String hora) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.horario = horario;
        this.hora = hora;
    }
    
   

    public Evento() {
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getHorario() {
        return horario;
    }

    public void setHorario(LocalDate horario) {
        this.horario = horario;
    }
    
    @Override
    public String toString(){
        
        return ("Nome do Evento: "+this.nome);
    }
}
