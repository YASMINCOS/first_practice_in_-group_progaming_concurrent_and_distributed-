package sistema_reserva.pessoas;

public class Hospede extends Pessoa {
    private String endereco;
    private int numeroQuarto;

    public Hospede(String nome, int idade, String cpf, String endereco, int numeroQuarto) {
        super(nome, idade, cpf);
        this.endereco = endereco;
        this.numeroQuarto = numeroQuarto;
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
}