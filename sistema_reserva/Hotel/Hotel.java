package sistema_reserva.Hotel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.pessoas.Hospede;
import sistema_reserva.pessoas.PossivelHospede;
import sistema_reserva.pessoas.funcionarios.Camareira;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Hotel {
    private static List<Recepcionista> recepcionistas;
    private List<Camareira> camareiras;
    private List<Quarto> quartos;
    private static Lock lock;
    private Queue<PossivelHospede> filaEspera;
    private List<Hospede> hospedes;

    public Hotel(int numRecepcionistas, int numCamareiras, int numQuartos) {
        recepcionistas = new ArrayList<>();
        camareiras = new ArrayList<>();
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        lock = new ReentrantLock();
        filaEspera = new LinkedList<>();
        addRecepcionista(numRecepcionistas);
        addCamareiras(numCamareiras);
    }
    void addRecepcionista(int numRecepcionistas){

        String[] names = {"Ana", "Carlos", "Maria", "Joana", "Tais"};
        Integer[] idades = {40, 20, 25, 27, 21};
        String[] cpf = {"111-111-111-11", "222-222-222-22", "333-333-333-33", "444-444-444-44", "555-555-555-55"};
        Integer[] salario = {2000, 1100, 1250, 2100, 1700};

        for (int i = 0; i < numRecepcionistas; i++){
            this.recepcionistas.add(new Recepcionista(names[i], idades[i], cpf[i], salario[i]));    
        }
    }

    void addCamareiras(int numCamareiras){

        String[] names = {"Dona Maria", "Alceu"};
        Integer[] idades = {41, 59};
        String[] cpf = {"121-121-121-12", "232-232-232-23"};
        Integer[] salario = {2100, 2000};

        for (int i = 0; i < numCamareiras; i++){
            this.camareiras.add(new Camareira(names[i], idades[i], cpf[i], salario[i]));    
        }
    }

    public List<Recepcionista> getRecepcionistas() {
        return this.recepcionistas;
    }

    public List<Camareira> getCamareiras() {
        return this.camareiras;
    }

    public List<Quarto> getQuartos() {
        return this.quartos;
    }

    public List<Hospede> getHospede() {
        return this.hospedes;
    }

    public Quarto getQuartoDisponivel() {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.isDisponivel()) {
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

    public static Recepcionista getRecepcionistaDisponivel() {
        lock.lock();
        try {
            for (Recepcionista recepcionista : recepcionistas) {
                if (!recepcionista.isOcupada()) {
                    return recepcionista;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public PossivelHospede getProximoHospedeNaFila() {
        lock.lock();
        try {
            return filaEspera.poll();
        } finally {
            lock.unlock();
        }
    }

    public Quarto getQuartoPorHospede(Hospede hospede) {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.getHospedes() != null && quarto.getHospedes().equals(hospede)) {
                    return quarto;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }
    
}
