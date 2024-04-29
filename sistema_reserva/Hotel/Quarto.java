package sistema_reserva.Hotel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import sistema_reserva.pessoas.Hospede;

public class Quarto {
    private int numero;
    private String tipo;
    private boolean disponivel;
    private boolean chaveNaRecepcao;
    private List<Hospede> hospedes;
    private Lock lock;

    private static final int CAPACIDADE_MAXIMA = 4;

    public Quarto(int numero, String tipo, boolean disponivel) {
        this.numero = numero;
        this.tipo = tipo;
        this.disponivel = disponivel;
        this.chaveNaRecepcao = true;
        this.hospedes = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public void setChaveNaRecepcao(boolean chaveNaRecepcao) {
        this.chaveNaRecepcao = chaveNaRecepcao;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void adicionarHospede(Hospede hospede) {
        lock.lock();
        try {
            if (hospedes.size() < CAPACIDADE_MAXIMA) {
                hospedes.add(hospede);
            } else {
                System.out.println("O quarto está cheio, não é possível adicionar mais hóspedes. Limite de 4 hospedes por quarto");
            }
        } finally {
            lock.unlock();
        }
    }

    public void removerHospede(Hospede hospede) {
        lock.lock();
        try {
            hospedes.remove(hospede);
        } finally {
            lock.unlock();
        }
    }
}
