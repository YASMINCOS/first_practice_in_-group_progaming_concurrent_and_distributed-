package sistema_reserva.pessoas;

import java.util.ArrayList;
import java.util.List;

public class Familia {
    private List<Hospede> hospedes = new ArrayList<Hospede>();
    private List<PossivelHospede> possiveisHospedes = new ArrayList<PossivelHospede>();
    private boolean chaveNaRecepcao;

    public boolean getChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    public void setChaveNaRecepcao(boolean chaveNaRecepcao) {
        this.chaveNaRecepcao = chaveNaRecepcao;
    }
    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<PossivelHospede> getPossiveisHospedes() {
        return possiveisHospedes;
    }

    public void addHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public void addPossivelHospede(PossivelHospede possivelHospede) {
        possiveisHospedes.add(possivelHospede);
    }

}
