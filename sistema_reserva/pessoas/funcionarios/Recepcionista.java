package sistema_reserva.pessoas.funcionarios;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;
import sistema_reserva.pessoas.PossivelHospede;

public class Recepcionista extends Pessoa {
    private int salario;
    private Queue<PossivelHospede> filaEspera;
    private Lock lock;

    public Recepcionista(String nome, int idade, String cpf, int salario) {
        super(nome, idade, cpf);
        this.salario = salario;
        this.lock = new ReentrantLock();
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void receberChave(Quarto quarto) {
        lock.lock();
        try {
            quarto.setChaveNaRecepcao(true);
            System.out.println("Recepcionista " + getNome() + " recebeu a chave do quarto " + quarto.getNumero() + ".");
        } finally {
            lock.unlock();
        }
    }

    public void adicionarFilaEspera(PossivelHospede pessoa) {
        filaEspera.add(pessoa);
        System.out.println("Pessoa " + pessoa.getNome() + " adicionada à fila de espera.");
    }

    public Pessoa proximaDaFilaEspera() {
        return filaEspera.poll();
    }

    public boolean haPessoasNaFilaEspera() {
        return !filaEspera.isEmpty();
    }

    public void atenderFilaEspera(List<Quarto> quartos) {
        while (haPessoasNaFilaEspera()) {
            Pessoa pessoa = proximaDaFilaEspera();
            tentarAlugarQuarto((PossivelHospede) pessoa, quartos);
        }
        System.out.println("Não há mais pessoas na fila de espera.");
    }

    public void tentarAlugarQuarto(PossivelHospede pessoa, List<Quarto> quartos) {
        if (haQuartosVagos(quartos)) {
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

    private boolean haQuartosVagos(List<Quarto> quartos) {
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                return true; 
            }
        }
        return false;
    }
}
