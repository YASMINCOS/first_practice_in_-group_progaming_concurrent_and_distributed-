package sistema_reserva.pessoas;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.funcionarios.Recepcionista;

public class Hospede extends Pessoa implements Runnable {
    private int numeroQuarto;
    private boolean estaNoQuarto;

    public Hospede(String nome, int idade, String cpf, int numeroQuarto) {
        super(nome, idade, cpf);
        this.numeroQuarto = numeroQuarto;
        this.estaNoQuarto = true;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public boolean isEstaNoQuarto() {
        return estaNoQuarto;
    }

    public void setEstaNoQuarto(boolean estaNoQuarto) {
        this.estaNoQuarto = estaNoQuarto;
    }

    public void deixarChaveNaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (estaNoQuarto) {
            estaNoQuarto = false;
            recepcionista.receberChave(quarto, this);
        } else {
            System.out.println("O hóspede já deixou a chave na recepção.");
        }
    }

    public void retirarChaveDaRecepcao(Quarto quarto, Recepcionista recepcionista) {
        if (!estaNoQuarto) {
            estaNoQuarto = true;
        } else {
            System.out.println("O hóspede já está com a chave do quarto.");
        }
    }

    @Override
    public void run() {
        try {
            Hotel hotel = new Hotel(5, 10, 10);
            while (true) {
                Thread.sleep((long) (Math.random() * 5000));

                Quarto quarto = hotel.getQuartoPorHospede(this);
                Recepcionista recepcionista = hotel.getRecepcionistaDisponivel();
                if (quarto != null && recepcionista != null) {
                    if (quarto.getHospedes().size() < 4) {
                        deixarChaveNaRecepcao(quarto, recepcionista);
                    } else {
                        System.out.println("O quarto está cheio. O hóspede " + getNome() + " não pôde deixar a chave na recepção.");
                    }
                }

                Thread.sleep((long) (Math.random() * 5000));

                if (quarto != null && recepcionista != null) {
                    retirarChaveDaRecepcao(quarto, recepcionista);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
