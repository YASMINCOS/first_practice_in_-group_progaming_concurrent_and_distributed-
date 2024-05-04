package sistema_reserva.pessoas;

import java.util.List;

import sistema_reserva.Hotel.Quarto;

public class PossivelHospede extends Pessoa {

    private int tentativas;
    private List<Quarto> quartos;
    private List<PossivelHospede> filaEspera;

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

    public void tentarAlugarQuarto(PossivelHospede pessoa, List<Quarto> quartos) {
        if (this.haQuartosVagos()) {
            System.out.println("Pessoa " + pessoa.getNome() + " conseguiu alugar um quarto.");
            pessoa.resetTentativas(); 
        } else {
            if (pessoa.getTentativas() < 2) {
                adicionarFilaEspera(pessoa);
                System.out.println("Não há quartos vagos. Pessoa " + pessoa.getNome() + " adicionada à fila de espera.");
                pessoa.incrementarTentativas(); 
            } else {
                pessoa.reclamarEIrEmbora(); 
            }
        }
    }

    private boolean haQuartosVagos() {
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                return true;
            }
        }
        return false;
    }

    public void adicionarFilaEspera(PossivelHospede pessoa) {
        filaEspera.add(pessoa);
    }

    public List<PossivelHospede> getListEspera(){
        return filaEspera;
    }
}
