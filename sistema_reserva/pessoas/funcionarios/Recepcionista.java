package sistema_reserva.pessoas.funcionarios;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;

public class Recepcionista extends Pessoa {
    private int salario;
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
}
