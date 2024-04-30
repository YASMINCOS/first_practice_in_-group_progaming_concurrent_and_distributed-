package sistema_reserva.pessoas;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Hospede extends Pessoa {
    private String endereco;
    private int numeroQuarto;
    private boolean estaNoQuarto;

    public Hospede(String nome, int idade, String cpf, String endereco, int numeroQuarto) {
        super(nome, idade, cpf);
        this.endereco = endereco;
        this.numeroQuarto = numeroQuarto;
        this.estaNoQuarto = true;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

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

    public void deixarChaveNaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (estaNoQuarto) {
            quarto.deixarChaveNaRecepcao();
            estaNoQuarto = false;
            recepcionista.receberChave(quarto);
        } else {
            System.out.println("O hóspede já deixou a chave na recepção.");
        }
    }

    public void retirarChaveDaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (!estaNoQuarto) {
            quarto.retirarChaveDaRecepcao();
            estaNoQuarto = true;
            recepcionista.receberChave(quarto);
        } else {
            System.out.println("O hóspede já está com a chave do quarto.");
        }
    }
}
