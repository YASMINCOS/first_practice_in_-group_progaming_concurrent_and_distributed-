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

    public String getTipo() {
        return tipo;
    }

    public void setHospedes(List<Hospede> hospede) {
      this.hospedes = hospede;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setChaveNaRecepcao(boolean chaveNaRecepcao) {
        this.chaveNaRecepcao = chaveNaRecepcao;
    }

    public boolean isChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void adicionarHospede(Hospede hospede) {
        lock.lock();
        try {
            if (!disponivel) {
                System.out.println("Não é possível adicionar um novo hóspede ao quarto " + numero
                        + " pois está passando por limpeza.");
            } else if (hospedes.size() >= CAPACIDADE_MAXIMA) {
                System.out.println(
                        "O quarto está cheio, não é possível adicionar mais hóspedes. Limite de 4 hóspedes por quarto");
            } else {
                System.out.println("Hospede adicionado no quarto");
                hospedes.add(hospede);
            }
        } finally {
            lock.unlock();
        }
    }

}
