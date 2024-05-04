package sistema_reserva.pessoas;

import java.util.ArrayList;
import java.util.List;

import sistema_reserva.Hotel.Quarto;

public class Familia {
    private List<Pessoa> integrantes;
    private boolean chaveNaRecepcao;
    private int tentativas;
    private String name;
    Quarto quarto;

    public Familia(List<Pessoa> pessoas, String name){
        this.integrantes = pessoas;
        this.name = name;
    }

    public void setQuarto(Quarto quarto){
        this.quarto = quarto;
    }

    public String getNome(){
        return this.name;
    }

    public void reclamarEIrEmbora() {
        System.out.println("Pessoa " + getNome() + " deixou uma reclamação e foi embora.");
    }

    public int getTentativas() {
        return tentativas;
    }

    public void incrementarTentativas() {
        tentativas++;
    }

    public void resetTentativas() {
        tentativas = 0;
    }

    public boolean getChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public void setChaveNaRecepcao(boolean chaveNaRecepcao) {
        this.chaveNaRecepcao = chaveNaRecepcao;
    }
    
    public List<Hospede> getIntegrantes() {
        return integrantes;
    }

    public void addIntegrante(Pessoa pessoa) {
        integrantes.add(pessoa);
    }

}
