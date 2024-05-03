import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Hospede;
import sistema_reserva.pessoas.Pessoa;
import sistema_reserva.pessoas.PossivelHospede;

public class Recepcionista extends Pessoa implements Runnable {
    private int salario;
    private boolean ocupada;
    private Lock lock;

    public Recepcionista(String nome, int idade, String cpf, int salario) {
        super(nome, idade, cpf);
        this.salario = salario;
        this.ocupada = false;
        this.lock = new ReentrantLock();
    }
    
    public int getSalario() {
        return salario;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void receberChave(Quarto quarto, Hospede hospede) {
        lock.lock();
        try {
            quarto.setChaveNaRecepcao(true);
            System.out.println("Recepcionista " + getNome() + " recebeu a chave do quarto " + quarto.getNumero() + " do hóspede " + hospede.getNome() + ".");
        } finally {
            lock.unlock();
        }
    }

    public Quarto alocarQuartoParaHospede(List<PossivelHospede> possiveisHospedes) {
        Hotel hotel = new Hotel(5, 6, 5);
        Quarto quarto = hotel.getQuartoDisponivel();
        if (quarto != null) {
            for (PossivelHospede possivelHospede : possiveisHospedes) {
                Hospede hospede = new Hospede(possivelHospede.getNome(), possivelHospede.getIdade(), possivelHospede.getCpf(), possivelHospede.getEndereco(), quarto.getNumero());
                quarto.adicionarHospede(hospede);
            }
            System.out.println("Recepcionista " + getNome() + " alocou o quarto " + quarto.getNumero() + " para o(s) hóspede(s).");
        } else {
            System.out.println("Recepcionista " + getNome() + " não encontrou quartos disponíveis para o(s) hóspede(s).");
        }
        return quarto;
    }
    
    public Quarto entregarChaveParaHospede(Hospede hospede) {
        Hotel hotel = new Hotel(5, 3, 6);
        Quarto quarto = hotel.getQuartoPorHospede(hospede);
        if (quarto != null && quarto.isChaveNaRecepcao()) {
            quarto.setChaveNaRecepcao(false);
            System.out.println("Recepcionista " + getNome() + " entregou a chave do quarto " + quarto.getNumero() + " para o hóspede " + hospede.getNome() + ".");
        } else {
            System.out.println("Recepcionista " + getNome() + " não pôde entregar a chave do quarto para o hóspede " + hospede.getNome() + ".");
        }
        return quarto;
    }
    
    @Override
    public void run() {
        try {
            Hotel hotel = new Hotel(10, 5, 5);
            while (true) {
                Thread.sleep(2000); 
                
                PossivelHospede possivelHospede = hotel.getProximoHospedeNaFila(); 
                if (possivelHospede != null) {
                    List<PossivelHospede> possiveisHospedes = possivelHospede.getListEspera();
                    Quarto quarto = alocarQuartoParaHospede(possiveisHospedes);
                    if (quarto != null) {
                        for (PossivelHospede ph : possiveisHospedes) {
                            receberChave(quarto, new Hospede(ph.getNome(), ph.getIdade(), ph.getCpf(),quarto.getNumero())); 
                        }
                    } else {
                        System.out.println("Recepcionista " + getNome() + " não conseguiu alocar um quarto para os hóspedes.");
                    }
                } else {
                    System.out.println("Recepcionista " + getNome() + " aguardando a chegada de hóspedes...");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
