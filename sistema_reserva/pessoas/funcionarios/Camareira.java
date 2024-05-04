package sistema_reserva.pessoas.funcionarios;

import java.util.List;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;

public class Camareira extends Pessoa implements Runnable{
    private int salario;
    private List<Quarto> quartos;
    private List<Integer> quartosLimpos;

    public Camareira(String nome, int idade, String cpf, int salario, List<Quarto> quartos) {
        super(nome, idade, cpf);
        this.salario = salario;

        this.quartos = quartos;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public List<Integer> getQuartosLimpos() {
        return quartosLimpos;
    }

    public void setQuartosLimpos(List<Integer> quartosLimpos) {
        this.quartosLimpos = quartosLimpos;
    }


    public void quartoDisponivelParaLimpeza(Quarto quarto) {
        if (quarto.isChaveNaRecepcao() && quarto.isDisponivel()) {
            System.out.println("Camareira " + getNome() + " está entrando no quarto " + quarto.getNumero() + " para limpeza.");
            limparQuarto(quarto);
        } else {
            System.out.println("Camareira " + getNome() + " não pode entrar no quarto " + quarto.getNumero() + " agora.");
        }
    }

    public Quarto obterProximoQuartoParaLimpeza() {
        for (Quarto quarto : quartos) {
            if (quarto.isChaveNaRecepcao() && !quarto.isDisponivel()) {
                return quarto;
            }
        }
        return null;
    }

    public void limparQuarto(Quarto quarto) {
        quarto.setChaveNaRecepcao(false);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread Interropida");
        }
        quarto.setChaveNaRecepcao(true);
    }

    @Override
    public void run() {
        while (true) {
            // adicionar condição de parada
            Quarto quarto = obterProximoQuartoParaLimpeza();
            if (quarto == null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread Interropida");
                }
            } else {
                limparQuarto(quarto);
            }
        }
    }
    
}

