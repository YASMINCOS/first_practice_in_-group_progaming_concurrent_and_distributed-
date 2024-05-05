package sistema_reserva.pessoas;

import java.util.ArrayList;
import java.util.List;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Familia {
    private List<Pessoa> integrantes;
    private boolean chaveNaRecepcao;
    private int tentativas;
    private String name;
    private int numeroQuarto;
    private boolean estaNoQuarto;
    Quarto quarto;

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public boolean isEstaNoQuarto() {
        return estaNoQuarto;
    }

    public void setEstaNoQuarto(boolean estaNoQuarto) {
        this.estaNoQuarto = estaNoQuarto;
    }

    public Familia(List<Pessoa> pessoas, String name){
        this.integrantes = pessoas;
        this.name = name;
    }

    public void setQuarto(Quarto quarto){
        this.quarto = quarto;
    }

    public String getNome(){
        return this.name;
    }

    public void reclamarEIrEmbora() {
        System.out.println("Pessoa " + getNome() + " deixou uma reclamação e foi embora.");
    }

    public int getTentativas() {
        return tentativas;
    }

    public void incrementarTentativas() {
        tentativas++;
    }

    public void resetTentativas() {
        tentativas = 0;
    }

    public boolean getChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public void setChaveNaRecepcao(boolean chaveNaRecepcao) {
        this.chaveNaRecepcao = chaveNaRecepcao;
    }
    
    public List<Hospede> getIntegrantes() {
        return integrantes;
    }

    public void addIntegrante(Pessoa pessoa) {
        integrantes.add(pessoa);
    }

    public void deixarChaveNaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (estaNoQuarto) {
            estaNoQuarto = false;
            recepcionista.receberChave(quarto, this);
        } else {
            System.out.println("O hóspede já deixou a chave na recepção.");
        }
    }
    
    public void retirarChaveDaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (!estaNoQuarto) {
            estaNoQuarto = true;
        } else {
            System.out.println("O hóspede já está com a chave do quarto.");
        }
    }

}
