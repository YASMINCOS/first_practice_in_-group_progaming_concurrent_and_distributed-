package sistema_reserva.pessoas.funcionarios;

import java.util.List;

import sistema_reserva.Hotel.Hotel;
import sistema_reserva.Hotel.Quarto;
import sistema_reserva.pessoas.Pessoa;

public class Camareira extends Pessoa implements Runnable{
    private int salario;
    private List<Integer> quartosLimpos;

    public Camareira(String nome, int idade, String cpf, int salario) {
        super(nome, idade, cpf);
        this.salario = salario;
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

    public void limparQuarto(Quarto quarto) {
        try {
            if (!quarto.isDisponivel()) {
                System.out.println("Iniciando limpeza do quarto " + quarto.getNumero());
                Thread.sleep(2000);
                System.out.println("Quarto " + quarto.getNumero() + " limpo.");
                quarto.setDisponivel(true);
                quarto.setChaveNaRecepcao(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Quarto quarto = null;
    
                if (quarto != null) {
                    quartoDisponivelParaLimpeza(quarto);
                } else {
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}

