package sistema_reserva.Hotel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.pessoas.PossivelHospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Hotel {
    private List<Recepcionista> recepcionistas;
    private List<Camareira> camareiras;
    private List<Quarto> quartos;
    private Lock lock;
    private Queue<PossivelHospede> filaEspera;

    public Hotel(int numRecepcionistas, int numCamareiras, int numQuartos) {
        recepcionistas = new ArrayList<>();
        camareiras = new ArrayList<>();
        quartos = new ArrayList<>();
        lock = new ReentrantLock();
        filaEspera = new LinkedList<>();
    }

    public List<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }

    public List<Camareira> getCamareiras() {
        return camareiras;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public Quarto getQuartoDisponivel() {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.isDisponivel()) {
                    quarto.setDisponivel(false);
                    return quarto;
                }
            }
            return null; 
        } finally {
            lock.unlock();
        }
    }

    public void liberarQuarto(Quarto quarto) {
        lock.lock();
        try {
            quarto.setDisponivel(true);
        } finally {
            lock.unlock();
        }
    }

    public void tentarAlugarQuarto(PossivelHospede pessoa, List<Quarto> quartos) {
        if (haQuartosVagos()) {
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

    public boolean adicionarFilaEspera(PossivelHospede pessoa) {
        lock.lock();
        try {
            if (haQuartosVagos()) {
                return true;
            } else {
                filaEspera.add(pessoa);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void atenderFilaEspera() {
        lock.lock();
        try {
            while (!filaEspera.isEmpty()) {
                PossivelHospede pessoa = filaEspera.poll();
                Quarto quarto = getQuartoDisponivel();
                if (quarto != null) {
                    System.out.println("Pessoa " + pessoa.getNome() + " conseguiu alugar um quarto.");
                } else {
                    pessoa.reclamarEIrEmbora();
                }
            }
            System.out.println("Não há mais pessoas na fila de espera.");
        } finally {
            lock.unlock();
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
}
