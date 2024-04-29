package sistema_reserva.pessoas;

public class PossivelHospede extends Pessoa {

    private int tentativas;

    public PossivelHospede(String nome, int idade, String cpf) {
        super(nome, idade, cpf);
        this.tentativas = 0; 
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

    public void reclamarEIrEmbora() {
        System.out.println("Pessoa " + getNome() + " deixou uma reclamação e foi embora.");
    }
}
